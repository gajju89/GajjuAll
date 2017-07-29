package com.zaso.agent.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.zaso.agent.model.UserAuthentication;
import com.zaso.agent.repositories.UserAuthenticationRepository;

@Repository
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
	@Autowired
	UserAuthenticationRepository authRepo;
	

	@Override
	public void userValidInfo(UserAuthentication userAuthentication) {
		// TODO Auto-generated method stub
		
		authRepo.insert(userAuthentication);

	}

	@Override
	public void deleteInfo(String accessToken) {
		// TODO Auto-generated method stub
		authRepo.deleteByauthCode(accessToken);
		
	}

}
