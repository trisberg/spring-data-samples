package core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.graph.neo4j.support.GraphDatabaseContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Repository
@Transactional
public class Neo4jPersonRepository implements PersonRepository {

	private GraphDatabaseContext graphDatabaseContext;

	@Autowired
	private Neo4jPersonRepository(GraphDatabaseContext graphDatabaseContext) {
		Assert.notNull(graphDatabaseContext, "GraphDatabaseContext is required");
		this.graphDatabaseContext = graphDatabaseContext;
	}

	public int count() {
		long count = this.graphDatabaseContext.count(Person.class);
		return (int)count;
	}

	public List<Person> getAll() {
		List<Person> people = new ArrayList<Person>();
		for (Person p :  this.graphDatabaseContext.findAll(Person.class)) {
			people.add(p);
		}
		return people;
	}

	public void add(Person person) {
		person.persist();
	}

}
