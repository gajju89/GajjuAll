package com.zaso.agent.model;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Repository
public class Agents {

	@JsonIgnore
	private String objectid;
	
	  private String email;
      private String fname;
      private String lname;
      private String address;
      private String mobile;

      private String longitude;
      private String lattitude;
	public String getObjectid() {
		return objectid;
	}
	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLattitude() {
		return lattitude;
	}
	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}
	@Override
	public String toString() {
		return "Agents [objectid=" + objectid + ", email=" + email + ", fname=" + fname + ", lname=" + lname
				+ ", address=" + address + ", mobile=" + mobile + ", longitude=" + longitude + ", lattitude="
				+ lattitude + "]";
	}
	
	
	



	
	
	
}
