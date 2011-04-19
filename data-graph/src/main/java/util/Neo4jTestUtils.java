package util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.graph.neo4j.support.GraphDatabaseContext;
import org.springframework.data.graph.neo4j.support.node.Neo4jHelper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import core.Person;

@Component
public class Neo4jTestUtils {

	@Autowired
	private GraphDatabaseContext graphDatabaseContext;

	@Transactional
    public void clearDatabase()
    {
		System.out.println("REMOVING: " + graphDatabaseContext.count(Person.class));
		Neo4jHelper.cleanDb(graphDatabaseContext);
    }

}
