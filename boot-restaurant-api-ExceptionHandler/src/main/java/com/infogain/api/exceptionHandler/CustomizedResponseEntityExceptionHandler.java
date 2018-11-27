package com.infogain.api.exceptionHandler;

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
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
{
  @ExceptionHandler(CouponNotFoundException.class)
  public final ResponseEntity<ExceptionResponse> handleCouponNotFoundException(CouponNotFoundException ex, WebRequest request)
  {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false),HttpStatus.NOT_FOUND.value());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }
  
   
  @ExceptionHandler(CustomerFeedbackNotFoundException.class)
  public final ResponseEntity<ExceptionResponse> handleCustomerFeedbackNotFoundException(CustomerFeedbackNotFoundException ex, WebRequest request)
  {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false),HttpStatus.NOT_FOUND.value());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(CouponListEmptyException.class)
  public final ResponseEntity<ExceptionResponse> handleCouponListEmptyException(CouponListEmptyException ex, WebRequest request)
  {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false),HttpStatus.NOT_FOUND.value());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }
  
   
  @ExceptionHandler(CustomerFeedbackListEmptyException.class)
  public final ResponseEntity<ExceptionResponse> handleCustomerFeedbackListEmptyException(CustomerFeedbackListEmptyException ex, WebRequest request)
  {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false),HttpStatus.NOT_FOUND.value());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(CouponAlreadyExistsException.class)
  public final ResponseEntity<ExceptionResponse> handleCouponAlreadyExistsException(CouponAlreadyExistsException ex, WebRequest request)
  {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false),HttpStatus.CONFLICT.value());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
  }
 
  @ExceptionHandler(CustomerFeedbackAlreadyExistsException.class)
  public final ResponseEntity<ExceptionResponse> handleCustomerFeedbackAlreadyExistsException(CustomerFeedbackAlreadyExistsException ex, WebRequest request)
  {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false),HttpStatus.CONFLICT.value());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
  }

  
  @ExceptionHandler(CouponExpiredException.class)
  public final ResponseEntity<ExceptionResponse> handleCouponExpiredException(CouponExpiredException ex, WebRequest request)
  {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false),HttpStatus.NOT_FOUND.value());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(InvalidCustomerFeedbackRangeException.class)
  public final ResponseEntity<ExceptionResponse> handleInvalidCustomerFeedbackRangeException(InvalidCustomerFeedbackRangeException ex, WebRequest request)
  {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false),HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }
 
  
  @ExceptionHandler(CustomerFeedbackEmptyException.class)
  public final ResponseEntity<ExceptionResponse> handleCustomerFeedbackEmptyException(CustomerFeedbackEmptyException ex, WebRequest request)
  {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false),HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) 
  {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false),HttpStatus.INTERNAL_SERVER_ERROR.value());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}