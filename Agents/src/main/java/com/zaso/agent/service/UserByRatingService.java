package com.zaso.agent.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.RatingByUser;
import com.zaso.agent.model.RatingsWithFeedback;
import com.zaso.agent.model.TotalRating;
import com.zaso.agent.model.UserAuthentication;
import com.zaso.agent.repositories.RatingByUserRepository;
import com.zaso.agent.repositories.TotalRatingRepository;
import com.zaso.agent.repositories.UserAuthenticationRepository;

@Repository
public class UserByRatingService {
	
	
	
	@Autowired
	UserAuthenticationRepository mAuthRepo;
	

	@Autowired
	RatingByUserRepository userRepo;
	
	@Autowired
	TotalRatingRepository totalRepo;
	
	public void save(String agentemail,RatingsWithFeedback ratings,String tocken)
	{
		List<RatingByUser> listUser=userRepo.findByagentEmail(agentemail);
		if(listUser.size()==0)
		{
			insertRating(agentemail,ratings, tocken);
		}
		else
		{
			userRepo.deleteByagentEmail(agentemail);
			totalRepo.deleteByagentEmail(agentemail);
			insertRating(agentemail,ratings, tocken);
		}
		
		
	}
	
	public List<RatingByUser> getAll()
	{
		return userRepo.findAll();
	}
	
	public List<RatingByUser> findByEmail(String email)
	{
		return userRepo.findByagentEmail(email);
	}
	
	
	
	public void insertRating(String agentemail,RatingsWithFeedback rating,String tocken)
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
		userEmail=user.getEmailId();
	
		RatingByUser userRating=new RatingByUser();
		userRating.setAgentEmail(agentemail);
		userRating.setRating(Float.parseFloat(rating.getRating()));
		userRating.setMessage(rating.getMessage());
		userRating.setUserEmail(userEmail);
		userRepo.insert(userRating);
		
		
		List<RatingByUser> list=findByEmail(agentemail);
		float totalRating=0;
		for(int i=0;i<list.size();i++)
		{
			float rating1=list.get(i).getRating();
			totalRating+=rating1;
		}
		float finalRating=totalRating/list.size();
		TotalRating average=new TotalRating();
		average.setAgentEmail(agentemail);
		average.setRating(finalRating);
		average.setTotalUser(list.size());
		totalRepo.insert(average);
	}
}
