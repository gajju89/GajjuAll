package com.zaso.agent.dao;



import java.util.List;

import com.zaso.agent.model.DocumentAvailable;

public interface DocumentAvailableDao {
	
	public void saveDocument(DocumentAvailable docAvailable);
	
	public List<DocumentAvailable> findDocument(String docNumber);
	
	public List<DocumentAvailable> findDocument();

}
