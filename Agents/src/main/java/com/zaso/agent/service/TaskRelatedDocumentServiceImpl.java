package com.zaso.agent.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.zaso.agent.model.TaskRelatedDocument;
import com.zaso.agent.repositories.TaskRelatedDocRepository;
@Repository
public class TaskRelatedDocumentServiceImpl implements TaskRelatedDocumentService {
	@Autowired
	TaskRelatedDocRepository taskRepo;
	

	@Override
	public void saveTaskRelatedDocument(TaskRelatedDocument taskdoc) {
		// TODO Auto-generated method stub
		taskRepo.save(taskdoc);

	}

	@Override
	public List<TaskRelatedDocument> getAllTask() {
		// TODO Auto-generated method stub
		return taskRepo.findAll();
	}

	
	public HashMap<String, List<TaskRelatedDocument>> getTaskDocument(String task) {
		// TODO Auto-generated method stub
		HashMap<String, List<TaskRelatedDocument>> hashTask=new HashMap<String, List<TaskRelatedDocument>>();
		List<TaskRelatedDocument> list=taskRepo.findByTask(task);
		 hashTask.put("task",list);
		 return hashTask;
		
	}
	
	

}
