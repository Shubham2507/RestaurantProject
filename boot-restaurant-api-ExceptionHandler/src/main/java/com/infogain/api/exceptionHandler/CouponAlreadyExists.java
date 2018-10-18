package com.infogain.api.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CouponAlreadyExists extends RuntimeException {
	
	public CouponAlreadyExists(String exception)
	{
	super(exception+" already present.");
	}

}
