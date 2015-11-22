package com.zaso.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zaso.agent.dao.DocumentAvailableDao;
import com.zaso.agent.model.DocumentAvailable;

public interface DocumentAvailableService {
	
	
	public void saveDocumnet(DocumentAvailable docAvailable);
	
	public List<DocumentAvailable> findDocument(String docNumber);
	
	public List<DocumentAvailable> findDocument();

}
