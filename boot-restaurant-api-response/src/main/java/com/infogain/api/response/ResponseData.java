package com.infogain.api.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize
public class ResponseData {
	
	private String code;
	private String message;
	private Object response;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	
	public ResponseData() {
	}

	public ResponseData(String code, String message, Object response) {
		super();
		this.code = code;
		this.message = message;
		this.response = response;
	}
   
}
