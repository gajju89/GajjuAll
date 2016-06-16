package com.zaso.agent.model;

public class UserUpdate {
	
	private String email;
	private String name;
	private String mobile;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return "UserUpdate [email=" + email + ", name=" + name + ", mobile=" + mobile + "]";
	}

	
}
