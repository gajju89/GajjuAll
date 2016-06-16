package com.zaso.agent.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.RatingByUser;

@Repository

public interface RatingByUserRepository extends MongoRepository<RatingByUser,String> {
	
	List<RatingByUser> findAll();
	
	RatingByUser insert(RatingByUser rating);
	
	List<RatingByUser> findByagentEmail(String email);
	
	void deleteByagentEmail(String email);

}
