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
        request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(BillDetailsNotFoundException.class)
  public final ResponseEntity<ExceptionResponse> handleBillDetailsNotFoundException(BillDetailsNotFoundException ex, WebRequest request)
  {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(CouponListEmptyException.class)
  public final ResponseEntity<ExceptionResponse> handleCouponListEmptyException(CouponListEmptyException ex, WebRequest request)
  {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(BillDetailsListEmptyException.class)
  public final ResponseEntity<ExceptionResponse> handleBillDetailsListEmptyException(BillDetailsListEmptyException ex, WebRequest request)
  {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(CouponAlreadyExistsException.class)
  public final ResponseEntity<ExceptionResponse> handleCouponAlreadyExistsException(CouponAlreadyExistsException ex, WebRequest request)
  {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
  }
  
  @ExceptionHandler(BillDetailsAlreadyExistsException.class)
  public final ResponseEntity<ExceptionResponse> handleBillDetailsAlreadyExistsException(BillDetailsAlreadyExistsException ex, WebRequest request)
  {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
  }
  
  
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) 
  {
    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}