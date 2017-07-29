package com.zaso.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.zaso.agent.model.LocalityWithin;


import com.zaso.agent.repositories.LocalityWithinRepository;

@Repository
public class LocalityWithinService {
	
	@Autowired
	LocalityWithinRepository localRepo;
	
	public List<LocalityWithin> allLocalityWithin()
	{
		return localRepo.findAll();
	}
	
	
	public void saveLocallityWithin(LocalityWithin locality)
	{
		localRepo.insert(locality);
	}

}