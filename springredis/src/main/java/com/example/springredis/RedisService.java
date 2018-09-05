package com.example.springredis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

@Component
public class RedisService {
	@Autowired
	private RedisTemplate<String, User> redisTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	@Qualifier("checkandset")
	private RedisScript<Boolean> checkAndSet;

	@Autowired
	private RedisTemplate<String, ResponseStat> respStatRedisTemplate;

	@Autowired
	@Qualifier("findstat")
	private RedisScript<String> findStat;
	
	@Autowired
	@Qualifier("isMessageRespondedInHistory")
	private RedisScript<String> isMessageRespondedInHistoryScript;
	
	@Autowired
	@Qualifier("testargs")
	private RedisScript<String> testargsScript;

	public RedisService() {

	}

	public void addUser(User user) {
		redisTemplate.opsForList().leftPush("user", user);
	}

	public User getUser() {
		return redisTemplate.opsForList().leftPop("user");
	}

	public void checkAndSet() {
		List<String> keys = new ArrayList<String>();
		keys.add("checkAndSet");
		boolean result = stringRedisTemplate.execute(checkAndSet, keys, "Tom", "Jack");
	}

	public void addRespStat(ResponseStat stat) {
		respStatRedisTemplate.opsForList().leftPush("stat", stat);
	}

	public String findStat(String msgKey, String channalKey, String respKey, String days, String count) {
		List<String> keys = new ArrayList<String>();
		keys.add("stat");
		return stringRedisTemplate.execute(findStat, keys, msgKey, channalKey, respKey,days,
				count);
	}
	
	public List<ResponseStat> getStats() {
		return respStatRedisTemplate.opsForList().range("stat", 0, -1);
	}
	
	public String isMessageRespondedInHistory(String msgKey, String msgGroupKey, String channalKey, String respKey, String days, String count) {
		List<String> keys = new ArrayList<String>();
		keys.add("stat");
		return stringRedisTemplate.execute(isMessageRespondedInHistoryScript, keys, msgKey, msgGroupKey, channalKey, respKey, days,
				count);
	}
	
	public String testargs(String s, String i, String b) {
		List<String> keys = new ArrayList<String>();
		keys.add("testargs");
		return stringRedisTemplate.execute(testargsScript, keys, s, i, b);
	}
}
