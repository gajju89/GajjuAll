package com.zaso.agent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.zaso.agent.model.Response;
import com.zaso.agent.model.UserAddressModel;
import com.zaso.agent.model.UserAddressWithAddressIdModel;
import com.zaso.agent.model.UserRegistration;
import com.zaso.agent.model.UserUpdate;
import com.zaso.agent.service.UserRegistrationServiceImpl;
import com.zaso.agent.utils.GeneralUtil;

@Controller
@RequestMapping("/RegisterUser")
public class UserRegistrationController {
	
	@Autowired
	UserRegistrationServiceImpl regService;
	
	
	@RequestMapping(value="/enterInfo", method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody Response saveOrUpdate(@RequestBody UserRegistration userRegistration)
	{  
		
		 
		
		return  regService.saveUser(userRegistration);
		
			
		}
		 
		
	
	
	@RequestMapping(value="/getLoginInfo/{email}/{password}",method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody Response getUserLoginInfo(@PathVariable("email")String email,@PathVariable("password")String passw)
	{
		return regService.getUserLoginInfo(email,passw);
		
	}
	
	@RequestMapping(value="/updatePassword/{password}/{varCode}",method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody boolean updatePass(@PathVariable("password")String password,@PathVariable("varCode")String varCode)
	{
		return regService.updatePassword(password, varCode); 
	}
	

	@RequestMapping(value="/updateprofile", method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody boolean updateProfile(@RequestBody UserUpdate userRegistration)
	{  
		
		 
		
		 return  regService.updateProfile(userRegistration);
		
			
		}
	
	@RequestMapping(value="/getUserProfile/{userEmail}/", method=RequestMethod.GET)
	public @ResponseBody UserRegistration getUserProfile(@PathVariable("userEmail")String userEmail)
	{  
		
		 
		
		  return regService.getProfile(userEmail);
		
			
		}
	
	@RequestMapping(value="/save/userAddress", method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody String updateProfile(@RequestBody UserAddressModel userAddressModel)
	{  
		
		 
		
		 return  regService.saveUserAddress(userAddressModel);
		
			
		}

	@RequestMapping(value="/find/all/useraddress/{emailid}/", method=RequestMethod.GET)
	public @ResponseBody List<UserAddressWithAddressIdModel> getAllAddress(@PathVariable("emailid")String email)
	{  
		
		 
		
		 return  regService.getAllAddress(email);
		
			
		}
}
