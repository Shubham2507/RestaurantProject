package com.infogain.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.infogain.api.config.JwtAuthenticationFilter;
import com.infogain.api.entity.Cart;

import com.infogain.api.repo.CartRepository;
@EnableWebMvc
@Service("cartService")
public class CartServiceImpl implements ICartService {
	@Autowired
	private CartRepository cartRepo;
	
	 @Autowired
		private JwtAuthenticationFilter jaf;

	@Override
	public List<Cart> getAllCart() {
		return cartRepo.findAll();
	
	}


	@Override
	public String deleteOneCart(int cartId) {
		cartRepo.delete(cartRepo.getOne(cartId));
		return "Deletion Successful";
	}

	
	@Override
	public String addItemToCart(Cart newCart) {
		String output="";
		Cart newCarts = cartRepo.findByItemIdAndUsername(newCart.getItemId(),jaf.getUsername()); 
		if (newCarts!=null)
		{
			output="ITEM ALREADY EXISTS IN CART !!!!";
		}
		else {
		Cart cart = new Cart();
		cart.setUsername(jaf.getUsername());
		cart.setItemId(newCart.getItemId());
		cart.setItemName(newCart.getItemName());
		cart.setDescription(newCart.getDescription());
		cart.setCategory(newCart.getCategory());
		cart.setRate(newCart.getRate());
		cart.setQuantity(newCart.getQuantity());
		cart.setTotalPrice(newCart.getQuantity() * newCart.getRate());

		cartRepo.save(cart);
		output="ITEM ADDED TO CART";
		}
		
		return output;
	}

	@Override
	public String deleteAllCart() {
		cartRepo.deleteAll();
		return "Deletion Successful";

	}

	
	@Override
	@Transactional
	public String updateCart(Cart cart) {
		Cart newItem = cartRepo.getOne(cart.getCartId());
		String temp ="";
		if (cart.getQuantity() == 0) {
			deleteOneCart(cart.getCartId());
			temp="THIS ITEM  IS DELETED FROM CART!";
			

		} else {
			newItem.setQuantity(cart.getQuantity());
			newItem.setTotalPrice(cart.getQuantity() * cart.getRate());
			cartRepo.save(newItem);
			temp="Updated Successfully!";

		}
		return temp;


	}
	

}
