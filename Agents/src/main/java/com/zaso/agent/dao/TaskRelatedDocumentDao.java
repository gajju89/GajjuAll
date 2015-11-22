package com.zaso.agent.dao;

import java.util.HashMap;
import java.util.List;

import javax.swing.text.TabableView;

import com.zaso.agent.model.TaskRelatedDocument;

public interface TaskRelatedDocumentDao {
	
	public void saveTaskRelatedDocument(TaskRelatedDocument taskDocument);
	
	public List<TaskRelatedDocument> getAllTask();
	
	public HashMap<String, List<TaskRelatedDocument>> getTaskDocument(String task);

}
