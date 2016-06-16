package com.zaso.agent.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="favourite")
public class Favourite {
	
	private String useremail;
	private String agentemail;
	private boolean state;
	public String getAgentemail() {
		return agentemail;
	}
	public void setAgentemail(String agentemail) {
		this.agentemail = agentemail;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Favourite [agentemail=" + agentemail + ", useremail=" + useremail + ", state=" + state + "]";
	}
	
	

}
