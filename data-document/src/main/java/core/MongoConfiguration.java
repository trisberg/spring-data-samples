package core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.document.mongodb.MongoFactoryBean;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.convert.MappingMongoConverter;
import org.springframework.data.document.mongodb.convert.MongoConverter;
import org.springframework.data.document.mongodb.mapping.MongoMappingContext;

import com.mongodb.Mongo;

@Configuration
public class MongoConfiguration {

    public @Bean MongoFactoryBean mongoFactory() {
    	return new MongoFactoryBean(); 
    }

    public @Bean PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
    	return new PersistenceExceptionTranslationPostProcessor();
    }
    
    public @Bean MongoConverter mongoConverter() {
    	return new MappingMongoConverter(new MongoMappingContext());
    }

    public @Bean MongoTemplate mongoTemplate(Mongo mongo, MongoConverter mongoConverter) {
    	MongoTemplate template = new MongoTemplate(mongo, "test", "person");
    	template.setMongoConverter(mongoConverter);
    	return template;
    }

}
