package com.onlinetv.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vaadin.ui.Button;

@Document(collection="videoCollection")
public class FreeVideosModel {
	@Id
	private String id;
	private String date;
	private String videoTitle;
	private String startTime;
	private String endTime;
	private String duration;
	private String videoUrl;
	private boolean paid;
//	@JsonIgnore
//	private Button delete;
	public String getId() {
		return id;
	}
//	public Button getDelete() {
//		return delete;
//	}
//	public void setDelete(Button delete) {
//		this.delete = delete;
//	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	
	
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	@Override
	public String toString() {
		return "FreeVideosModel [id=" + id + ", date=" + date + ", videoTitle=" + videoTitle + ", startTime="
				+ startTime + ", endTime=" + endTime + ", duration=" + duration + ", videoUrl=" + videoUrl + ", paid="
				+ paid + "]";
	}
	
	
	

}
