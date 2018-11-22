package com.infogain.api.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerFeedbackListEmptyException extends RuntimeException 
{
	public CustomerFeedbackListEmptyException(String exception)

	{
		super(exception+" is empty");
	}
}
