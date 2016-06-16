package com.zaso.agent.service;

import java.util.HashMap;
import java.util.List;

import com.zaso.agent.model.TaskRelatedDocument;

public interface TaskRelatedDocumentService {
	

	public void saveTaskRelatedDocument(TaskRelatedDocument taskdoc);
	
	public List<TaskRelatedDocument> getAllTask();
	
	public HashMap<String, List<TaskRelatedDocument>> getTaskDocument(String task);

	

}
