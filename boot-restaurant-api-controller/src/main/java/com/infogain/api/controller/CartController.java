package com.infogain.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.api.entity.Cart;
import com.infogain.api.response.ResponseData;
import com.infogain.api.service.ICartService;
//import com.infogain.api.config.CORSFilter;





@RestController("cartController")
@RequestMapping("/poc/cart")
public class CartController {

	@Autowired
	private ICartService cartService;
	String msg = "Following Data Found";
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseData getEmps() {

		List<Cart> emps = cartService.getAllCart();
		return new ResponseData("200", msg, emps);
	}

	/*@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseData findOne(@PathVariable(value = "id") int eId) {

		Cart cart = cartService.findOneCart(eId);
		return new ResponseData("200", msg, cart);

	}*/
	@CrossOrigin
	@RequestMapping(value="/add",method = RequestMethod.POST)
public ResponseData addNew(@RequestBody Cart newDto) {
		
		newDto = cartService.addNewCart(newDto);
		return new ResponseData("200", msg, newDto);
		
	}
	

	@CrossOrigin
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
public String deleteEmp(@PathVariable("id") int eId) {
		
		cartService.deleteOneCart(eId);
		return "Deletion Successful of eId= "+eId;		
	}
	@CrossOrigin
	@RequestMapping(method=RequestMethod.DELETE)
public String deleteAll() {
		
		return "Deleted All Item From Cart";
		
	}
	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
	public ResponseData updateCart(@PathVariable("id") int cartId, @RequestBody Cart cart) {
			
			Cart newCart=cartService.updateCart(cartId, cart);
			
			return new ResponseData("200", msg, newCart);
		}
}
