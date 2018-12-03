package com.infogain.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infogain.api.entity.CustomerFeedback;

@Repository
public interface CustomerFeedbackRepository extends CrudRepository<CustomerFeedback, Integer>{
	
	CustomerFeedback findByFeedbackId(int feedbackId);
	List<CustomerFeedback> findByUsername(String username);
	

}
