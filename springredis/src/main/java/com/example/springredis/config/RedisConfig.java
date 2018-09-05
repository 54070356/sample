package com.example.springredis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class RedisConfig {
    /**
     * Type safe representation of application.properties
     */
    @Autowired RedisConfigProperties clusterProperties;

    public @Bean RedisConnectionFactory connectionFactory() {
    		if(clusterProperties.isCluster()) {
        return new JedisConnectionFactory(
            new RedisClusterConfiguration(clusterProperties.getNodes()));
    } else {
    		RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration(clusterProperties.getHost(), clusterProperties.getPort());
    		return new JedisConnectionFactory(serverConfig);
    }
    }
}
