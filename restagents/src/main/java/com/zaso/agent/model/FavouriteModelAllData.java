package com.zaso.agent.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FavouriteModelAllData {
	@JsonIgnore
	private String objectid;
	private String emailid;
	private String fName;
	private String lName;
	private String mobileNumber;
	private String address;
	private Position location;
	private List<AgentSpecialities> list;
	private List<String> picurl;
	private boolean isfavourite;
	private boolean complete;
	public String getObjectid() {
		return objectid;
	}
	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}
	
	public List<String> getPicurl() {
		return picurl;
	}
	public void setPicurl(List<String> picurl) {
		this.picurl = picurl;
	}
	public boolean isIsfavourite() {
		return isfavourite;
	}
	public void setIsfavourite(boolean isfavourite) {
		this.isfavourite = isfavourite;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailid == null) ? 0 : emailid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FavouriteModelAllData other = (FavouriteModelAllData) obj;
		if (emailid == null) {
			if (other.emailid != null)
				return false;
		} else if (!emailid.equals(other.emailid))
			return false;
		return true;
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
		return "FavouriteModelAllData [objectid=" + objectid + ", emailid=" + emailid + ", fName=" + fName + ", lName="
				+ lName + ", mobileNumber=" + mobileNumber + ", address=" + address + ", location=" + location
				+ ", list=" + list + ", picurl=" + picurl + ", isfavourite=" + isfavourite + ", complete=" + complete
				+ "]";
	}

}
