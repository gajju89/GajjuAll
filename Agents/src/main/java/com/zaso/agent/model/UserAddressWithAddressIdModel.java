package com.zaso.agent.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="userAddresses")
public class UserAddressWithAddressIdModel {
	@Id
	private String id;
	private String emailid;
	private String name;
	private String pincode;
	private String address;
	private String city;
	private String state;
	private String phoneNumber;
	private String landMark;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLandMark() {
		return landMark;
	}
	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}
	
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	
	@Override
	public String toString() {
		return "UserAddressWithAddressIdModel [addressId=" + id + ", emailid=" + emailid + ", name=" + name
				+ ", pincode=" + pincode + ", address=" + address + ", city=" + city + ", state=" + state
				+ ", phoneNumber=" + phoneNumber + ", landMark=" + landMark + "]";
	}
	
	

}
