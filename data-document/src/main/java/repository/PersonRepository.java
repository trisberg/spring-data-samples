package repository;

import java.util.List;

import org.springframework.data.document.mongodb.repository.MongoRepository;

import core.Person;

public interface PersonRepository extends MongoRepository<Person, String> {

	List<Person> findAll();
	
	Person findByName(String name);

	Person save(Person person);

}
