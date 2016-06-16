package com.zaso.agent.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.AgentPic;
import com.zaso.agent.model.AgentSpecialities;
import com.zaso.agent.model.Favourite;
import com.zaso.agent.model.FavouriteModel;
import com.zaso.agent.model.FavouriteModelAllData;
import com.zaso.agent.model.MongoAgent;
import com.zaso.agent.model.Position;
import com.zaso.agent.model.UserAuthentication;
import com.zaso.agent.repositories.AgentPicRepository;
import com.zaso.agent.repositories.AgentRepository;
import com.zaso.agent.repositories.AgentSpecialityRepository;
import com.zaso.agent.repositories.FavouriteRepository;
import com.zaso.agent.repositories.UserAuthenticationRepository;

@Repository
public class FavouriteServiceImpl  {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	MongoOperations operations ;
	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
		operations = (MongoOperations) mongoTemplate;
		
	}
	
	
	@Autowired
	AgentPicServiceImpl servicePic;
	
	@Autowired
	AgentPicRepository picRepo;
	
	@Autowired
	FavouriteRepository favRepo;
	
	@Autowired
	AgentRepository agentRepo;
	
	@Autowired
	AgentSpecialityRepository specRepo;
	
	@Autowired
	UserAuthenticationRepository mAuthRepo;


	public void saveFavourite(String email,String accessToken,boolean state) {
		// TODO Auto-generated method stub
		String useremail=null;
		Favourite fav=new Favourite();
		/*List<UserAuthentication> user=mAuthRepo.findByAuthCode(accessToken);
		Iterator<UserAuthentication> itr=user.iterator();
		while(itr.hasNext())
		{
			UserAuthentication user1=itr.next();
			if(user1.getAuthCode().equals(accessToken))
			{
			 useremail=user1.getEmailId();
			}
		}*/
		UserAuthentication user=mAuthRepo.findByAuthCode(accessToken);
		useremail=user.getEmailId();
		
		fav.setAgentemail(email);
		fav.setUseremail(useremail);
		fav.setState(state);
		favRepo.insert(fav);

	}

	
	public HashSet<FavouriteModelAllData> getAllFav(String accessToken) {
		// TODO Auto-generated method stub
		String userEmail=null;
		/*List<UserAuthentication> user=mAuthRepo.findByAuthCode(accessToken);
		Iterator<UserAuthentication> itr=user.iterator();
		while(itr.hasNext())
		{
			UserAuthentication user1=itr.next();
			if(user1.getAuthCode().equals(accessToken))
			{
			 userEmail=user1.getEmailId();
			}
		}*/
		UserAuthentication user=mAuthRepo.findByAuthCode(accessToken);
		userEmail=user.getEmailId();
		
		HashSet<FavouriteModelAllData> favAgentInfo=new HashSet<FavouriteModelAllData>();
		//UserAuthentication auth=mAuthRepo.findByAuthCode(accessToken);
		// userEmail=auth.getEmailId();
		List<Favourite> list1=favRepo.findByUseremail(userEmail);
		MongoAgent agentInfo=new MongoAgent();
		 
		List<MongoAgent> listAgent=agentRepo.findAll();
		List<AgentSpecialities> spec=specRepo.findAll();
		
		//AgentPicServiceImpl servicePic=new AgentPicServiceImpl();
		
		for(int i=0;i<list1.size();i++)
		{
			FavouriteModelAllData favModel=new FavouriteModelAllData();
			Position position=new Position();
			MongoAgent agent=agentRepo.findByEmailid(list1.get(i).getAgentemail());
			List<String> urls=servicePic.list(list1.get(i).getAgentemail());
			List<AgentSpecialities> spec1=specRepo.findByEmail(list1.get(i).getAgentemail());
			Position loc=agent.getLocation();
			favModel.setAddress(agent.getAddress());
			favModel.setEmailid(agent.getEmailid());
			favModel.setfName(agent.getfName());
			favModel.setlName(agent.getlName());
			favModel.setIsfavourite(list1.get(i).isState());	
			favModel.setLocation(loc);
			favModel.setMobileNumber(agent.getMobileNumber());
			favModel.setPicurl(urls);
			favModel.setList(spec1);
			favModel.setComplete(agent.isComplete());
			favAgentInfo.add(favModel);
		}
		return favAgentInfo;
		
	}

	
	public void deleteFav(String email,String tocken,boolean state) {
		// TODO Auto-generated method stub
		UserAuthentication auth=mAuthRepo.findByAuthCode(tocken);
		MongoOperations operations = (MongoOperations) mongoTemplate;
		Query searchUserQuery = new Query();
		searchUserQuery.addCriteria(Criteria.where("agentemail").is(email));
		searchUserQuery.addCriteria(Criteria.where("useremail").is(auth.getEmailId()));
		operations.remove(searchUserQuery, Favourite.class);
		
		/*boolean state1=true;
		UserAuthentication auth=mAuthRepo.findByAuthCode(tocken);
		Favourite favourite=new Favourite();
		favourite.setAgentemail(email);
		favourite.setState(state1);
		favourite.setUseremail(auth.getEmailId());
		favRepo.delete(favourite);*/
	}
	
	public List<Favourite> getFavouriteByEmail(String tocken)
	{
		String userEmail=null;
		/*List<UserAuthentication> user=mAuthRepo.findByAuthCode(tocken);
		Iterator<UserAuthentication> itr=user.iterator();
		while(itr.hasNext())
		{
			UserAuthentication user1=itr.next();
			if(user1.getAuthCode().equals(tocken))
			{
			 userEmail=user1.getEmailId();
			}
		}*/
		UserAuthentication user=mAuthRepo.findByAuthCode(tocken);
		
		List<Favourite> list1=favRepo.findByUseremail(user.getEmailId());
		
		return list1;
		
	}


	public void saveFavouriteFromList(String email,List<FavouriteModelAllData> favouriteModelAllData) {
		// TODO Auto-generated method stub
		Query query=new Query(Criteria.where("useremail").is(email));
		operations.remove(query,Favourite.class);
		
		Iterator<FavouriteModelAllData> iter=favouriteModelAllData.iterator();
		while(iter.hasNext())
		{
			FavouriteModelAllData favourite =iter.next();
			Favourite fav=new Favourite();
			fav.setAgentemail(favourite.getEmailid());
			fav.setUseremail(email);
			fav.setState(favourite.isIsfavourite());
			operations.save(fav);
					
		}
		
		
		
	}
	
	

}

