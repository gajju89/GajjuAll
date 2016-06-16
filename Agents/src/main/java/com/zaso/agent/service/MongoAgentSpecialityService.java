package com.zaso.agent.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.AgentSpecialities;
import com.zaso.agent.model.AgentWithLowerPrice;
import com.zaso.agent.model.AgentWithSpeciality;
import com.zaso.agent.model.Cordinate;
import com.zaso.agent.model.Favourite;
import com.zaso.agent.model.LocalityWithin;
import com.zaso.agent.model.MongoAgent;
import com.zaso.agent.repositories.AgentRepository;
import com.zaso.agent.repositories.AgentSpecialityRepository;
import com.zaso.agent.repositories.FavouriteRepository;
import com.zaso.agent.repositories.LocalityWithinRepository;

@Repository
public class MongoAgentSpecialityService {
	
	@Autowired
	LocalityWithinRepository locWithin;
	
	@Autowired
	FavouriteRepository favRepo;
	
	@Autowired
	AgentRepository agentrepo;
	
	@Autowired
	AgentSpecialityRepository specRepo;
	
	//HashMap<String,HashMap<String ,AgentWithLowerPrice>> lowerPriceMapOuter=new HashMap<String,HashMap<String ,AgentWithLowerPrice>>();
	
	
	
	public List<AgentWithSpeciality> getAll(Double lon,Double lat,Double maxdist)
	{
		List<AgentWithSpeciality> agentListComplete=new ArrayList<AgentWithSpeciality>();
		
		List<AgentWithSpeciality> agentList=new ArrayList<AgentWithSpeciality>();
		Point location = new Point(lon,lat);
		//location.setLocation(lon,lat);
		Distance distance = new Distance(maxdist, Metrics.KILOMETERS);
		//List<AgentWithSpeciality> agentList=new ArrayList<AgentWithSpeciality>();
		List<MongoAgent> agent=agentrepo.findByLocationNear(location, distance);
		/*List<MongoAgent> agent=agentrepo.findAll();*/
		List<AgentSpecialities> listSpec=null;
		//List<Favourite> listFav=null;
		
		listSpec=specRepo.findAll();
		
		Iterator<MongoAgent> iterator=agent.iterator();
		while(iterator.hasNext())
		{
			
			MongoAgent age=iterator.next();	
			listSpec=specRepo.findByEmail(age.getEmailid());
			AgentWithSpeciality agentWithSpeciality=new AgentWithSpeciality();
			agentWithSpeciality.setAddress(age.getAddress());
			agentWithSpeciality.setEmailid(age.getEmailid());
			agentWithSpeciality.setfName(age.getfName());
			agentWithSpeciality.setList(listSpec);
			agentWithSpeciality.setlName(age.getlName());
			agentWithSpeciality.setObjectid(age.getObjectid());
			agentWithSpeciality.setMobileNumber(age.getMobileNumber());
			agentWithSpeciality.setLocation(age.getLocation());
			agentWithSpeciality.setComplete(age.isComplete());
			agentList.add(agentWithSpeciality);
		}
		/*for(int i=0;i<agent.size();i++)
		{
			MongoAgent age=agent.get(i);
			listFav=favRepo.
			listSpec=specRepo.findByEmail(age.getEmailid());
			AgentWithSpeciality agentWithSpeciality=new AgentWithSpeciality();
			agentWithSpeciality.setAddress(age.getAddress());
			agentWithSpeciality.setEmailid(age.getEmailid());
			agentWithSpeciality.setfName(age.getfName());
			agentWithSpeciality.setList(listSpec);
			agentWithSpeciality.setlName(age.getlName());
			agentWithSpeciality.setObjectid(age.getObjectid());
			agentWithSpeciality.setMobileNumber(age.getMobileNumber());
			agentWithSpeciality.setLocation(age.getLocation());
			agentList.add(agentWithSpeciality);
		}*/
		Iterator<AgentWithSpeciality> iter=agentList.iterator();
		while(iter.hasNext())
		{
			AgentWithSpeciality newList=iter.next();
			if(newList.isComplete())
			{
				agentListComplete.add(newList);
			}
		}
		
		
		return agentListComplete;
	}
	
