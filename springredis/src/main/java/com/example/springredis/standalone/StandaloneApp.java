package com.example.springredis.standalone;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StandaloneApp {
	@Autowired
	private JedisConnectionFactory connectionFactory;
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Test
	public void test() {
		RedisConnection connection = connectionFactory.getConnection();
		System.out.println(connection.getClass().getName()); //JedisClusterConnection
		 
		connection.set("thing1".getBytes(), "thing1".getBytes());           // slot: 12182
		connection.set("{thing1}.thing2".getBytes(), "{thing1}.thing2".getBytes());  // slot: 12182
		connection.set("thing2".getBytes(), "thing2".getBytes());           // slot:  5461

		List<byte[]> list1 = connection.mGet("thing1".getBytes(), "{thing1}.thing2".getBytes());
		print(list1);
		List<byte[]> list2 = connection.mGet("thing1".getBytes(), "thing2".getBytes()); 
		print(list2);
		
	}
	
	@Test
	public void testTemplate() {
		redisTemplate.opsForValue().set("name", "Jack");
	}
	
	private void print(List<byte[]> list) {
		list.forEach(item->{
			System.out.println(new String(item));
		});
	}

}
