package com.infogain.api.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CouponAlreadyExistsException extends RuntimeException {
	
	public CouponAlreadyExistsException(String exception)
	{
	super(exception+" already present.");
	}

}