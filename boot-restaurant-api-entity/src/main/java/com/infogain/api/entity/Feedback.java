package com.infogain.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int feedId;
	String username;
public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
public int getFeedId() {
		return feedId;
	}
	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}
int foodRating;
String comment;
int serviceRating;
int ambienceRating;
public int getFoodRating() {
	return foodRating;
}
public void setFoodRating(int foodRating) {
	this.foodRating = foodRating;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public int getServiceRating() {
	return serviceRating;
}
public void setServiceRating(int serviceRating) {
	this.serviceRating = serviceRating;
}
public int getAmbienceRating() {
	return ambienceRating;
}
public void setAmbienceRating(int ambienceRating) {
	this.ambienceRating = ambienceRating;
}

}
