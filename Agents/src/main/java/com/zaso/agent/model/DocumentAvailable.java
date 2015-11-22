package com.zaso.agent.model;

import org.springframework.stereotype.Repository;

@Repository
public class DocumentAvailable {
	
	private String docNumber;
	private String docName;
	
	
	public String getDocNumber() {
		return docNumber;
	}
	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	
	@Override
	public String toString() {
		return "DocumentAvailable [docNumber=" + docNumber + ", docName=" + docName + "]";
	}
	
	
	
	
	

}
