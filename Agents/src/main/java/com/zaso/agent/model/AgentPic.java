package com.zaso.agent.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="agentPic")
public class AgentPic {
	private String emailId;
	private String  url;
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
