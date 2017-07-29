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

import com.zaso.agent.service.LocalityService;
import com.zaso.agent.model.*;


@Controller
@RequestMapping(value="/locality")
public class LocalityController {
	
	@Autowired
	LocalityService locService;
	
	@RequestMapping(value="/getAll",method=RequestMethod.GET)
	public @ResponseBody List<LocalityWithPolygon> getAllLoc()
	{
		return locService.allLocality();
	}
	
	@RequestMapping(value="/getAll/{id}",method=RequestMethod.GET)
	public @ResponseBody List<Locality> getById(@PathVariable("id")String id)
	{
		return locService.findlocalityById(id);
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody void saveLocality(@RequestBody Locality locality)
	{
		locService.saveLocallity(locality);
	}

}