	public  HashMap<String,HashMap<String ,AgentWithLowerPrice>> getByLocation(Double lon,Double lat,Double maxdist)
	{
		//String lowerCommission=null;
		
		//HashMap<String,HashMap<String ,AgentWithLowerPrice>> lowerPriceMapOuter1=new HashMap<String,HashMap<String ,AgentWithLowerPrice>>();
		List<AgentWithSpeciality> agentListComplete=new ArrayList<AgentWithSpeciality>();
		Point location = new Point(lon,lat);
		//location.setLocation(lon,lat);
		Distance distance = new Distance(maxdist, Metrics.KILOMETERS);
		List<AgentWithSpeciality> agentList=new ArrayList<AgentWithSpeciality>();
		List<MongoAgent> agent=agentrepo.findByLocationNear(location, distance);
		List<AgentSpecialities> listSpec=null;
		List<AgentSpecialities> listSpec2=new ArrayList<AgentSpecialities>();
		AgentSpecialities listSpec1=null;
		//listSpec=specRepo.findAll();
		
		Iterator<MongoAgent> iterator=agent.iterator();
		HashMap<String,HashMap<String ,AgentWithLowerPrice>> lowerPriceMapOuter=new HashMap<String,HashMap<String ,AgentWithLowerPrice>>();
		while(iterator.hasNext())
		{
			
			MongoAgent age=iterator.next();	
			listSpec=specRepo.findByEmail(age.getEmailid());
			
			for(int i=0;i<listSpec.size();i++)
			{
				listSpec1=listSpec.get(i);
				listSpec2.add(listSpec1);
			}
			
			AgentWithSpeciality agentWithSpeciality=new AgentWithSpeciality();
			agentWithSpeciality.setAddress(age.getAddress());
			agentWithSpeciality.setEmailid(age.getEmailid());
			agentWithSpeciality.setfName(age.getfName());
			agentWithSpeciality.setList(listSpec);
			agentWithSpeciality.setlName(age.getlName());
			agentWithSpeciality.setObjectid(age.getObjectid());
			agentWithSpeciality.setMobileNumber(age.getMobileNumber());
			agentWithSpeciality.setLocation(age.getLocation());
			agentWithSpeciality.setComplete(age.isComplete());
			agentList.add(agentWithSpeciality);
	
		}
		Iterator<AgentWithSpeciality> iter=agentList.iterator();
		while(iter.hasNext())
		{
			AgentWithSpeciality newList=iter.next();
			if(newList.isComplete())
			{
				agentListComplete.add(newList);
			}
		}
		
		/*return agentListComplete;
		
		List<AgentWithLowerPrice> listLower;
		for(int i=0;i<agentListComplete.size();i++){
			
		}*/
		
		return	lowerPriceMapOuter=clubAgentSpeciality(listSpec2);
		
		// lowerPriceMapOuter
		
	}
	
	public List<AgentWithSpeciality> getByNearPolygon(String id)
	{
		List<AgentWithSpeciality> agentListComplete=new ArrayList<AgentWithSpeciality>();
	
		//List<Point> cordinates=within.getCordinateWithin();
		List<Point> cordinates=null;
		List<LocalityWithin> listLocWithin=locWithin.findBylocalityId(id);
		if(listLocWithin!=null)
		{
		 cordinates=listLocWithin.get(0).getCordinateWithin();
		}
		
		List<AgentWithSpeciality> agentList=new ArrayList<AgentWithSpeciality>();
		Polygon polygon=new Polygon(cordinates);
		List<MongoAgent> agent=agentrepo.findByLocationWithin(polygon);
		
		List<AgentSpecialities> listSpec;
		listSpec=specRepo.findAll();
		
		Iterator<MongoAgent> iterator=agent.iterator();
		while(iterator.hasNext())
		{
			
			MongoAgent age=iterator.next();	
			listSpec=specRepo.findByEmail(age.getEmailid());
			AgentWithSpeciality agentWithSpeciality=new AgentWithSpeciality();
			agentWithSpeciality.setAddress(age.getAddress());
			agentWithSpeciality.setEmailid(age.getEmailid());
			agentWithSpeciality.setfName(age.getfName());
			agentWithSpeciality.setList(listSpec);
			agentWithSpeciality.setlName(age.getlName());
			agentWithSpeciality.setObjectid(age.getObjectid());
			agentWithSpeciality.setMobileNumber(age.getMobileNumber());
			agentWithSpeciality.setLocation(age.getLocation());
			agentWithSpeciality.setComplete(age.isComplete());
			agentList.add(agentWithSpeciality);
		}
		
		Iterator<AgentWithSpeciality> iter=agentList.iterator();
		while(iter.hasNext())
		{
			AgentWithSpeciality newList=iter.next();
			if(newList.isComplete())
			{
				agentListComplete.add(newList);
			}
		}
		
		
		return agentListComplete;
		
	}
	
