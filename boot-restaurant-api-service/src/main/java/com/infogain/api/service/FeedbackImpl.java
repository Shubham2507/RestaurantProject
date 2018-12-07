package com.infogain.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.boot.restaurant.api.dto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infogain.api.config.JwtAuthenticationFilter;
import com.infogain.api.entity.Feedback;
import com.infogain.api.entity.Menu;
import com.infogain.api.repo.FeedbackRepo;
import com.infogain.api.repo.MenuRepository;

@Service
public class FeedbackImpl {
	@Autowired
	private FeedbackRepo feedbackRepo;

	@Autowired
	private JwtAuthenticationFilter jaf;
	
	public List<Feedback> getAllFeedback() {
		List<Feedback> fList = feedbackRepo.findAll();
		return fList;
	}

	

	
	/*@Override
	public MenuDto findOneMenu(int itemId) {
		Menu men = menuRepo.getOne(itemId);
		MenuDto menuDto = new MenuDto();
		menuDto.setItemId(men.getItemId());
		menuDto.setItemName(men.getItemName());
		menuDto.setDescription(men.getDescription());
		menuDto.setCategory(men.getCategory());
		menuDto.setQuantity(men.getQuantity());
		menuDto.setRate(men.getRate());

		return menuDto;
	}

	*/
	public Feedback addFeedback(Feedback feedback) {
		Feedback f= new Feedback();
		f.setUsername(jaf.getUsername());
		f.setComment(feedback.getComment());
		f.setFoodRating(feedback.getFoodRating());
		f.setServiceRating(feedback.getServiceRating());
		feedbackRepo.save(f);
		return f;
	}



}
