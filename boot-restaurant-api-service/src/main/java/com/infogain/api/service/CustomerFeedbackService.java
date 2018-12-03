package com.infogain.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.infogain.api.config.JwtAuthenticationFilter;
import com.infogain.api.entity.CustomerFeedback;
import com.infogain.api.repository.CustomerFeedbackRepository;

@Service
public class CustomerFeedbackService implements ICustomerFeedbackService {


	@Autowired
	private CustomerFeedbackRepository customerFeedbackRepository;
	 @Autowired
	 private JwtAuthenticationFilter jaf;
	
	@Override
	public List<CustomerFeedback> getAllCustomerFeedback() {
		return (List<CustomerFeedback>) customerFeedbackRepository.findAll();

	}

	@Override
	public CustomerFeedback getCustomerFeedbackByFeedbackId(int feedbackId) {
		return customerFeedbackRepository.findByFeedbackId(feedbackId);

	}

	@Override
	public List<CustomerFeedback> getCustomerFeedbackByUsername(String username) {
		return  (List<CustomerFeedback>) customerFeedbackRepository.findByUsername(username);

	}

	@Override
	public CustomerFeedback addCustomerFeedback(CustomerFeedback customerFeedback) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //fetches the details of logged user. UserDetails is own class of Java Authentication mechanism.
		CustomerFeedback customerFeedbackObject=new CustomerFeedback(userDetails.getUsername(), customerFeedback.getFoodRating(), customerFeedback.getServiceRating(), customerFeedback.getAmbienceRating(), customerFeedback.getComment());
		return customerFeedbackRepository.save(customerFeedbackObject);
	}

	@Override
	public CustomerFeedback updateCustomerFeedback(CustomerFeedback customerFeedback) {
		System.out.println("inside update method in service");
		return customerFeedbackRepository.save(customerFeedback);
		

	}

	@Override
	public String deleteCustomerFeedback(int feedbackId) {
		CustomerFeedback customerFeedbackObject=customerFeedbackRepository.findByFeedbackId(feedbackId);
		customerFeedbackRepository.delete(customerFeedbackObject);
		return "Customer Feedback deleted";
	}

}
