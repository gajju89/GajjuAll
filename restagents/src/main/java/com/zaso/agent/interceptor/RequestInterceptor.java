package com.zaso.agent.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zaso.agent.service.UserRegistrationServiceImpl;

public class RequestInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	UserRegistrationServiceImpl useService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
		    throws Exception
		    {
		
		String authcode=request.getHeader("auth_token");
		
		 if(useService.authentication(authcode)||request.getRequestURL().toString().contains("/getLoginInfo")||request.getRequestURL().toString().contains("/enterInfo")||request.getRequestURL().toString().contains("/category")||request.getRequestURL().toString().contains("/forgotPass")||request.getRequestURL().toString().contains("/updatePassword"))
		 {
			 return true;
	}
		 else
		 {
			 response.getWriter().write("");
		 
			 return false;
		 }

		
		    }

}
