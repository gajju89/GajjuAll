
package com.zaso.agent.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="ratingByUser")
public class RatingByUser {
	
	private String userEmail;
	private String agentEmail;
	private float rating;
	private String message ;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
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
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "RatingByUser [userEmail=" + userEmail + ", agentEmail=" + agentEmail + ", rating=" + rating
				+ ", message=" + message + "]";
	}
	
	

}
