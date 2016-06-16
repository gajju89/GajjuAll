package com.zaso.agent.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="userOrders")
public class UserOrder {
	@Id
	private String orderid;
	private String orderNumber;
	private String userEmail;
	private String category;
	private String subCategory;
	private String agentAsign;
	private String orderStatus;
	private String dateOfOrder;
	private String userName;
	private String addressId;
	private String userNumber;
	private double lon;
	private double lat;
	
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getAgentAsign() {
		return agentAsign;
	}
	public void setAgentAsign(String agentAsign) {
		this.agentAsign = agentAsign;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getDateOfOrder() {
		return dateOfOrder;
	}
	public void setDateOfOrder(String dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	@Override
	public String toString() {
		return "UserOrders [orderid=" + orderid + ", orderNumber=" + orderNumber + ", userEmail=" + userEmail
				+ ", category=" + category + ", subCategory=" + subCategory + ", agentAsign=" + agentAsign
				+ ", orderStatus=" + orderStatus + ", dateOfOrder=" + dateOfOrder + ", userName=" + userName
				+ ", addressId=" + addressId + ", userNumber=" + userNumber + ", lon=" + lon + ", lat=" + lat + "]";
	}
	
	
	

}
