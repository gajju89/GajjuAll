package com.zaso.agent.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="lastAddressId")
public class LastAddressId {
	
	private long lastid;

	public long getLastid() {
		return lastid;
	}

	public void setLastid(long lastid) {
		this.lastid = lastid;
	}

}
