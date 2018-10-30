package com.infogain.api.test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.infogain.api.entity.Cart;
import com.infogain.api.repo.CartRepository;
import com.infogain.api.service.CartServiceImpl;
@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest
public class CartServiceImplTest {
	@Autowired
	private CartServiceImpl csi;
	@MockBean
	private CartRepository cartRepo;
	
	Cart newCart= new Cart();
	@Test
	public void testGetAllCart() {
		List<Cart> cart= new ArrayList<>();
		cart.add(new Cart(1,500,"vishal",2,"testitem",250,"snacks",2,"nothing new"));
		cart.add(new Cart(2,100,"vinod",3,"testitem2",100,"snacks",1,"nothing new in this"));
		cart.add(new Cart(3,700,"vishal",4,"testitem3",350,"snacks",2,"nothing new in this too"));
		when(csi.getAllCart()).thenReturn(cart);
		List<Cart> cartLst = csi.getAllCart();
		assertNotNull(cartLst);
		
			}
	@Test
	public void testDeleteOneCart() {
		List<Cart> cart= new ArrayList<>();
		cart.add(new Cart(2,100,"vinod",3,"testitem2",100,"snacks",1,"nothing new in this"));
		cart.add(new Cart(3,700,"vishal",4,"testitem3",350,"snacks",2,"nothing new in this too"));
		cart.add(new Cart(1,500,"vishal",2,"testitem",250,"snacks",2,"nothing new"));
		cartRepo.deleteById(Mockito.anyInt());
		String response = csi.deleteOneCart(3);
		assertEquals("Deletion Successful", response);			
	}
	@Test
	public void testDeleteAllCart() {
		List<Cart> cart= new ArrayList<>();
		cart.add(new Cart(2,100,"vinod",3,"testitem2",100,"snacks",1,"nothing new in this"));
		cart.add(new Cart(3,700,"vishal",4,"testitem3",350,"snacks",2,"nothing new in this too"));
		cart.add(new Cart(1,500,"vishal",2,"testitem",250,"snacks",2,"nothing new"));
	    cartRepo.deleteAll();
		String response = csi.deleteAllCart();
		assertEquals("Deletion Successful", response);			
		
	}
	@Test
	public void testAddItemToCart() {
		
		newCart.setCartId(8);
		newCart.setCategory("nothing");
		newCart.setDescription("nothing new");
		newCart.setItemId(6);
		newCart.setItemName("testitem");
		newCart.setQuantity(10);
		newCart.setRate(100);
		newCart.setTotalPrice(1000);
		newCart.setUsername("abcd");
		when(cartRepo.save(Mockito.any())).thenReturn(newCart);
		String response=csi.addItemToCart(newCart);
		assertEquals("ITEM ADDED TO CART", response);
		
	}
	/*@Test
	public void testUpdateCart() {
		newCart.setCartId(8);
		newCart.setCategory("nothing");
		newCart.setDescription("nothing new");
		newCart.setItemId(6);
		newCart.setItemName("testitem");
		newCart.setQuantity(10);
		newCart.setRate(100);
		newCart.setTotalPrice(1000);
		newCart.setUsername("abcd");
		when(cartRepo.save(Mockito.any())).thenReturn(newCart);
		String response=csi.updateCart(newCart);
		assertEquals("Updated Successfully!", response);
		
	}*/
	
	 @Configuration
	    static class Config {
	        @Bean
	        CartServiceImpl beanA() {
	            return new CartServiceImpl();
	        }	
	 }
}
