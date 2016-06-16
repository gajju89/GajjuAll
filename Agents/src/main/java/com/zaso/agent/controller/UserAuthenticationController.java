package com.zaso.agent.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.zaso.agent.model.Response;
import com.zaso.agent.model.UserAuthentication;

import com.zaso.agent.service.UserAuthenticationServiceImpl;
import com.zaso.agent.utils.GeneralUtil;

@Controller
@RequestMapping("Login")
public class UserAuthenticationController {
	
	@Autowired
	UserAuthenticationServiceImpl authService;
	
	@RequestMapping(value="/authentication/{email}", method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody Response saveOrUpdate(@PathVariable("email")String email)
	{  
		Response resp=new Response();
		String accessToken=GeneralUtil.generateAccessToken();
		
		UserAuthentication userAuth=new UserAuthentication();
		userAuth.setAuthCode(accessToken);
		userAuth.setEmailId(email);
		
			authService.userValidInfo(userAuth);
			
			if(accessToken!=null)
			{
				resp.setStatus(true);
				resp.setTocken(accessToken);
				return resp;
			}
				else
				{
					resp.setStatus(false);
					resp.setTocken(accessToken);
				}
			return resp;
		 
	}

}
