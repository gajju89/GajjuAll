package com.zaso.agent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zaso.agent.model.RatingByUser;
import com.zaso.agent.model.RatingsWithFeedback;
import com.zaso.agent.service.UserByRatingService;


@Controller
@RequestMapping(value="/ratingByUser")
public class RatingByUserController {
	
	@Autowired
	UserByRatingService ratingService;
	
	@RequestMapping(value="/getAll",method=RequestMethod.GET)
	public @ResponseBody List<RatingByUser> getAll()
	{
		return ratingService.getAll();
	}
	
	@RequestMapping(value="/saveRating/{agentemail}/{tocken}",method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody void saveRating(@PathVariable("agentemail")String email,@RequestBody RatingsWithFeedback ratings,@PathVariable("tocken")String tocken)
	{
		ratingService.save(email,ratings,tocken);
	}

}
