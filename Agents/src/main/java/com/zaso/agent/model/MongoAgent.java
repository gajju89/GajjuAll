package com.zaso.agent.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zaso.agent.model.Position;

@Document(collection="mongoAgent")
public class MongoAgent {
	@Id
	@JsonIgnore
	private String objectid;
		
		private String address;
		private String speciality;
		private List<Position> location;
		public String getObjectid() {
			return objectid;
		}
		public void setObjectid(String objectid) {
			this.objectid = objectid;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getSpeciality() {
			return speciality;
		}
		public void setSpeciality(String speciality) {
			this.speciality = speciality;
		}
		public List<Position> getLocation() {
			return location;
		}
		public void setLocation(List<Position> location) {
			this.location = location;
		}
		@Override
		public String toString() {
			return "MongoAgent [objectid=" + objectid + ", address=" + address + ", speciality=" + speciality
					+ ", location=" + location + "]";
		}
	
		

}
