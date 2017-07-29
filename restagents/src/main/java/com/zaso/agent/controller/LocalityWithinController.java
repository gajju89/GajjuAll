package com.zaso.agent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zaso.agent.model.LocalityWithin;
import com.zaso.agent.repositories.LocalityWithinRepository;
import com.zaso.agent.service.LocalityWithinService;

@Controller
@RequestMapping(value="/localityWith")
public class LocalityWithinController {
	
	@Autowired
	LocalityWithinService withRepo;
	
	
	@RequestMapping(value="/getAll",method=RequestMethod.GET)
	public @ResponseBody List<LocalityWithin> getAll()
	{
		return withRepo.allLocalityWithin();
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody void saveParameter(@RequestBody LocalityWithin within)
	{
		
		withRepo.saveLocallityWithin(within);
	}

}
