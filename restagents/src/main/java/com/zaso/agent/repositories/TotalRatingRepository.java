package com.zaso.agent.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.TotalRating;

@Repository

public interface TotalRatingRepository extends MongoRepository<TotalRating, String> {
	
	List<TotalRating> findAll();
	
	TotalRating insert(TotalRating total);
	void deleteByagentEmail(String email);
	List<TotalRating> findByagentEmail(String email);

}
