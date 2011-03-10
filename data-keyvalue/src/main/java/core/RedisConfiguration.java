package core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.keyvalue.redis.connection.RedisConnectionFactory;
import org.springframework.data.keyvalue.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.keyvalue.redis.core.RedisTemplate;
import org.springframework.data.keyvalue.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.keyvalue.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

	@Bean
	RedisConnectionFactory connectionFactory() {
		JedisConnectionFactory jedisConnFactory = new JedisConnectionFactory();
		jedisConnFactory.setUsePool(true);
		return jedisConnFactory;
	}
	
	@Bean
	RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>(connectionFactory);
		//template.setDefaultSerializer(new JacksonJsonRedisSerializer<Object>(Object.class));
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new JacksonJsonRedisSerializer<Object>(Object.class));
		template.setStringSerializer(new StringRedisSerializer());
		return template;
	}
}
