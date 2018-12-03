package com.infogain.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.tomcat.util.buf.UDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.infogain.api.config.JwtAuthenticationFilter;
import com.infogain.api.entity.CustomerFeedback;
import com.infogain.api.exceptionHandler.CustomerFeedbackListEmptyException;
import com.infogain.api.exceptionHandler.CustomerFeedbackNotFoundException;
import com.infogain.api.exceptionHandler.InvalidCustomerFeedbackRangeException;
import com.infogain.api.response.ResponseDataModified;
import com.infogain.api.service.ICustomerFeedbackService;

@RestController
@EnableWebMvc
@RequestMapping(value = "/customerfeedback")
public class CustomerFeedbackController {
	
	@Autowired
	ICustomerFeedbackService customerFeedbackService;
	
	
	@GetMapping()
	public ResponseEntity<?> getAllCustomerFeedback()
	{
		List<CustomerFeedback> customerFeedbackList= customerFeedbackService.getAllCustomerFeedback();

		if (customerFeedbackList==null || customerFeedbackList.isEmpty() )
			throw new CustomerFeedbackListEmptyException("Customer Feedback List : ");

		return new ResponseEntity<>(new ResponseDataModified("200", customerFeedbackList),HttpStatus.OK);
	}
	
	@GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCustomerFeedbackByUsername(@PathVariable("username") String username)
	{
		List<CustomerFeedback> customerFeedbackList = customerFeedbackService.getCustomerFeedbackByUsername(username);

		if (customerFeedbackList==null || customerFeedbackList.isEmpty())
			throw new CustomerFeedbackListEmptyException("Customer Feedback ");

		return new ResponseEntity<>(new ResponseDataModified("200",customerFeedbackList),HttpStatus.OK);
	}
		
	@GetMapping(value = "/{feedbackId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCustomerFeedbackByFeedbackId(@PathVariable("feedbackId") int feedbackId)
	{
		CustomerFeedback customerFeedbackObject = customerFeedbackService.getCustomerFeedbackByFeedbackId(feedbackId);

		if (customerFeedbackObject == null)
			throw new CustomerFeedbackNotFoundException("Customer Feedback ");

		return new ResponseEntity<>( new ResponseDataModified("200",customerFeedbackObject),HttpStatus.OK);
	}
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addCustomerFeedback(@RequestBody @Valid CustomerFeedback customerFeedback) {
		//CustomerFeedback customerFeedbackObject = customerFeedbackService.getCustomerFeedbackByFeedbackId(customerFeedback.getFeedbackId());
		
		if(customerFeedback.getFoodRating()<0 || customerFeedback.getFoodRating()>5 || customerFeedback.getServiceRating()<0 || customerFeedback.getServiceRating()>5 || customerFeedback.getAmbienceRating()<0 || customerFeedback.getAmbienceRating()>5)
			throw new InvalidCustomerFeedbackRangeException("Please provide rating on a scale of 0-5");
		customerFeedbackService.addCustomerFeedback(customerFeedback);
		return new ResponseEntity<>( new ResponseDataModified("200",customerFeedback),HttpStatus.OK);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCustomerFeedback(@RequestBody @Valid CustomerFeedback customerFeedback) {
		CustomerFeedback customerFeedbackObject = customerFeedbackService.getCustomerFeedbackByFeedbackId(customerFeedback.getFeedbackId());

		if (customerFeedbackObject == null)
			throw new CustomerFeedbackNotFoundException("Customer Feedback ");
		if(customerFeedback.getFoodRating()<0 || customerFeedback.getFoodRating()>5 || customerFeedback.getServiceRating()<0 || customerFeedback.getServiceRating()>5 || customerFeedback.getAmbienceRating()<0 || customerFeedback.getAmbienceRating()>5)
			throw new InvalidCustomerFeedbackRangeException("Please provide rating on a scale of 0-5");

		customerFeedbackService.updateCustomerFeedback(customerFeedback);
		
		return new ResponseEntity<> (new ResponseDataModified("200",customerFeedbackObject),HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{feedbackId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteCustomerFeedback(@PathVariable("feedbackId") int feedbackId) {
		CustomerFeedback customerFeedbackObject = customerFeedbackService.getCustomerFeedbackByFeedbackId(feedbackId);

		if (customerFeedbackObject == null)
			throw new CustomerFeedbackNotFoundException("Customer Feedback ");

		customerFeedbackService.deleteCustomerFeedback(feedbackId);
		return new ResponseEntity<>(new ResponseDataModified("410", customerFeedbackObject),HttpStatus.GONE);
	}
	
	
}
