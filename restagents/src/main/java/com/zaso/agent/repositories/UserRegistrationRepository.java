package com.zaso.agent.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.UserRegistration;

@Repository
public interface UserRegistrationRepository extends MongoRepository<UserRegistration, String> {
	
	UserRegistration insert(UserRegistration userRegistration);
	
	List<UserRegistration> findByemailid(String email);

}
