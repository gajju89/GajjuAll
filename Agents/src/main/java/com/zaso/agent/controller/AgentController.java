package com.zaso.agent.controller;


import java.util.List;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zaso.agent.model.Agents;
import com.zaso.agent.service.AgentServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/agent")
public class AgentController {
	@Autowired
	AgentServiceImpl ages;
	
	
	private static final Logger logger = LoggerFactory.getLogger(AgentController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public @ResponseBody List<Agents> getAll()
	{
		return ages.list();
	}
	/*@RequestMapping(value="/getAll", method=RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody JSONArray getAll()
	{
		List<Agents> agent=ages.list();
		Map<String,List<Agents>> map1=new HashMap<String,List<Agents>>();
		map1.put("agent1", agent);
		JSONObject jObj=new JSONObject(map1);
		//jObj.put("agent1",jObj);
		// JSONArray jArray = new JSONArray(agent);
		 JSONArray jArray = new JSONArray(jObj);
		// return jArray.put(map1);
		 return jArray;
	}
	*/
	@RequestMapping(value="/entry", method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody void saveOrUpdate(@RequestBody Agents agent)
	{  
		 ages.saveOrUpdate(agent);
		 
		 
	}
	@RequestMapping(value="/delete/{emailid}", method=RequestMethod.GET)
	public @ResponseBody void DeleteAgent(@PathVariable String emailid)
	{
		ages.DeleteAgent(emailid);
	}
}