	public HashMap<String,HashMap<String ,AgentWithLowerPrice>> clubAgentSpeciality(List<AgentSpecialities> speciality)
	{
		HashMap<String,HashMap<String ,AgentWithLowerPrice>> lowerPriceSpecialityMapOuter=new HashMap<String,HashMap<String ,AgentWithLowerPrice>>();
		//HashMap<String ,AgentWithLowerPrice> lowerMap=new HashMap<String, AgentWithLowerPrice>();
		//HashMap<String ,AgentWithLowerPrice> lowerMap=new HashMap<String, AgentWithLowerPrice>();
		for(int i=0;i<speciality.size();i++)
		{
		
			AgentWithLowerPrice agentLower=new AgentWithLowerPrice();
			
			if(lowerPriceSpecialityMapOuter.containsKey(speciality.get(i).getName()))
			{
				HashMap<String ,AgentWithLowerPrice> specialityMapInner= lowerPriceSpecialityMapOuter.get(speciality.get(i).getName());
				if(specialityMapInner.containsKey(speciality.get(i).getType()))
				{
				agentLower=lowerPriceSpecialityMapOuter.get(speciality.get(i).getName()).get(speciality.get(i).getType());
				String specialityCommission=null,agentLowerCommission=null,specialityCommissionAfter=null,agentLowerCommissionAfter=null;
				specialityCommission=speciality.get(i).getOurPrice().replaceAll(",", "");
				agentLowerCommission=agentLower.getCommission().replaceAll(",","");
				if(specialityCommission!="")
				{
					continue ;
					}
				else
				{
				Pattern p = Pattern.compile("[0-9]+");
				Matcher m1=p.matcher(specialityCommission);
				Matcher m2=p.matcher(agentLowerCommission);
				int start = 0,end = 0,start1 = 0,end1 = 0;
				
				while(m1.find())
				{
					 start=m1.start();
					 end=m1.end();
					 break;
					 
					}
				specialityCommissionAfter=specialityCommission.substring(start, end);
				while(m2.find())
				{
					 start1=m2.start();
					 end1=m2.end();
					 break;
					}
				agentLowerCommissionAfter=agentLowerCommission.substring(start1, end1);
				
				
				
				//if(Integer.parseInt((speciality.get(i).getCommission().toString().replaceAll("[^0-9.]+", ""))) <= Integer.parseInt((agentLower.getCommission().toString().replaceAll("[^0-9.]+", ""))))
				int specialityCommissionAfterInt= Integer.parseInt(specialityCommissionAfter);
				int agentLowerCommisionAfterInt = Integer.parseInt(agentLowerCommissionAfter);
				if( specialityCommissionAfterInt<=agentLowerCommisionAfterInt)
				{
					AgentWithLowerPrice agentLower1=new AgentWithLowerPrice();
					agentLower1.setEmailid(speciality.get(i).getEmail());
					agentLower1.setCommission(speciality.get(i).getOurPrice());
					specialityMapInner.put(speciality.get(i).getType(),agentLower1);
					//lowerPriceMapOuter1.put(speciality.get(i).getName(),lowerMap);
					
				}
				}
				}else
				{
					
					agentLower.setEmailid(speciality.get(i).getEmail());
					agentLower.setCommission(speciality.get(i).getOurPrice());
					specialityMapInner.put(speciality.get(i).getType(),agentLower);
					
				}
			}else
			{
				HashMap<String ,AgentWithLowerPrice> lowerPriceSpecialityMapInner=new HashMap<String, AgentWithLowerPrice>();		
				//HashMap<String ,AgentWithLowerPrice> lowerMap1=new HashMap<String, AgentWithLowerPrice>();
				agentLower.setEmailid(speciality.get(i).getEmail());
				agentLower.setCommission(speciality.get(i).getOurPrice());
				lowerPriceSpecialityMapInner.put(speciality.get(i).getType(),agentLower);
				lowerPriceSpecialityMapOuter.put(speciality.get(i).getName(),lowerPriceSpecialityMapInner);
				
				
		}
//			lowerPriceMapOuter1.put(speciality.get(i).getName(),lowerMap);
			
		}
		return lowerPriceSpecialityMapOuter;
	
		
	}
	
	public  List<AgentWithSpeciality> getByLocationUpdate(Double lon,Double lat,Double maxdist)
	{
		
		List<AgentWithSpeciality> agentListComplete=new ArrayList<AgentWithSpeciality>();
		Point location = new Point(lon,lat);
		//location.setLocation(lon,lat);
		Distance distance = new Distance(maxdist, Metrics.KILOMETERS);
		List<AgentWithSpeciality> agentList=new ArrayList<AgentWithSpeciality>();
		List<MongoAgent> agent=agentrepo.findByLocationNear(location, distance);
		List<AgentSpecialities> listSpec=null;
		List<AgentSpecialities> listSpec2=new ArrayList<AgentSpecialities>();
		AgentSpecialities listSpec1=null;
		listSpec=specRepo.findAll();
		
		Iterator<MongoAgent> iterator=agent.iterator();
		while(iterator.hasNext())
		{
			
			MongoAgent age=iterator.next();	
			listSpec=specRepo.findByEmail(age.getEmailid());
			
			for(int i=0;i<listSpec.size();i++)
			{
				listSpec1=listSpec.get(i);
				listSpec2.add(listSpec1);
			}
			
			AgentWithSpeciality agentWithSpeciality=new AgentWithSpeciality();
			agentWithSpeciality.setAddress(age.getAddress());
			agentWithSpeciality.setEmailid(age.getEmailid());
			agentWithSpeciality.setfName(age.getfName());
			agentWithSpeciality.setList(listSpec);
			agentWithSpeciality.setlName(age.getlName());
			agentWithSpeciality.setObjectid(age.getObjectid());
			agentWithSpeciality.setMobileNumber(age.getMobileNumber());
			agentWithSpeciality.setLocation(age.getLocation());
			agentWithSpeciality.setComplete(age.isComplete());
			agentList.add(agentWithSpeciality);
	
		}
		Iterator<AgentWithSpeciality> iter=agentList.iterator();
		while(iter.hasNext())
		{
			AgentWithSpeciality newList=iter.next();
			if(newList.isComplete())
			{
				agentListComplete.add(newList);
			}
		}
		
		return agentListComplete;
		
		
		
	}

}
