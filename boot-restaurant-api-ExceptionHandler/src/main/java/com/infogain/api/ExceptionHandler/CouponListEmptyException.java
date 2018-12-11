package com.infogain.api.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CouponListEmptyException extends RuntimeException {
	
	public CouponListEmptyException(String exception)

	{
		super(exception+" is empty");
	}
}