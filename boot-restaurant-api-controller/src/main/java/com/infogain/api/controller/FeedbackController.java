package com.infogain.api.controller;

import java.util.List;
import java.util.Optional;

import org.boot.restaurant.api.dto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.infogain.api.ExceptionHandler.ItemAlreadyExistsInMenuException;
import com.infogain.api.ExceptionHandler.MenuItemNotFoundException;
import com.infogain.api.entity.Feedback;
import com.infogain.api.entity.Menu;
import com.infogain.api.response.ResponseData;
import com.infogain.api.service.FeedbackImpl;

@RestController("feedbackController")
@RequestMapping("/feedback")
@EnableWebMvc
public class FeedbackController {
	@Autowired
	private FeedbackImpl fImpl;
	
	
	 @CrossOrigin
		
		@GetMapping
		public ResponseData getMenu() {

			List<Feedback> feedback = fImpl.getAllFeedback();
			
			
			return new ResponseData("200", "List of Feedback", feedback);
			

		}
		@CrossOrigin
	    @PostMapping
		public ResponseData addItemInMenu(@RequestBody Feedback feedback) {
			Feedback feed=fImpl.addFeedback(feedback);
			
			
				
			return new ResponseData("200", "Added successfuly", feed);

		}
}
