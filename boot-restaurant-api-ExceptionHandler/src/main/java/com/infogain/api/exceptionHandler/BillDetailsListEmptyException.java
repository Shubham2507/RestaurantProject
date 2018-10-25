package com.infogain.api.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BillDetailsListEmptyException extends RuntimeException{

	public BillDetailsListEmptyException(String exception)

	{
		super(exception+" is empty");
	}
}
