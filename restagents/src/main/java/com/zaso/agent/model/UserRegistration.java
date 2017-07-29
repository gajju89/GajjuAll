package com.zaso.agent.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="registerUsers")
public class UserRegistration {
	private String fullName;
	private String emailid;
	private String mobileNumber;
	private String password;
	private boolean mobileNumberVarified;
	private boolean userBoarded;
	private String date;
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
	
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isMobileNumberVarified() {
		return mobileNumberVarified;
	}
	public void setMobileNumberVarified(boolean mobileNumberVarified) {
		this.mobileNumberVarified = mobileNumberVarified;
	}
	
	
	public boolean isUserBoarded() {
		return userBoarded;
	}
	public void setUserBoarded(boolean userBoarded) {
		this.userBoarded = userBoarded;
	}
	
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "UserRegistration [fullName=" + fullName + ", emailid=" + emailid + ", mobileNumber=" + mobileNumber
				+ ", password=" + password + ", mobileNumberVarified=" + mobileNumberVarified + ", userBoarded="
				+ userBoarded + ", date=" + date + "]";
	}
	
	

}
