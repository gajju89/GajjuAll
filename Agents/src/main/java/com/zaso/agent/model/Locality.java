package com.zaso.agent.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

@Repository
@Document(collection="locality")
public class Locality {
	
	private String localityId;
	private  String localityName;
	private Cordinate cordinate;
	public String getLocalityId() {
		return localityId;
	}
	public void setLocalityId(String localityId) {
		this.localityId = localityId;
	}
	public String getLocalityName() {
		return localityName;
	}
	public void setLocalityName(String localityName) {
		this.localityName = localityName;
	}
	public Cordinate getCordinate() {
		return cordinate;
	}
	public void setCordinate(Cordinate cordinate) {
		this.cordinate = cordinate;
	}
	@Override
	public String toString() {
		return "Locality [localityId=" + localityId + ", localityName=" + localityName + ", cordinate=" + cordinate
				+ "]";
	}

	
}
