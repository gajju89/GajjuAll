package com.zaso.agent.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.UserAuthentication;

@Repository
public interface UserAuthenticationRepository extends MongoRepository<UserAuthentication, String> {

	UserAuthentication findByAuthCode(String accessToken);
	
	UserAuthentication insert(UserAuthentication userAuthentication);
	
	Long deleteByauthCode(String accessToken);
	
	UserAuthentication findByEmailId(String email);
}
