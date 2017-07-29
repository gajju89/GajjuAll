package com.zaso.mongo.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.zaso.mongo.model.MongoAgent;
//import com.zaso.mongo.repositories.AgentRepository;
import com.zaso.mongo.repositories.AgentRepository;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	AgentRepository agentrepo;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/*
	@Autowired
	 private MongoOperations mongoOperations;
	*/
	/*
	private static final String START = "location";
	
	 public static final Double KILOMETER = 111.0d;*/
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
	
		
		
		@RequestMapping(value="/All",method=RequestMethod.GET)
		public @ResponseBody List<MongoAgent> getAll()
		{
			//return mongoOperations.findAll(MongoAgent.class);
			return agentrepo.findAll();
		}

		@RequestMapping(value="/insert",method=RequestMethod.POST, headers="Accept=application/json")
		public @ResponseBody void saveAll(@RequestBody MongoAgent agent)
		{
			//mongoOperations.save(agent);
			agentrepo.save(agent);
		}
		
		@RequestMapping(value="/getbylocation/{lon}/{lat}/{maxdistance}",method=RequestMethod.GET)
		public @ResponseBody List<MongoAgent> getByLocation(@PathVariable("lon") Double lon,
        @PathVariable("lat") Double lat,
        @PathVariable("maxdistance") Double maxdist)
		{
			Point location = new Point(lon,lat);
			//location.setLocation(lon,lat);
			Distance distance = new Distance(maxdist, Metrics.KILOMETERS);
			return agentrepo.findByLocationNear(location, distance);
		}
		
	/*	@RequestMapping(value = "/getbylocation/{lon}/{lat}/{maxdistance}",
	            method = RequestMethod.GET, produces = "application/json")
	    public
	    @ResponseBody
	    List<MongoAgent> getByLocation(@PathVariable("lon") Double lon,
	                              @PathVariable("lat") Double lat,
	                              @PathVariable("maxdistance") Double maxdistance)
	            throws Exception {
	        Criteria criteria = new Criteria(START).near(new Point(lon, lat)).
	                maxDistance(getInKilometer(maxdistance));
	        List<MongoAgent> mongo = mongoOperations.find(new Query(criteria),
	                MongoAgent.class);
	        return mongo;
	    }
		
		
		
		 private Double getInKilometer(Double maxdistance) {
		        return maxdistance / KILOMETER;
		    }
		*/



/*@RequestMapping(value="/getbylocation/{lat}/{lon}/{maxdistance}",method=RequestMethod.GET)
public @ResponseBody List<MongoAgent> getByLocation(@PathVariable("lat") Double lat,
@PathVariable("lon") Double lon,
@PathVariable("maxdistance") Double maxdist)
{
	Point location = new Point(lat,lon);
	//location.setLocation(lon,lat);
	Distance distance = new Distance(maxdist, Metrics.KILOMETERS);
	return agentrepo.findByLocationNear(location, distance);
}*/
}