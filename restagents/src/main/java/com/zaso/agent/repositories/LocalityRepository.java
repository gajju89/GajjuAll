package com.zaso.agent.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.Locality;

@Repository
public interface LocalityRepository extends MongoRepository<Locality,String> {
	
	
	Locality insert(Locality locality);
	
	List<Locality> findAll();
	
	List<Locality> findBylocalityId(String id);

}
