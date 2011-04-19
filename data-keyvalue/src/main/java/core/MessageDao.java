package core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.keyvalue.redis.connection.RedisConnectionFactory;
import org.springframework.data.keyvalue.redis.core.RedisTemplate;
import org.springframework.data.keyvalue.redis.core.ValueOperations;
import org.springframework.data.keyvalue.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.keyvalue.redis.serializer.StringRedisSerializer;
import org.springframework.data.keyvalue.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Repository;

import domain.Message;
import domain.User;

@Repository
public class MessageDao {
	
	private ValueOperations<String, Message> ops;
		
	@Autowired
	public void setConnectionFactory(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Message> redisTemplate = new RedisTemplate<String, Message>(connectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new JacksonJsonRedisSerializer<User>(User.class));
		redisTemplate.setStringSerializer(new StringRedisSerializer());
		this.ops = redisTemplate.opsForValue();
	}


	public void add(User user, Message message) {
		this.ops.set(user.getId() + ":" + message.getTimestamp(), message);
	}
	
	public Object get(String key) {
		return this.ops.get(key);
	}
}
