package com.onlinetv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;


	
	  
	@Configuration
	@EnableMongoRepositories({"com.onlinetv..repositories"})
	public class MongoConfig {
	  
	    public static MongoDbFactory getMongoDbFactory() throws Exception {
	    	return new SimpleMongoDbFactory(new MongoClient("localhost",27017), "admin");
	    }
	 
	    public  static MongoTemplate getMongoTemplate() throws Exception {
	        MongoTemplate mongoTemplate = new MongoTemplate(getMongoDbFactory());
	        return mongoTemplate;
	    }

}