package core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.keyvalue.redis.connection.RedisConnectionFactory;
import org.springframework.data.keyvalue.redis.core.RedisTemplate;
import org.springframework.data.keyvalue.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.keyvalue.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;

import domain.User;

@Repository
public class UserDao {
	
	private RedisTemplate<String, User> redisTemplate;
	
	@Autowired
	public void setConnectionFactory(RedisConnectionFactory connectionFactory) {
		this.redisTemplate = new RedisTemplate<String, User>(connectionFactory);
		this.redisTemplate.setKeySerializer(new StringRedisSerializer());
		this.redisTemplate.setValueSerializer(new JacksonJsonRedisSerializer<User>(User.class));
		this.redisTemplate.setStringSerializer(new StringRedisSerializer());
	}


	public void add(User user) {
		redisTemplate.opsForValue().set(user.getId().toString(), user);
	}
	
	public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

}
