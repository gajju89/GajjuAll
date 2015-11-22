package com.zaso.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zaso.agent.dao.DocumentAvailableDao;
import com.zaso.agent.model.DocumentAvailable;

@Repository
public class DocumentAvailableServiceImpl implements DocumentAvailableService {
	
	@Autowired
	DocumentAvailableDao docDao;

	@Override
	public void saveDocumnet(DocumentAvailable docAvailable) {
		// TODO Auto-generated method stub
		docDao.saveDocument(docAvailable);

	}
	public List<DocumentAvailable> findDocument(String docNumber)
	{
		return docDao.findDocument(docNumber);
	}
	
	public List<DocumentAvailable> findDocument()
	{
		return docDao.findDocument();
	}

}
