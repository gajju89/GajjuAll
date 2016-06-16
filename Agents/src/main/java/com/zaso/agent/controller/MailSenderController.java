package com.zaso.agent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zaso.agent.service.MailSenderService;

@Controller
@RequestMapping(value="/email")
public class MailSenderController {
	@Autowired
	MailSenderService senderService;
	@RequestMapping(value="/sendEmail/{userEmail}/",method=RequestMethod.GET)
	public @ResponseBody void sendEmail(@PathVariable("userEmail")String email)
	{
		//senderService.sendMail(email);
		
	}

}
