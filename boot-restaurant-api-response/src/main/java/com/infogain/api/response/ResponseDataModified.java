package com.infogain.api.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseDataModified
{
	private String statusCode;
	private Object response;
	
	public ResponseDataModified() {
		super();
	}

	public ResponseDataModified(String statusCode, Object response) {
		super();
		this.statusCode = statusCode;
		this.response = response;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode= statusCode;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	

}