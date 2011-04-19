package core;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.graph.neo4j.config.Neo4jConfiguration;

@Configuration
public class MyNeo4jConfiguration extends Neo4jConfiguration {

	@Bean(destroyMethod = "shutdown")
	public GraphDatabaseService graphDatabaseService() {
		return new EmbeddedGraphDatabase("target/neo4j-mejug");
	}

}
