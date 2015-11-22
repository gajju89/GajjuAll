package com.zaso.mongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;


	
	  
	@Configuration
	@EnableMongoRepositories({"com.zaso.mongo.repositories"})
	public class MongoConfig {
	  
	    public @Bean MongoDbFactory getMongoDbFactory() throws Exception {
	    	return new SimpleMongoDbFactory(new MongoClient("localhost",27017), "agents");
	    }
	 
	    public @Bean(name="mongoTemplate") MongoTemplate getMongoTemplate() throws Exception {
	        MongoTemplate mongoTemplate = new MongoTemplate(getMongoDbFactory());
	        return mongoTemplate;
	    }

}
