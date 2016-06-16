package com.zaso.agent.service;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import org.springframework.stereotype.Repository;

import com.zaso.agent.model.AgentSpecialities;
import com.zaso.agent.model.Agents;
import com.zaso.agent.model.LastMongoId;
import com.zaso.agent.model.MongoAgent;
import com.zaso.agent.model.Position;
import com.zaso.agent.repositories.AgentRepository;
import com.zaso.agent.repositories.AgentSpecialityRepository;

@Repository
public class AgentServiceImpl {
	@Autowired
	AgentRepository agentrepo;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	AgentSpecialityRepository specRepo;

	public void saveOrUpdate(Agents agent) {
		LastMongoId last = new LastMongoId();
		// agent.setObjectid(GeneralUtil.getLastUserId());
		// TODO Auto-generated method stub
		// age.saveOrUpdate(agent );

		// agent.setObjectid(String.valueOf(last.getId()));

		// String id=GeneralUtil.getLastUserId();
		MongoAgent mongoAgent = new MongoAgent();
		mongoAgent.setAddress(agent.getAddress());
		MongoOperations operations = (MongoOperations) mongoTemplate;
		Query searchUserQuery = new Query();
		LastMongoId init = operations.findOne(searchUserQuery, LastMongoId.class);
		if (init == null) {
			init = new LastMongoId();
			init.setLastid(1l);
			operations.insert(init);
		} else {
			init.setLastid(init.getLastid() + 1l);
			operations.updateFirst(searchUserQuery, Update.update("lastid", init.getLastid()), LastMongoId.class);
		}

		Position position = new Position();
		position.setLat(Double.parseDouble(agent.getLattitude()));
		position.setLon(Double.parseDouble(agent.getLongitude()));

		mongoAgent.setLocation(position);
		mongoAgent.setEmailid(agent.getEmail());
		mongoAgent.setfName(agent.getFname());
		mongoAgent.setlName(agent.getLname());
		mongoAgent.setMobileNumber(agent.getMobile());
		mongoAgent.setObjectid(String.valueOf(init.getLastid()));
		// mongoAgent.setSpeciality(agent.getSpeciality());
		agentrepo.insert(mongoAgent);
	}

	public void DeleteAgent(String emailid) {
		agentrepo.deleteByEmailid(emailid);
	}

	public List<MongoAgent> list() {
		// TODO Auto-generated method stub
		return agentrepo.findAll();
	}

	public void saveSpeciality(AgentSpecialities speciality) {
		specRepo.insert(speciality);
	}

	public List<AgentSpecialities> getAllSpeciality() {
		return specRepo.findAll();
	}

	/*
	 * public List<String> getSpecialityList(String email) { List<String>
	 * specialities=new ArrayList<String>(); List<AgentSpecialities>
	 * listSpec=specRepo.findByEmail(email); Iterator<AgentSpecialities>
	 * iterator=listSpec.iterator(); while(iterator.hasNext()) { String
	 * speciality=iterator.next(). }
	 */
	public MongoAgent findByEmail(String email) {
		return agentrepo.findByEmailid(email);
	}

}
