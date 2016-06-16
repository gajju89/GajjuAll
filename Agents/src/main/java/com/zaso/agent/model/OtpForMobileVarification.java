package com.zaso.agent.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="mobileOTP")
public class OtpForMobileVarification {
	private String userEmail;
	private String otp;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	@Override
	public String toString() {
		return "OtpForMobileVarification [userEmail=" + userEmail + ", otp=" + otp + "]";
	}
	
	

}
