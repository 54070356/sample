package com.example.springredis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig1 {
//	@Bean
//	public RedisTemplate<String, User> msgOfferRedisTemplate(RedisConnectionFactory connectionFactory) {
//		RedisTemplate<String, User>  template = new RedisTemplate<String, User>();
//		template.setConnectionFactory(connectionFactory);
//		template.setKeySerializer(new StringRedisSerializer());
//		template.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class) );
//		return template;
//	}
	
	
	@Bean
	public RedisTemplate<String, User> userRedisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, User>  template = new RedisTemplate<String, User>();
		template.setConnectionFactory(connectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new MyRedisSerializer<User>(User.class) );
		return template;
	}
	
	@Bean
	public RedisTemplate<String, ResponseStat> respStatRedisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, ResponseStat>  template = new RedisTemplate<String, ResponseStat>();
		template.setConnectionFactory(connectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new Jackson2JsonRedisSerializer<ResponseStat>(ResponseStat.class) );
		return template;
	}
}
