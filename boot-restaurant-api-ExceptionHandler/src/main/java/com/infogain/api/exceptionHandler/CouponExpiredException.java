package com.infogain.api.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CouponExpiredException extends RuntimeException {
	
	public CouponExpiredException(String exception)
	{
		super(exception+" has expired");
	}

}
