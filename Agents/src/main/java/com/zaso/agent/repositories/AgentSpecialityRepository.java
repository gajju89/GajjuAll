package com.zaso.agent.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.AgentSpecialities;

@Repository
public interface AgentSpecialityRepository extends MongoRepository<AgentSpecialities,String> {
	
	AgentSpecialities insert(AgentSpecialities agentSpecialities);
	
	List<AgentSpecialities> findAll();
	
	List<AgentSpecialities> findByEmail(String email);

}
