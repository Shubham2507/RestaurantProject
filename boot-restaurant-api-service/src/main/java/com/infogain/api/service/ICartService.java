package com.infogain.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.infogain.api.entity.Cart;


//@Service("cartService")
public interface ICartService {
	List<Cart> getAllCart();

	String deleteOneCart(int cartId);

	//CartDto findOneCart(int cartId);

	Cart addNewCart(Cart cartObj);

	Cart updateCart(int cartId,Cart cart);

	void deleteAllCart();

	//CartDto updateCartURL(int eId, CartDto empDto);

}
