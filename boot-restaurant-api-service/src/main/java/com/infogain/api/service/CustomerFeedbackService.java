package com.infogain.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infogain.api.entity.CustomerFeedback;
import com.infogain.api.repository.CustomerFeedbackRepository;

@Service
public class CustomerFeedbackService implements ICustomerFeedbackService {


	@Autowired
	private CustomerFeedbackRepository customerFeedbackRepository;
	
	@Override
	public List<CustomerFeedback> getAllCustomerFeedback() {
		return (List<CustomerFeedback>) customerFeedbackRepository.findAll();

	}

	@Override
	public CustomerFeedback getCustomerFeedbackByFeedbackId(int feedbackId) {
		return customerFeedbackRepository.findByFeedbackId(feedbackId);

	}

	@Override
	public List<CustomerFeedback> getCustomerFeedbackByUserId(int userId) {
		return  (List<CustomerFeedback>) customerFeedbackRepository.findByUserId(userId);

	}

	@Override
	public CustomerFeedback addCustomerFeedback(CustomerFeedback customerFeedback) {
		return customerFeedbackRepository.save(customerFeedback);
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
