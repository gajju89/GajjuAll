package com.zaso.agent.service;

import com.zaso.agent.model.UserAuthentication;

public interface UserAuthenticationService {
	
	public void userValidInfo(UserAuthentication userAuthentication);
	public void deleteInfo(String accessToken);

}
