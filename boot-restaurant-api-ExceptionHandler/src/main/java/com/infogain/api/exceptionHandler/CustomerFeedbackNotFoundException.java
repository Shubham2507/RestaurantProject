package com.infogain.api.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerFeedbackNotFoundException extends RuntimeException{
	
	public CustomerFeedbackNotFoundException(String exception)
	{
		super(exception+" not found");
	}


}
