package com.zaso.agent.model;

import org.springframework.stereotype.Repository;

@Repository
public class Shops {

private String objectid;
	
	private String address;
	private String email;
	private String fname;
	private String lname;
	private String mobile;
	private String speciality;
	public String getObjectid() {
		return objectid;
	}
	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	@Override
	public String toString() {
		return "Shops [objectid=" + objectid + ", address=" + address + ", email=" + email + ", fname=" + fname
				+ ", lname=" + lname + ", mobile=" + mobile + ", speciality=" + speciality + "]";
	}
	
}
