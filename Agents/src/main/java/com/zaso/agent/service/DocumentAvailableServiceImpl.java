package com.zaso.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.zaso.agent.model.DocumentAvailable;
import com.zaso.agent.repositories.DocumentAvailRepository;

@Repository
public class DocumentAvailableServiceImpl  {
	
	@Autowired
	DocumentAvailRepository docRepo;

	
	public void saveDocumnet(DocumentAvailable docAvailable) {
		// TODO Auto-generated method stub
		docRepo.save(docAvailable);

	}
	public List<DocumentAvailable> findDocument(String docNumber)
	{
		return docRepo.findBydocNumber(docNumber);
	}
	
	public List<DocumentAvailable> findDocument()
	{
		return docRepo.findAll();
	}

}
