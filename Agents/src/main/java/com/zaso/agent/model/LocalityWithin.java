package com.zaso.agent.model;

import java.util.List;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

@Repository
@Document(collection="localityWithin")
public class LocalityWithin {
	
	private String localityId;
	
	
	private String locality;
	
	private List<Point> cordinateWithin;
	
	

	
	public String getLocalityId() {
		return localityId;
	}
	public void setLocalityId(String localityId) {
		this.localityId = localityId;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public List<Point> getCordinateWithin() {
		return cordinateWithin;
	}
	public void setCordinateWithin(List<Point> cordinateWithin) {
		this.cordinateWithin = cordinateWithin;
	}
	@Override
	public String toString() {
		return "LocalityWithin [localityId=" + localityId + ", locality=" + locality + ", cordinateWithin="
				+ cordinateWithin + "]";
	}
	
	
	

}
