package com.zaso.agent.service;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Repository;

@Repository
public class MailSenderService {
	
	@Autowired
	ForgotPasswordServiceImpl serv;

/*@Autowired
JavaMailSenderImpl mailSender;
	
	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}
	*/
	
	public void sendMail(String to,String code) {
		
		//String code=serv.findVericationCode(to);
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.mail.yahoo.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("ejentomail@yahoo.com","ejento1990");
				}
			});

		//SimpleMailMessage message = new SimpleMailMessage();
		
		/*message.setFrom(mailSender.getUsername());
		message.setTo(to);
		message.setSubject("your password varification code is ");
		message.setText(code);
		mailSender.send(message);*/	
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ejentomail@yahoo.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("your password varification code is");
			message.setText("Dear customer your varification code is :    " +
					code);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
public void sendOtp(String to,String code) {
		
		//String code=serv.findVericationCode(to);
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.mail.yahoo.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("ejentomail@yahoo.com","ejento1990");
				}
			});

		//SimpleMailMessage message = new SimpleMailMessage();
		
		/*message.setFrom(mailSender.getUsername());
		message.setTo(to);
		message.setSubject("your password varification code is ");
		message.setText(code);
		mailSender.send(message);*/	
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ejentomail@yahoo.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("your One Time Password !");
			message.setText("Dear customer your otp code is :    " +
					code);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}


}
