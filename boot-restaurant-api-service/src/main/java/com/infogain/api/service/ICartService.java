package com.infogain.api.service;

import java.util.List;

import com.infogain.api.entity.Cart;


//@Service("cartService")
public interface ICartService {
	List<Cart> getAllCart();

	String deleteOneCart(int cartId);

	//CartDto findOneCart(int cartId);

	String addItemToCart(Cart cartObj);

	String updateCart(Cart cart);

	void deleteAllCart();
	
	//CartDto updateCartURL(int eId, CartDto empDto);

}
