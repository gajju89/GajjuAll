package com.zaso.agent.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="totalRating")
public class TotalRating {
	
	private String agentEmail;
	private float  rating;
	private int  totalUser;
	public String getAgentEmail() {
		return agentEmail;
	}
	public void setAgentEmail(String agentEmail) {
		this.agentEmail = agentEmail;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public int getTotalUser() {
		return totalUser;
	}
	public void setTotalUser(int totalUser) {
		this.totalUser = totalUser;
	}
	@Override
	public String toString() {
		return "TotalRating [agentEmail=" + agentEmail + ", rating=" + rating + ", totalUser=" + totalUser + "]";
	}
	
	

}
