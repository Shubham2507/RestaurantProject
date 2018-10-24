package com.infogain.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.api.entity.Cart;
import com.infogain.api.response.ResponseData;
import com.infogain.api.service.ICartService;

@RestController("cartController")
@RequestMapping("/poc/cart")
public class CartController {

	@Autowired
	private ICartService cartService;
	String msg = "Following Data Found";
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseData getEmps() {

		List<Cart> cart = cartService.getAllCart();
		return new ResponseData("200", msg, cart);
	}

	/*@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseData findOne(@PathVariable(value = "id") int eId) {

		Cart cart = cartService.findOneCart(eId);
		return new ResponseData("200", msg, cart);

	}*/
	@CrossOrigin
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value="/add",method = RequestMethod.POST)
public ResponseData addNew(@RequestBody Cart cart) {
		
		String carts = cartService.addItemToCart(cart);
		return new ResponseData("200", msg, carts);
		
	}
	

	@CrossOrigin
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
public String deleteEmp(@PathVariable("id") int cartId) {
		
		cartService.deleteOneCart(cartId);
		return "Deletion Successful of eId= "+cartId;		
	}
	@CrossOrigin
	@RequestMapping(method=RequestMethod.DELETE)
public String deleteAll() {
		
		cartService.deleteAllCart();
		return "Deleted All Item From Cart";
		
	}
	@RequestMapping( method=RequestMethod.PUT)
	public ResponseData updateCart( @RequestBody Cart cart) {
			
			String newCart=cartService.updateCart( cart);
			
			return new ResponseData("200", msg, newCart);
		}
}
