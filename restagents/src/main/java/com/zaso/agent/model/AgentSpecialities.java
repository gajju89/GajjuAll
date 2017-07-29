package com.zaso.agent.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection="agentSpeciality")
public class AgentSpecialities {
	@Id
	@JsonIgnore
	private String objectid;
	private String email;
	private String name;
	private String type;
	private String commission;
	private String ourPrice;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	
	
	
	
	public String getOurPrice() {
		return ourPrice;
	}
	public void setOurPrice(String ourPrie) {
		this.ourPrice = ourPrie;
	}
	@Override
	public String toString() {
		return "AgentSpecialities [objectid=" + objectid + ", email=" + email + ", name=" + name + ", type=" + type
				+ ", commission=" + commission + ", ourPrie=" + ourPrice + "]";
	}
	
	
	
	
	

}
