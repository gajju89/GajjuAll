package com.zaso.agent.model;

import java.net.URL;

import org.springframework.stereotype.Repository;

@Repository
public class DocumentStorage {
	private String docid;
	private String docNumber;
	private String userId;
	private String objectKey;
	
	
	public String getDocid() {
		return docid;
	}
	public void setDocid(String docid) {
		this.docid = docid;
	}
	public String getDocNumber() {
		return docNumber;
	}
	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getObjectKey() {
		return objectKey;
	}
	public void setObjectKey(String objectKey) {
		this.objectKey = objectKey;
	}
	@Override
	public String toString() {
		return "Document [docNumber=" + docNumber + ", userId=" + userId + ", objectKey=" + objectKey + "]";
	}
	
	
	
	

}
