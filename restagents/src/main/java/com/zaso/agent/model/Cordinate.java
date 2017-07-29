package com.zaso.agent.model;

import org.springframework.data.geo.Point;

public class Cordinate  {
	
	private Double lattitude;
	private Double longitude;
	
	
	
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLattitude() {
		return lattitude;
	}
	public void setLattitude(Double lattitude) {
		this.lattitude = lattitude;
	}
	@Override
	public String toString() {
		return "Cordinate [longitude=" + longitude + ", lattitude=" + lattitude + "]";
	}
	
	

}
