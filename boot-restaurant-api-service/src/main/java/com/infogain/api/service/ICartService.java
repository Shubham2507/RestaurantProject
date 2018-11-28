package com.infogain.api.service;

import java.util.List;

import com.infogain.api.entity.Cart;


public interface ICartService {
	List<Cart> getAllCart();

	String deleteOneCart(int cartId);


	String addItemToCart(Cart cartObj);

	String updateCart(Cart cart);

	String deleteAllCart();
	

}
