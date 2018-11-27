package com.infogain.api.service;

import java.util.List;

import com.infogain.api.entity.CustomerFeedback;

public interface ICustomerFeedbackService {
	
	List<CustomerFeedback> getAllCustomerFeedback();
    CustomerFeedback getCustomerFeedbackByFeedbackId(int feedbackId);
    List<CustomerFeedback> getCustomerFeedbackByUserId(int userId);
    CustomerFeedback addCustomerFeedback(CustomerFeedback customerFeedback);
    CustomerFeedback updateCustomerFeedback(CustomerFeedback customerFeedback);
    String deleteCustomerFeedback(int feedbackId);

}
