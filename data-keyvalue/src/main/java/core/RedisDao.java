package core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.keyvalue.redis.core.RedisTemplate;
import org.springframework.data.keyvalue.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisDao {
	
	private ValueOperations<String, Object> valueOps;
	
	@Autowired
	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.valueOps = redisTemplate.opsForValue();
	}

	public void add(String key, Object value) {
		valueOps.set(key, value);
	}
	
	public Object get(String key) {
		return valueOps.get(key);
	}

}
