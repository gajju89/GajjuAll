package com.zaso.agent.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="userOrders")
public class UserOrders {
	
	private String userEmail;
	private String specialityType;
	private String agentEmail;
	private String mailStatus;
	private String orderStatus;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getSpecialityType() {
		return specialityType;
	}
	public void setSpecialityType(String specialityType) {
		this.specialityType = specialityType;
	}
	public String getAgentEmail() {
		return agentEmail;
	}
	public void setAgentEmail(String agentEmail) {
		this.agentEmail = agentEmail;
	}
	public String getMailStatus() {
		return mailStatus;
	}
	public void setMailStatus(String mailStatus) {
		this.mailStatus = mailStatus;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	@Override
	public String toString() {
		return "UserOrders [userEmail=" + userEmail + ", specialityType=" + specialityType + ", agentEmail="
				+ agentEmail + ", mailStatus=" + mailStatus + ", orderStatus=" + orderStatus + "]";
	}
	
	

}
