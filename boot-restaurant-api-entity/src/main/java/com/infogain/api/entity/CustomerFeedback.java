package com.infogain.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;  


@Entity
@Table(name="CustomerFeedback")
public class CustomerFeedback {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="FeedbackId")
	private int feedbackId;
	

	@Column(nullable=false,name="Username")
	private String username;
	
	@Column(name="Food_Rating")
	private int foodRating;
	
	@Column(name="Service_Rating")
	private int serviceRating;
	
	@Column(name="Ambience_Rating")
	private int ambienceRating;
	
	@Column(name="Comment")
	private String comment;

	public CustomerFeedback() {
		super();
	}
 

	public CustomerFeedback(String username, int foodRating, int serviceRating, int ambienceRating, String comment) {
		super();
		this.username = username;
		this.foodRating = foodRating;
		this.serviceRating = serviceRating;
		this.ambienceRating = ambienceRating;
		this.comment = comment;
	}

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getFoodRating() {
		return foodRating;
	}

	public void setFoodRating(int foodRating) {
		this.foodRating = foodRating;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "CustomerFeedback [feedbackId=" + feedbackId + ", username=" + username + ", foodRating=" + foodRating
				+ ", serviceRating=" + serviceRating + ", ambienceRating=" + ambienceRating + ", comment=" + comment
				+ "]";
	}	
	
	
	

}
