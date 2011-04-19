package repository;

import org.springframework.data.document.mongodb.repository.MongoRepository;

import domain.Person;

public interface PersonRepository extends MongoRepository<Person, String> {

	Person findByName(String name);

}
