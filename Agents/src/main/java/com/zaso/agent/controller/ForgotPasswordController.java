package com.zaso.agent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zaso.agent.service.ForgotPasswordServiceImpl;
import com.zaso.agent.service.MailSenderService;
import com.zaso.agent.utils.GeneralUtil;

@Controller
@RequestMapping(value="/forgotPass")
public class ForgotPasswordController {
	
	@Autowired
	MailSenderService mailService;
	
	@Autowired
	ForgotPasswordServiceImpl servicePass;
	
	@RequestMapping(value="/save/varCode/{userEmail}/",method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody void save(@PathVariable("userEmail")String email)
	{
		String code=GeneralUtil.confirmationCode();
		servicePass.saveVarificationCode(email, code);
		
	}
	
	@RequestMapping(value="/delete/varCode/{email}/",method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody void delete(@PathVariable("email")String email)
	{
		servicePass.deleteVarificationCode(email);
	}
	
	
	@RequestMapping(value="/save/otp/{userEmail}/",method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody boolean saveOtp(@PathVariable("userEmail")String email)
	{
		String code=GeneralUtil.generateOtp();
		servicePass.saveOtp(email, code);
		return true;
		
	}
	
	@RequestMapping(value="/delete/otp/{userEmail}/{otp}",method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody boolean deleteOtp(@PathVariable("userEmail")String email,@PathVariable("otp")String otp)
	{
		servicePass.deleteOtp(email,otp);
		return true;
	}

}
