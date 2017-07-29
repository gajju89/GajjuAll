package com.zaso.agent.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="lastOrderId")
public class LastOrderId {
	private long lastid;

	public long getLastid() {
		return lastid;
	}

	public void setLastid(long lastid) {
		this.lastid = lastid;
	}


}
