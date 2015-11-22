package com.zaso.agent.service;

import org.springframework.web.multipart.MultipartFile;

import com.zaso.agent.model.DocumentStorage;

public interface DocumentService {
	
	public void saveDocument(MultipartFile[] file,String userid);

}
