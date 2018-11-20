package com.infogain.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.api.entity.Cart;
import com.infogain.api.entity.OrderPlaced;

import com.infogain.api.response.ResponseData;
import com.infogain.api.service.IOrderService;


@RestController("orderController")
@RequestMapping("/poc/order")
public class OrderController {
	

	@Autowired
	private IOrderService orderedService;
	String msg = "Following Data Found";
	
	@CrossOrigin
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseData getEmps() {

		List<OrderPlaced> order = orderedService.getAllOrder();
		return new ResponseData("200", msg, order);
	}
	@CrossOrigin
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@RequestMapping(method = RequestMethod.POST,consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseData addOrderItem(@RequestBody OrderPlaced order) {
		
		// List<Cart> newCart=orderedService.find(username);
		 
		OrderPlaced newOrder=orderedService.addOrder(order);
		return new ResponseData("200", msg, newOrder);

	}
	@CrossOrigin
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@RequestMapping( method=RequestMethod.PUT)
	public ResponseData updateCart( @RequestBody OrderPlaced order) {
			
			OrderPlaced orderUpdate=orderedService.updateOrder(order);
			
			return new ResponseData("200", msg, orderUpdate);
		}

}
