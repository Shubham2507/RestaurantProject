package com.infogain.api.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerFeedbackEmptyException extends RuntimeException {
	
	public CustomerFeedbackEmptyException(String exception)

	{
		super(exception);
	}

}
