package com.zaso.agent.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zaso.agent.dao.TaskRelatedDocumentDao;
import com.zaso.agent.model.TaskRelatedDocument;
@Repository
public class TaskRelatedDocumentServiceImpl implements TaskRelatedDocumentService {
	@Autowired
	TaskRelatedDocumentDao taskdao;

	@Override
	public void saveTaskRelatedDocument(TaskRelatedDocument taskdoc) {
		// TODO Auto-generated method stub
		taskdao.saveTaskRelatedDocument(taskdoc);

	}

	@Override
	public List<TaskRelatedDocument> getAllTask() {
		// TODO Auto-generated method stub
		return taskdao.getAllTask();
	}

	@Override
	public HashMap<String, List<TaskRelatedDocument>> getTaskDocument(String task) {
		// TODO Auto-generated method stub
		return taskdao.getTaskDocument(task);
	}
	
	

}
