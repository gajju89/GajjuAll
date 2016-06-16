package com.zaso.agent.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zaso.agent.service.UserRegistrationServiceImpl;

@Aspect
public class AccessSecurity {
	@Autowired
	UserRegistrationServiceImpl useService;

public Object authentication(ProceedingJoinPoint pjp)throws Throwable
{
	 HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//System.out.println("the request is"+request);
//	 System.out.println("Advice running properly:");
String authcode=request.getHeader("auth_token");
	 if(useService.authentication(authcode))
	 {
		 return pjp.proceed();
}
	 else
		 return "something wrong";

}
}
