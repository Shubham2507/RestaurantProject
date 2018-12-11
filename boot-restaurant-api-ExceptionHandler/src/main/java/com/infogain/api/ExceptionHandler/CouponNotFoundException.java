package com.infogain.api.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CouponNotFoundException extends RuntimeException{
	
	public CouponNotFoundException(String exception)
	{
		super(exception+" not found");
	}

}