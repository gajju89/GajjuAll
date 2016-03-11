package com.onlinetv.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="lastmongoid")
public class LastMongoId {

	private long lastid;

	public long getLastid() {
		return lastid;
	}

	public void setLastid(long lastid) {
		this.lastid = lastid;
	}

	
	
	
}
