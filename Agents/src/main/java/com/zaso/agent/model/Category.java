package com.zaso.agent.model;

import org.springframework.stereotype.Repository;

@Repository
public class Category {

	private String objectid;
	private String categoryname;
	public String getObjectid() {
		return objectid;
	}
	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	@Override
	public String toString() {
		return "Categiry [objectid=" + objectid + ", categiryname=" + categoryname + "]";
	}
	
}
