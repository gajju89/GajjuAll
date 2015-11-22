package com.zaso.agent.config;


import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@PropertySource("classpath:application.properties")
public class AgentConfig {
	
	 private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
	    
	    @Resource
	    private Environment env;
	    
	    @Bean
	    public CommonsMultipartResolver multipartResolver(){
	    	CommonsMultipartResolver multipartConfigFactory = new CommonsMultipartResolver();
	        multipartConfigFactory.setMaxUploadSizePerFile(50*1024L);
	     
	       return multipartConfigFactory;
	    }
	    
	    @Bean
	    public DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	 
	        dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
	        dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
	        dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
	        dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
	 
	        return dataSource;
	    }
	    
	    @Bean
	    public JdbcTemplate jdbcTemplate() {
	        return new JdbcTemplate(dataSource());
	    }

}
