package com.zaso.agent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zaso.agent.model.TotalRating;
import com.zaso.agent.service.TotalRatingService;

@Controller
@RequestMapping(value="/totalRating")
public class TotalRatingController {
	
	@Autowired
	TotalRatingService totalService;
	
	
	@RequestMapping(value="/getAll/",method=RequestMethod.GET)
	public @ResponseBody List<TotalRating> getAll()
	{
		return totalService.getAllRating();
		
	}

	@RequestMapping(value="/save",method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody void saveAllRating(TotalRating total)
	{
		totalService.saveTotalRating(total);
	}
	@RequestMapping(value="/getAll/{agentemail}/",method=RequestMethod.GET)
	public @ResponseBody TotalRating getAll(@PathVariable("agentemail")String email)
	{
		return totalService.findByEmail(email);
		
	}

}
