package com.zaso.agent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.TotalRating;
import com.zaso.agent.repositories.TotalRatingRepository;

@Repository
public class TotalRatingService {
	
	@Autowired
	TotalRatingRepository totalRepo;
	
	
	public void saveTotalRating(TotalRating total)
	{
		totalRepo.insert(total);
	}
	
	public List<TotalRating> getAllRating()
	{
		return totalRepo.findAll();
	}
	
	public TotalRating findByEmail(String email)
	{
		TotalRating total=new TotalRating();
		List<TotalRating> list=totalRepo.findByagentEmail(email);
		for(int i=0;i<list.size();i++)
		{
			TotalRating rating=list.get(i);
			total.setAgentEmail(email);
			total.setRating(rating.getRating());
			total.setTotalUser(rating.getTotalUser());
			break;
			
		}
		return total;
		
		
	}

}
