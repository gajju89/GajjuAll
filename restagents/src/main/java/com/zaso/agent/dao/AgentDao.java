package com.zaso.agent.dao;

import java.util.List;


import com.zaso.agent.model.Agents;
import com.zaso.agent.model.Category;

public interface AgentDao {
	

    public void saveOrUpdate(Agents agent);
    
    public void DeleteAgent(String emailid);
     
    public List<Agents> list();

}
