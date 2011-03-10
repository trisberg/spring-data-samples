package demo.repository;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.keyvalue.redis.connection.RedisCommands;
import org.springframework.data.keyvalue.redis.connection.RedisConnectionFactory;

import core.RedisDao;
import core.UserDao;
import domain.User;

public class RedisDemo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml", RedisDemo.class);
		
		RedisDao dao = context.getBean(RedisDao.class);
		RedisCommands commands = (RedisCommands)context.getBean(RedisConnectionFactory.class).getConnection();
		UserDao userDao = context.getBean(UserDao.class);
		
		
		// add some data
		dao.add("One", "some test data");
		dao.add("Two", "some more test data");
		dao.add("Three", new Date());
		dao.add("Four", 12345.67);
		User u = new User("Thomas");
		userDao.add(u);
		
		System.out.println("There are currently " + commands.keys("*".getBytes()).size() + " keys in the DB:");
		
		System.out.println("One: " + dao.get("One"));
		System.out.println("Two: " + dao.get("Two"));
		System.out.println("Three: " + new Date((Long) dao.get("Three")));
		System.out.println("Four: " + dao.get("Four"));
		System.out.println("User: " + userDao.get(u.getId().toString()));

	}

}
