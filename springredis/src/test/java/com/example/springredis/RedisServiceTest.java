package com.example.springredis;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

 

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.example.springredis"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@PropertySource({"classpath:core-test.properties"})
public class RedisServiceTest {
	
	@Autowired
	RedisService redisService;
	
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
}
