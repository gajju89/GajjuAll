package com.zaso.agent.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.LocalityWithin;

@Repository
public interface LocalityWithinRepository extends MongoRepository<LocalityWithin,String>{
	
	@SuppressWarnings("unchecked")
	LocalityWithin insert(LocalityWithin within);
	
	List<LocalityWithin> findAll();
	
	List<LocalityWithin> findBylocality(String locality);
	List<LocalityWithin> findBylocalityId(String id);
	

}
