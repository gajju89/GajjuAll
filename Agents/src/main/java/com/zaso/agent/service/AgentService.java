package com.zaso.agent.service;

import java.util.List;

import com.zaso.agent.model.Agents;

public interface AgentService {

	
	 public void saveOrUpdate(Agents agent);
	 
	 public void DeleteAgent(String emailid);
     
	    public List<Agents> list();
}
