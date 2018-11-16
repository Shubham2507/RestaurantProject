package com.infogain.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.infogain.api.entity.OrderPlaced;
import com.infogain.api.response.ResponseData;
import com.infogain.api.service.IOrderService;


@RestController("orderController")
@RequestMapping("/poc/order")
@EnableWebMvc
public class OrderController {


	@Autowired
	private IOrderService orderedService;
	String msg = "Following Data Found";

	@CrossOrigin
	@GetMapping
	public ResponseData getAllOrder() {

		List<OrderPlaced> order = orderedService.getAllOrder();
		return new ResponseData("200", msg, order);
	}
	@CrossOrigin
	@PostMapping
	public ResponseData addOrderItem(@RequestBody OrderPlaced order) {

		List<OrderPlaced> newOrder=orderedService.addOrder(order);
		return new ResponseData("200", msg, newOrder);

	}
	@CrossOrigin
	@PutMapping
	public ResponseData updateOrderItem( @RequestBody OrderPlaced order) {

		OrderPlaced orderUpdate=orderedService.updateOrder(order);

		return new ResponseData("200", msg, orderUpdate);
	}

}
