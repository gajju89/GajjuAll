package com.zaso.agent.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.TaskRelatedDocument;

@Repository
public interface TaskRelatedDocRepository extends MongoRepository<TaskRelatedDocument, String> {
	
	TaskRelatedDocument save(TaskRelatedDocument taskRelatedDocument);
	
	List<TaskRelatedDocument> findAll();
	
	List<TaskRelatedDocument> findByTask(String task);
	

}
