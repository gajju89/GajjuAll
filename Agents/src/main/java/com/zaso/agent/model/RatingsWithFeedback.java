package com.zaso.agent.model;

public class RatingsWithFeedback {

    String rating;
    String message;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	@Override
	public String toString() {
		return "RatingsWithFeedback [rating=" + rating + ", message=" + message + "]";
	}
    
}