package com.zaso.agent.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.Locality;
import com.zaso.agent.model.LocalityWithPolygon;
import com.zaso.agent.model.LocalityWithin;
import com.zaso.agent.repositories.LocalityRepository;

@Repository
public class LocalityService {
	
	@Autowired
	MailSenderService mailServ;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	MongoOperations operations ;
	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
		operations = (MongoOperations) mongoTemplate;
		
	}
	
	@Autowired
	LocalityRepository localRepo;
	
	public List<LocalityWithPolygon> allLocality()
	{
		List<LocalityWithPolygon> listPoly=new ArrayList<LocalityWithPolygon>();
		List<Locality> listLoc=localRepo.findAll();
		for(int i=0;i<listLoc.size();i++)
		{
			
			Locality loc=listLoc.get(i);
			Query query=new Query();
			query.addCriteria(Criteria.where("localityId").is(loc.getLocalityId()));
			LocalityWithin locWithin=operations.findOne(query,LocalityWithin.class);
			LocalityWithPolygon locPoly=new LocalityWithPolygon();
			locPoly.setCordinate(loc.getCordinate());
			locPoly.setCordinateWithin(locWithin.getCordinateWithin());
			locPoly.setLocalityId(loc.getLocalityId());
			locPoly.setLocalityName(loc.getLocalityName());
			listPoly.add(locPoly);
			
		}
		return listPoly;
	}
	
	public List<Locality> findlocalityById(String id)
	{
		return localRepo.findAll();
	}
	public void saveLocallity(Locality locality)
	{
		localRepo.insert(locality);
	}

}
