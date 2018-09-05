package com.example.springredis.script;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

@Configuration
public class LuaScriptConfig {
	@Bean(name="checkandset")
	public RedisScript<Boolean> script() {
	  DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<Boolean>();
	  redisScript.setLocation(new ClassPathResource("/checkandset.lua"));
	  redisScript.setResultType(Boolean.class);
	  return redisScript;
	}
	
	@Bean(name="findstat")
	public RedisScript<String> findStatScript() {
	  DefaultRedisScript<String> redisScript = new DefaultRedisScript<String>();
	  redisScript.setLocation(new ClassPathResource("/findstat.lua"));
	  redisScript.setResultType(String.class);
	  return redisScript;
	}
	
	@Bean(name="isMessageRespondedInHistory")
	public RedisScript<String> isMessageRespondedInHistoryScript() {
	  DefaultRedisScript<String> redisScript = new DefaultRedisScript<String>();
	  redisScript.setLocation(new ClassPathResource("/isMessageRespondedInHistory.lua"));
	  redisScript.setResultType(String.class);
	  return redisScript;
	}
	
	
	@Bean(name="testargs")
	public RedisScript<String> testargsScript() {
	  DefaultRedisScript<String> redisScript = new DefaultRedisScript<String>();
	  redisScript.setLocation(new ClassPathResource("/testargs.lua"));
	  redisScript.setResultType(String.class);
	  return redisScript;
	}
	
	
	
	
}
