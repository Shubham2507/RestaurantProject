package com.infogain.api.ExceptionHandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	 @ExceptionHandler(MenuItemNotFoundException.class)
	  public final ResponseEntity<ErrorDetails> 
	 handleMenuNotFoundException(MenuItemNotFoundException ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	  }
	 
	 @ExceptionHandler(OrderedItemNotFoundException.class)
	  public final ResponseEntity<ErrorDetails> 
	 handleMenuNotFoundException(OrderedItemNotFoundException ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	  }
	 
	 @ExceptionHandler(ItemAlreadyExistsInMenuException.class)
	  public final ResponseEntity<ErrorDetails> 
	 handleMenuNotFoundException(ItemAlreadyExistsInMenuException ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	 }
	 
	 @ExceptionHandler(TableEntriesNullException.class)
	  public final ResponseEntity<ErrorDetails> 
	 handleMenuNotFoundException(TableEntriesNullException ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.NO_CONTENT);
	 }
	 
	 @ExceptionHandler(Exception.class)
	 public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
	   ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	       request.getDescription(false));
	   return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	 
	 
	  
}
