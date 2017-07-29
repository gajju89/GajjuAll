package com.zaso.agent.controller;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zaso.agent.model.Favourite;
import com.zaso.agent.model.FavouriteModel;
import com.zaso.agent.model.FavouriteModelAllData;
import com.zaso.agent.service.FavouriteServiceImpl;

@Controller
@RequestMapping("/favourite")
public class FavouriteController {
	@Autowired
	FavouriteServiceImpl favServImpl;
	
	@RequestMapping(value="/save/fav/{accessToken}/{email}/{state}", method=RequestMethod.POST,headers="accept=application/json")
	public @ResponseBody void saveFavourite(@PathVariable("accessToken")String accessToken,@PathVariable("email")String email,@PathVariable("state")boolean state)
	{
		favServImpl.saveFavourite(email,accessToken,state);
	}
	
	@RequestMapping(value="/getAll/fav/{accessToken}", method=RequestMethod.GET)
	public @ResponseBody HashSet<FavouriteModelAllData> getAllFav(@PathVariable("accessToken")String accessToken)
	{
		return favServImpl.getAllFav(accessToken);
	}
	
	

	@RequestMapping(value="/save/fav/updatefromlist/{email}/", method=RequestMethod.POST,headers="accept=application/json")
	public @ResponseBody void saveFavourite(@PathVariable("email") String email,@RequestBody List<FavouriteModelAllData> favouriteModelAllData)
	{
		favServImpl.saveFavouriteFromList(email,favouriteModelAllData);
	}
	@RequestMapping(value="/delete/fav/{email}/{tocken}/{state}", method=RequestMethod.POST)
	public @ResponseBody void deleteFav(@PathVariable("email")String emailid,@PathVariable("tocken")String tocken,@PathVariable("state")boolean state)
	{
		favServImpl.deleteFav(emailid,tocken,state);
	}
	
	@RequestMapping(value="/getFavByEmail/{accessToken}", method=RequestMethod.GET)
	public @ResponseBody List<Favourite> getFavouriteByEmail(@PathVariable("accessToken")String accessToken)
	{
		return favServImpl.getFavouriteByEmail(accessToken);
	}
	
	

}
