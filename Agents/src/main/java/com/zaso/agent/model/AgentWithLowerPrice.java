package com.zaso.agent.model;

public class AgentWithLowerPrice {
	
	private String emailid;
	private String commission;
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	@Override
	public String toString() {
		return "AgentWithLowerPrice [emailid=" + emailid + ", commission=" + commission + "]";
	}
	
	

}
