package com.zaso.agent.model;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="forgotPassword")
public class ForgotPassword {
	
	private String emailid;
	
	private String varificationCode;

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getVarificationCode() {
		return varificationCode;
	}

	public void setVarificationCode(String varificationCode) {
		this.varificationCode = varificationCode;
	}

	@Override
	public String toString() {
		return "ForgotPassword [emailid=" + emailid + ", varificationCode=" + varificationCode + "]";
	}
	
	

}
