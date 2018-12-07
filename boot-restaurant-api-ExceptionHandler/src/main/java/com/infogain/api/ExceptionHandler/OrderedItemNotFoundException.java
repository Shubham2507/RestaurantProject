package com.infogain.api.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderedItemNotFoundException extends RuntimeException {
	public OrderedItemNotFoundException(String exception) {
	    super(exception);
}
}