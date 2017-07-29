package com.zaso.agent.model;

public class OrderSpecification {
	
	private String userEmail;
	private String category;
	private String subCategory;
	private String agentAsign;
	private String userName;
	private String addressId;
	private String userMobileNumber;
	private Double lon;
	private Double lat;
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
	public String getUserMobileNumber() {
		return userMobileNumber;
	}
	public void setUserMobileNumber(String userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
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
		return "OrderSpecification [userEmail=" + userEmail + ", category=" + category + ", subCategory=" + subCategory
				+ ", agentAsign=" + agentAsign + ", userName=" + userName + ", addressId=" + addressId
				+ ", userMobileNumber=" + userMobileNumber + ", lon=" + lon + ", lat=" + lat + "]";
	}
	
	

}
