package demo.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.document.mongodb.MongoTemplate;

import repository.PersonRepository;
import core.Address;
import core.Person;

public class MongoDemo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml", MongoDemo.class);
		
		// clear out old test data first
		MongoTemplate template  = context.getBean(MongoTemplate.class);
		if (template.collectionExists("person")) {
			template.dropCollection("person");
		}
		
		PersonRepository personRepository = context.getBean(PersonRepository.class);
		
		// add some people
		Person mark = createMark();
		mark = personRepository.save(mark);
		Person thomas = createThomas();
		thomas = personRepository.save(thomas);
		
		System.out.println("There are currently " + personRepository.count() + " people in the DB:");
		List<Person> people = personRepository.findAll();
		for (Person person : people) {
			System.out.println(person);
		}
		
		Person found = personRepository.findByName("Mark");
		System.out.println("Found: " + found);

	}

	private static Person createMark() {
		Person mark = new Person("Mark");
		Map<String, Object> marksPersonalInfo = new HashMap<String, Object>();
		marksPersonalInfo.put("Project Lead", "Spring Integration");
		mark.setPersonalInfo(marksPersonalInfo);
		Address marksAddress = new Address();
		marksAddress.setStreet("123 Main Street");
		marksAddress.setCity("Cambridge");
		marksAddress.setState("MA");
		marksAddress.setZip("02140");
		mark.setAddress(marksAddress);
		return mark;
	}

	private static Person createThomas() {
		Person thomas = new Person("Thomas");
		Map<String, Object> thomasPersonalInfo = new HashMap<String, Object>();
		thomasPersonalInfo.put("Hobby", "photography");
		thomasPersonalInfo.put("Sport", "soccer");
		thomas.setPersonalInfo(thomasPersonalInfo);
		return thomas;
	}

}
