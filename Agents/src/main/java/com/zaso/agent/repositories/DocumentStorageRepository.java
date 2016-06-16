package com.zaso.agent.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.DocumentStorage;

@Repository
public interface DocumentStorageRepository extends MongoRepository<DocumentStorage,String> {
	
	DocumentStorage save(DocumentStorage storage);
	
	

}
