package com.zaso.agent.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

@Repository
@Document(collection="taskRelatedDocument")
public class TaskRelatedDocument {
	private String taskId;
	private String task;
	private String docNumber;
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getDocNumber() {
		return docNumber;
	}
	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}
	
	@Override
	public String toString() {
		return "TaskRelatedDocument [task=" + task + ", docNumber=" + docNumber + "]";
	}
	
	

}
