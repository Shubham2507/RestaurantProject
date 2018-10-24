/*package com.infogain.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.api.entity.Cart;
import com.infogain.api.entity.OrderedItem;
import com.infogain.api.response.ResponseData;
import com.infogain.api.service.IOrderedItemService;

@RestController("orderedItemController")
@RequestMapping("/poc/orderitem")
public class OrderedItemController {

	@Autowired
	private IOrderedItemService orderedService;
	String msg = "Following Data Found";


	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseData getEmps() {

		List<OrderedItem> orderItem = orderedService.getAllOrderedItem();
		return new ResponseData("200", msg, orderItem);
	}
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST)
	public ResponseData addOrderedItem(@RequestBody OrderedItem orderItem) {

		OrderedItem orderedItem=orderedService.addOrderedItem(orderItem);
		return new ResponseData("200", msg, orderedItem);

	}
	@CrossOrigin
	@RequestMapping(value = "/{username}",method = RequestMethod.GET)
	public ResponseData getByUsername(@PathVariable(value = "username") String username) {

		List<OrderedItem> orderItem = orderedService.findByUsername(username);
		return new ResponseData("200", msg, orderItem);
	}

}
*/