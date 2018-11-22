package com.infogain.api.exceptionHandler;

import java.util.Date;

public class ExceptionResponse {
	
	 private Date timestamp;
	  private String message;
	  private String details;
	  private int statusCode;

	  public ExceptionResponse(Date timestamp, String message, String details, int statusCode) {
	    super();
	    this.timestamp = timestamp;
	    this.message = message;
	    this.details = details;
	    this.statusCode = statusCode;
	  }

	  public Date getTimestamp() {
	    return timestamp;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public String getDetails() {
	    return details;
	  }

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
