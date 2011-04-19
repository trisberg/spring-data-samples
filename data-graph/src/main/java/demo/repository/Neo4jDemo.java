package demo.repository;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import util.Neo4jTestUtils;

import core.Person;
import core.PersonRepository;

public class Neo4jDemo {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("config.xml", Neo4jDemo.class);
		
       // first clean up from any previous runs
        Neo4jTestUtils graphUtils = context.getBean(Neo4jTestUtils.class);
        graphUtils.clearDatabase();
        
		final PersonRepository personRepository = context.getBean(PersonRepository.class);

		// add some people - must be done inside a transaction
        new TransactionTemplate(context.getBean(PlatformTransactionManager.class))
        	.execute(new TransactionCallback<Object>() {
					@Override
					public Object doInTransaction(TransactionStatus status) {
						Person mark = new Person("Mark");
						personRepository.add(mark);
						Person thomas = new Person("Thomas");
						personRepository.add(thomas);
						mark.addFriend(thomas);
						Person emil = new Person("Emil");
						personRepository.add(emil);
						thomas.addFriend(emil);
						@SuppressWarnings("unused")
						Person bubba = new Person("Bubba");
						personRepository.add(bubba);
						return null;
					}
		        });

		System.out.println("There are currently " + personRepository.count() + " people in the DB:");
		List<Person> people = personRepository.getAll();
		for (Person person : people) {
			System.out.println(person.toString() + " has " + person.friendsToString());
		}

		context.close();
	}

}
