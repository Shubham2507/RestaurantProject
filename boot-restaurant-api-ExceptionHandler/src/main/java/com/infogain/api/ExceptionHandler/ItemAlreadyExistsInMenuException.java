package com.infogain.api.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ItemAlreadyExistsInMenuException extends RuntimeException{

	public ItemAlreadyExistsInMenuException (String exception) {
		super(exception);
		// TODO Auto-generated constructor stub
	}

}
