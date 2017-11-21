package com.example.springredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisService {
	private RedisTemplate<String, User> redisTemplate;
	
	@Autowired
	public RedisService(RedisTemplate<String, User> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	public void addUser(User user) {
		redisTemplate.opsForList().leftPush("user", user);
	}
}
