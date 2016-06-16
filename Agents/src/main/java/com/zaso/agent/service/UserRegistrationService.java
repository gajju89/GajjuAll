package com.zaso.agent.service;

import org.json.JSONObject;

import com.zaso.agent.model.Response;
import com.zaso.agent.model.UserRegistration;


public interface UserRegistrationService {
	
	public void saveUser(UserRegistration user,String accessToken);
	
	public Response getUserLoginInfo(String email,String passw);
	
	public boolean authentication(String authcode);

}
