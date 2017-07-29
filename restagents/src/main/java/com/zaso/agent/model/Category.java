package com.zaso.agent.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

//@Repository
@Document(collection="category")
public class Category {
	@Id
	private String id;
	private String categoryname;
	private String picUrl;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryname=" + categoryname + ", picUrl=" + picUrl + "]";
	}
	
}
