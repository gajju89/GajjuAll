package com.zaso.agent.dao;

import java.util.List;

import com.zaso.agent.model.AgentPic;


public interface AgentPicDao {
	
	 public void saveOrUpdate(AgentPic agepic);
	    
	    /*public void DeleteAgentPic(String url);*/
	     
	    public List<AgentPic> list(String agepic);

}
