package com.zaso.agent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zaso.agent.service.UserAuthenticationServiceImpl;

@Controller
@RequestMapping("/logout")
public class LogoutController {

	@Autowired
	UserAuthenticationServiceImpl authService;
	
	@RequestMapping(value="/deleteInfo/{accessToken}",method=RequestMethod.POST)
	public @ResponseBody void logout(@PathVariable("accessToken")String accessToken)
	{
	      authService.deleteInfo(accessToken);	
	}

}
