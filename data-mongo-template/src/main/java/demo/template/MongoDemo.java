package demo.template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;

import domain.Address;
import domain.Person;

public class MongoDemo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml", MongoDemo.class);
		
		// clear out old test data first
		MongoTemplate template  = context.getBean(MongoTemplate.class);
		if (template.collectionExists(template.getCollectionName(Person.class))) {
			template.dropCollection(template.getCollectionName(Person.class));
		}
		
		// add some people
		Person mark = createMark();
		template.save(mark);
		Person thomas = createThomas();
		template.save(thomas);
		
		System.out.println("There are currently " + template.getCollection(Person.class).size() + " people in the DB:");
		List<Person> people = template.getCollection(Person.class);
		for (Person person : people) {
			System.out.println(person);
		}
		
		Person found = template.findOne(new Query(Criteria.where("name").is("Mark")), Person.class);
		System.out.println("Found: " + found);

		System.out.println("Using: " + template.getCollectionName(Person.class));

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
