package com.infogain.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.infogain.api.ExceptionHandler.OrderedItemNotFoundException;
import com.infogain.api.dto.OrderHistory;
import com.infogain.api.dto.OrderHistoryOfUser;
import com.infogain.api.entity.OrderPlaced;
import com.infogain.api.repo.OrderRepository;
import com.infogain.api.response.ResponseData;
import com.infogain.api.service.IOrderService;


@RestController("orderController")
@RequestMapping("/poc/order")
@EnableWebMvc
public class OrderController {


	@Autowired
	private IOrderService orderedService;
	@Autowired
	private OrderRepository orderRepository;
	String msg = "Following Data Found";

	@CrossOrigin
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping
	public ResponseData getAllOrder() {

		List<OrderPlaced> order = orderedService.getAllOrder();
		
		return new ResponseData("200", msg, order);
	}


	@CrossOrigin
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping("/orderHistory")
	public ResponseData getAllUserDetails() {
		
		List<OrderHistoryOfUser>  orders = orderedService.getUserDetails();
		if(orders==null)
			throw new OrderedItemNotFoundException("No Order History Found");
		else
		return new ResponseData("200", msg, orders);
	}
	
	@CrossOrigin
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@PostMapping
	public ResponseData addOrderItem(@RequestBody OrderPlaced order) {

		List<OrderPlaced> newOrder=orderedService.addOrder(order);
		return new ResponseData("200", msg, newOrder);

	}
	@CrossOrigin
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@PutMapping
	public ResponseData updateOrderItem( @RequestBody OrderPlaced order) {
		int oId=order.getId();
        Optional<OrderPlaced> orders= orderRepository.findById(oId);
        if(!orders.isPresent())
        	throw new OrderedItemNotFoundException("Order with ID : "+oId+"Not found");
		OrderPlaced orderUpdate=orderedService.updateOrder(order);

		return new ResponseData("200", msg, orderUpdate);
	}

}
