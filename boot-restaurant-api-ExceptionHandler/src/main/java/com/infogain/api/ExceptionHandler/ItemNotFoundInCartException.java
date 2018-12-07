package com.infogain.api.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundInCartException extends RuntimeException{

	public ItemNotFoundInCartException (String exception) {
		super(exception);
		// TODO Auto-generated constructor stub
	}

}
