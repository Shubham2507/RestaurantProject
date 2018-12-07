package com.infogain.api.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class TableEntriesNullException extends RuntimeException{

	public TableEntriesNullException (String exception) {
		super(exception);
		// TODO Auto-generated constructor stub
	}

}
