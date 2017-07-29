package com.zaso.agent.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.DocumentAvailable;

@Repository
public interface DocumentAvailRepository extends MongoRepository<DocumentAvailable, String> {
	
	@SuppressWarnings("unchecked")
	DocumentAvailable save(DocumentAvailable docAvailable);
	
	List<DocumentAvailable> findAll();
	
	List<DocumentAvailable> findBydocNumber(String docnumber);

}
