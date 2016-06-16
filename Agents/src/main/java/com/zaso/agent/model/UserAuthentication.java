package com.zaso.agent.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="userAuthentication")
public class UserAuthentication {
	
	private String emailId;
	private String authCode;
	private String loginDate;
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String date) {
		this.loginDate = date;
	}
	@Override
	public String toString() {
		return "UserAuthentication [emailId=" + emailId + ", authCode=" + authCode + ", loginDate=" + loginDate + "]";
	}
	
	
	
	

}
