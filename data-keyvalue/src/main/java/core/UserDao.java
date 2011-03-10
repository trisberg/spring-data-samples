package core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.keyvalue.redis.connection.RedisConnectionFactory;
import org.springframework.data.keyvalue.redis.core.RedisTemplate;
import org.springframework.data.keyvalue.redis.core.ValueOperations;
import org.springframework.data.keyvalue.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.keyvalue.redis.serializer.StringRedisSerializer;
import org.springframework.data.keyvalue.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Repository;

import domain.User;

@Repository
public class UserDao {
	
	private ValueOperations<String, User> ops;
	
	private RedisAtomicLong userIdCounter;
	
	@Autowired
	public void setConnectionFactory(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, User> redisTemplate = new RedisTemplate<String, User>(connectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new JacksonJsonRedisSerializer<User>(User.class));
		redisTemplate.setStringSerializer(new StringRedisSerializer());
		this.ops = redisTemplate.opsForValue();

		RedisTemplate<String, Long> longTemplate = new RedisTemplate<String, Long>(connectionFactory);
		longTemplate.setKeySerializer(new StringRedisSerializer());
		longTemplate.setValueSerializer(new JacksonJsonRedisSerializer<Long>(Long.class));
		this.userIdCounter = new RedisAtomicLong("global:uid", longTemplate);
	}


	public void add(User user) {
		user.setId(newUserId());
		this.ops.set(user.getId(), user);
	}
	
	public Object get(String key) {
		return this.ops.get(key);
	}

	private String newUserId() {
		return "user:" + this.userIdCounter.incrementAndGet();
	}
}
