package core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.document.mongodb.MongoFactoryBean;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.SimpleMongoConverter;

import com.mongodb.Mongo;

@Configuration
public class MongoConfiguration {

    public @Bean MongoFactoryBean mongoFactory() {
    	return new MongoFactoryBean(); 
    }

    public @Bean PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
    	return new PersistenceExceptionTranslationPostProcessor();
    }
    
    public @Bean MongoTemplate mongoTemplate(Mongo mongo) {
    	return new MongoTemplate(mongo, "test", "person", new SimpleMongoConverter());
    }

}
