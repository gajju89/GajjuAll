package com.zaso.agent.repositories;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.AgentPic;


@Repository
public interface AgentPicRepository extends MongoRepository<AgentPic,String> {
	
	AgentPic insert(AgentPic agentpic);
	
	List<AgentPic> findByemailId(String email);

}
