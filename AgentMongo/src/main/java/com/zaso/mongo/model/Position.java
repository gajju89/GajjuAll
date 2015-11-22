package com.zaso.mongo.model;

public class Position {
	
	private Double lon;
	private Double lat;
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return "Position [lon=" + lon + ", lat=" + lat + "]";
	}
}
