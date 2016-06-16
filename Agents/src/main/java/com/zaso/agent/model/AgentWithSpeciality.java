package com.zaso.agent.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AgentWithSpeciality {
	@JsonIgnore
	private String objectid;
	private String emailid;
	private String fName;
	private String lName;
	private String mobileNumber;
	private String address;
	private Position location;
	private List<AgentSpecialities> list;
	private boolean complete;
	public String getObjectid() {
		return objectid;
	}
	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Position getLocation() {
		return location;
	}
	public void setLocation(Position location) {
		this.location = location;
	}
	public List<AgentSpecialities> getList() {
		return list;
	}
	public void setList(List<AgentSpecialities> list) {
		this.list = list;
	}
	
	
	
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	@Override
	public String toString() {
		return "AgentWithSpeciality [objectid=" + objectid + ", emailid=" + emailid + ", fName=" + fName + ", lName="
				+ lName + ", mobileNumber=" + mobileNumber + ", address=" + address + ", location=" + location
				+ ", list=" + list + ", complete=" + complete + "]";
	}
	
	
	

}
