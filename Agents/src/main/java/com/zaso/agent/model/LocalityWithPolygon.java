package com.zaso.agent.model;

import java.util.List;

import org.springframework.data.geo.Point;


public class LocalityWithPolygon {
	
	private String localityId;
		private  String localityName;
		private Cordinate cordinate;
		private List<Point> cordinateWithin;
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
		public List<Point> getCordinateWithin() {
			return cordinateWithin;
		}
		public void setCordinateWithin(List<Point> cordinateWithin) {
			this.cordinateWithin = cordinateWithin;
		}
		@Override
		public String toString() {
			return "LocalityWithPolygon [localityId=" + localityId + ", localityName=" + localityName + ", cordinate="
					+ cordinate + ", cordinateWithin=" + cordinateWithin + "]";
		}
		
		
		
		
	

}
