package com.zaso.agent.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="favourite")
public class FavouriteModel {
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agentemail == null) ? 0 : agentemail.hashCode());
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
		FavouriteModel other = (FavouriteModel) obj;
		if (agentemail == null) {
			if (other.agentemail != null)
				return false;
		} else if (!agentemail.equals(other.agentemail))
			return false;
		return true;
	}

	private String agentemail;

	private String address;
	//private String email;
	private String fname;
	private String lname;
	private String mobile;
	private List<AgentSpecialities> speciality;
	private Double longitude;
	private Double lattitude;
	private List<String> picurl;
	private boolean isfavourite;
	public String getAgentemail() {
		return agentemail;
	}
	public void setAgentemail(String agentemail) {
		this.agentemail = agentemail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public List<AgentSpecialities> getSpeciality() {
		return speciality;
	}
	public void setSpeciality(List<AgentSpecialities> spec1) {
		this.speciality = spec1;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double double1) {
		this.longitude = double1;
	}
	public Double getLattitude() {
		return lattitude;
	}
	public void setLattitude(Double double1) {
		this.lattitude = double1;
	}
	public List<String> getPicurl() {
		return picurl;
	}
	public void setPicurl(List<String> list) {
		this.picurl = list;
	}
	
	
	
	
	
	public boolean isIsfavourite() {
		return isfavourite;
	}
	public void setIsfavourite(boolean isfavourite) {
		this.isfavourite = isfavourite;
	}
	
	@Override
	public String toString() {
		return "FavouriteModel [agentemail=" + agentemail + ", address=" + address + ", fname=" + fname + ", lname="
				+ lname + ", mobile=" + mobile + ", speciality=" + speciality + ", longitude=" + longitude
				+ ", lattitude=" + lattitude + ", picurl=" + picurl + ", isfavourite=" + isfavourite + "]";
	}
	
	
	

	


	
	

}
