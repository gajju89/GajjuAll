package com.zaso.agent.model;

import java.util.Date;

public class Response {
	private boolean status;
	private String tocken;
	private boolean usedMobileNumber;
	private boolean verifiedMobileNumber;
	private String userRegisterDate;
	
	
	
	public boolean isVerifiedMobileNumber() {
		return verifiedMobileNumber;
	}
	public void setVerifiedMobileNumber(boolean verifiedMobileNumber) {
		this.verifiedMobileNumber = verifiedMobileNumber;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getTocken() {
		return tocken;
	}
	public void setTocken(String tocken) {
		this.tocken = tocken;
	}
	public boolean isUsedMobileNumber() {
		return usedMobileNumber;
	}
	public void setUsedMobileNumber(boolean usedMobileNumber) {
		this.usedMobileNumber = usedMobileNumber;
	}
	public String getUserRegisterDate() {
		return userRegisterDate;
	}
	public void setUserRegisterDate(String date) {
		this.userRegisterDate = date;
	}
	
	
	
	
	
	

}
