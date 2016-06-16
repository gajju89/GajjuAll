package com.zaso.agent.controller;


	

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
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

import com.zaso.agent.model.AgentWithLowerPrice;
import com.zaso.agent.model.AgentWithSpeciality;
import com.zaso.agent.model.Category;
import com.zaso.agent.model.LocalityWithin;
import com.zaso.agent.model.MongoAgent;
//import com.zaso.mongo.repositories.AgentRepository;
import com.zaso.agent.repositories.AgentRepository;
import com.zaso.agent.service.MongoAgentSpecialityService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/mongo")
public class AgentMongoController {
	@Autowired
	MongoAgentSpecialityService spec;
	
	@Autowired
	AgentRepository agentrepo;
	

	private static final Logger logger = LoggerFactory.getLogger(AgentMongoController.class);

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
	
		
		
		@RequestMapping(value="/All/{lon}/{lat}/{maxdistance}",method=RequestMethod.GET)
		public @ResponseBody byte[] getAll(@PathVariable("lon") Double lon,
		        @PathVariable("lat") Double lat,
		        @PathVariable("maxdistance") Double maxdist)
		{
			//return mongoOperations.findAll(MongoAgent.class);
			List<AgentWithSpeciality> listAgent=spec.getAll(lon, lat, maxdist);
			
			try {
				ObjectMapper mapper=new ObjectMapper();
				String serialised=mapper.writeValueAsString(listAgent);
				
				 Cipher c = Cipher.getInstance("AES");
				   byte[] key = "43381A8E10469C42EFBD6EA0DFD9CA71".getBytes("UTF-8");
				   MessageDigest sha = MessageDigest.getInstance("SHA-1");
				   key = sha.digest(key);
				   key = Arrays.copyOf(key, 16);
					SecretKeySpec k =
					  new SecretKeySpec(key, "AES");
					c.init(Cipher.ENCRYPT_MODE, k);
					 return c.doFinal(serialised.toString().getBytes("UTF-8"));
					//serialised.reset();
					
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		/*@RequestMapping(value="/insert",method=RequestMethod.POST, headers="Accept=application/json")
		public @ResponseBody void saveAll(@RequestBody MongoAgent agent)
		{
			//mongoOperations.save(agent);
			agentrepo.save(agent);
		}*/
		
		@RequestMapping(value="/getbylocation/{lon}/{lat}/{maxdistance}",method=RequestMethod.GET)
		public @ResponseBody byte[] getByLocation(@PathVariable("lon") Double lon,
        @PathVariable("lat") Double lat,
        @PathVariable("maxdistance") Double maxdist)
		{
			//return spec.getByLocation(lon, lat, maxdist);
			
			
				HashMap<String,HashMap<String ,AgentWithLowerPrice>> map=spec.getByLocation(lon, lat, maxdist);
				try {
					ObjectMapper mapper=new ObjectMapper();
					String serialised=mapper.writeValueAsString(map);
					
					 Cipher c = Cipher.getInstance("AES");
					   byte[] key = "43381A8E10469C42EFBD6EA0DFD9CA71".getBytes("UTF-8");
					   MessageDigest sha = MessageDigest.getInstance("SHA-1");
					   key = sha.digest(key);
					   key = Arrays.copyOf(key, 16);
						SecretKeySpec k =
						  new SecretKeySpec(key, "AES");
						c.init(Cipher.ENCRYPT_MODE, k);
						 return c.doFinal(serialised.toString().getBytes("UTF-8"));
						//serialised.reset();
						
				} catch (JsonGenerationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalBlockSizeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BadPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
				
			}
			
		
		
		@RequestMapping(value="/getByPolygon/{localityId}",method=RequestMethod.GET)
		public @ResponseBody byte[] getByPolygon(@PathVariable("localityId") String id)
		{
			//return spec.getByNearPolygon(id);
			List<AgentWithSpeciality> list=spec.getByNearPolygon(id);
			try {
				ObjectMapper mapper=new ObjectMapper();
				String serialised=mapper.writeValueAsString(list);
				
				 Cipher c = Cipher.getInstance("AES");
				   byte[] key = "43381A8E10469C42EFBD6EA0DFD9CA71".getBytes("UTF-8");
				   MessageDigest sha = MessageDigest.getInstance("SHA-1");
				   key = sha.digest(key);
				   key = Arrays.copyOf(key, 16);
					SecretKeySpec k =
					  new SecretKeySpec(key, "AES");
					c.init(Cipher.ENCRYPT_MODE, k);
					 return c.doFinal(serialised.toString().getBytes("UTF-8"));
					//serialised.reset();
					
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
		}
		
		@RequestMapping(value="/getbylocationUpdate/{lon}/{lat}/{maxdistance}",method=RequestMethod.GET)
		public @ResponseBody byte[] getByLocationUpdate(@PathVariable("lon") Double lon,
        @PathVariable("lat") Double lat,
        @PathVariable("maxdistance") Double maxdist)
		{
			List<AgentWithSpeciality> list=spec.getByLocationUpdate(lon, lat, maxdist);
			
			try {
				ObjectMapper mapper=new ObjectMapper();
				String serialised=mapper.writeValueAsString(list);
				
				 Cipher c = Cipher.getInstance("AES");
				   byte[] key = "43381A8E10469C42EFBD6EA0DFD9CA71".getBytes("UTF-8");
				   MessageDigest sha = MessageDigest.getInstance("SHA-1");
				   key = sha.digest(key);
				   key = Arrays.copyOf(key, 16);
					SecretKeySpec k =
					  new SecretKeySpec(key, "AES");
					c.init(Cipher.ENCRYPT_MODE, k);
					 return c.doFinal(serialised.toString().getBytes("UTF-8"));
					//serialised.reset();
					
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
		}
		
		
		/*@RequestMapping(value="/getBySpeciality/{speciality}",method=RequestMethod.GET)
		public @ResponseBody List<MongoAgent> getBySpeciality(@PathVariable("speciality")String speciality){
			return agentrepo.findByTheSpeciality(speciality);
		}*/
		
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


