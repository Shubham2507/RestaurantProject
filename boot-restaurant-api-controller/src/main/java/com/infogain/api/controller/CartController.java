package com.infogain.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.infogain.api.entity.Cart;
import com.infogain.api.response.ResponseData;
import com.infogain.api.response.ResponseDataModified;
import com.infogain.api.service.ICartService;

@RestController("cartController")
@RequestMapping("/poc/cart")
@EnableWebMvc
public class CartController {
	

	@Autowired
	private ICartService cartService;
	String msg = "Following Data Found";
	@CrossOrigin

	@PreAuthorize("hasAnyRole('USER','ADMIN')")

	@GetMapping
	public ResponseData getCart() {

		List<Cart> cart = cartService.getAllCart();
		return new ResponseData("200", msg, cart);
	}


	@CrossOrigin

	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@PostMapping("/add")
	public ResponseData addItem(@RequestBody Cart cart) {


		String carts = cartService.addItemToCart(cart);
		return new ResponseData("200", msg, carts);

	}


	@CrossOrigin

	@PreAuthorize("hasAnyRole('USER','ADMIN')")

	@DeleteMapping(value="/{id}")
	public String deleteOneCart(@PathVariable("id") int cartId) {


		cartService.deleteOneCart(cartId);
		return "Deletion Successful of cartId= "+cartId;		
	}
	@CrossOrigin

	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@DeleteMapping
	public String deleteAll() {
		cartService.deleteAllCart();
		return "Deleted All Item From Cart";

	}

	@CrossOrigin
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@PutMapping
	public ResponseData updateCart( @RequestBody Cart cart) {

		String newCart=cartService.updateCart( cart);

		return new ResponseData ("200", msg, newCart);
	}
}
