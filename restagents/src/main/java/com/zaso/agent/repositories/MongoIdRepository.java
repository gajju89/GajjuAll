package com.zaso.agent.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zaso.agent.model.LastMongoId;
import com.zaso.agent.model.MongoAgent;

public interface MongoIdRepository extends MongoRepository<LastMongoId, String> {
	@Override
	List<LastMongoId> findAll();
	

}
