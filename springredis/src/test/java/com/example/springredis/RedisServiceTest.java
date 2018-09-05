package com.example.springredis;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = { "com.example.springredis" })
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@PropertySource({ "classpath:application.properties" })
public class RedisServiceTest {

	@Autowired
	RedisService redisService;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Test
	public void testAddUser() {
		User user = new User();
		user.setUserId(1000);
		user.setUsername("Tom");
		user.setAddress("China");
		user.setBirthday(Calendar.getInstance().getTime());
		user.setMarried(false);
		user.setSex(2);

		redisService.addUser(user);

		User readUser = redisService.getUser();
		Assert.assertEquals(1000, readUser.getUserId());
	}

	@Test
	public void testCheckAndSet() {
		redisService.checkAndSet();
	}

	@Test
	public void testRespStat() {
		Set<String> keys = redisTemplate.keys("stat*");
		redisTemplate.delete(keys);
		for (int i = 0; i < 1000; i++) {
			ResponseStat stat = new ResponseStat();
			stat.setMsgKey("msgKey" + (i % 2));
			stat.setChannelKey("channelKey" + i % 2);
			stat.setCount(i);
			stat.setDays(i % 3 + 1);
			redisService.addRespStat(stat);
		}
		long start = System.currentTimeMillis();
		int maxLoop = 100;
		String result = null;
		for (int i = 0; i < maxLoop; i++) {
			result = redisService.findStat("msgKey1", "channelKey1", "respKey1", "2", "3");
		}
		long end = System.currentTimeMillis();
		System.out.println("time: " + (end - start));
		System.out.println("sum: " + result);
		// Assert.assertTrue(result);

		start = System.currentTimeMillis();
		int sum = 0;
		for (int i = 0; i < maxLoop; i++) {
			sum = 0;
			List<ResponseStat> stats = redisService.getStats();

			for (ResponseStat stat : stats) {
				if (stat.getMsgKey().equals("msgKey1") && stat.getChannelKey().equals("channelKey1")) {
					sum += stat.getCount();
				}
			}

		}

		end = System.currentTimeMillis();
		System.out.println("time: " + (end - start));
		System.out.println("sum2: " + sum);
	}
	
	
	@Test
	public void testIsMessageRespondedInHistory() {
		Set<String> keys = redisTemplate.keys("stat*");
		redisTemplate.delete(keys);
		List<String> lists = new ArrayList<String>();
		//count, days
		lists.add("msgKey1|web|accept|msgGroup1|2|2");
		//lists.add("msgKey1|web|reject|msgGroup1|1|1");

		//lists.add("msgKey2|callcenter|reject|msgGroup1|2|1");
 		//lists.add("msgKey1|callcenter|accept|msgGroup1|1|1");
		
		//lists.add("msgKey1:web:accept:FmsgGroup1:3");
	
		
		redisTemplate.opsForList().leftPushAll("stat", lists);
		 
		long start = System.currentTimeMillis();
	 
		String result = redisService.isMessageRespondedInHistory("msgKey1", "", "web", "accept", "2", "3"); 
		 
		long end = System.currentTimeMillis();
		System.out.println("time: " + (end - start));
		System.out.println("result: " + result);
		// Assert.assertTrue(result);
	}
	
	@Test
	public void testargs() {
		String result = redisService.testargs("", "1", "true");
		System.out.println("result: " + Integer.parseInt(result));
	}

}
