package com.infogain.api.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CustomerFeedbackAlreadyExistsException extends RuntimeException {

	public CustomerFeedbackAlreadyExistsException (String exception)
	{
	super(exception+" already present.");
	}

}
