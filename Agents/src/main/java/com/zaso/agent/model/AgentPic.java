package com.zaso.agent.model;

public class AgentPic {
	private String emailId;
	private String url;
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "AgentPic [emailId=" + emailId + ", url=" + url + "]";
	}
	
	

}
