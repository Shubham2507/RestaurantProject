package com.infogain.api.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.infogain.api.controller.CartController;
import com.infogain.api.entity.Cart;
import com.infogain.api.service.CartServiceImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes=CartController.class)
public class CartControllerTest {


	private MockMvc mvc;
	@Autowired
	private WebApplicationContext ctx;

	@MockBean
	private CartServiceImpl csi;
	
	
	List<Cart> cart=new ArrayList<>();
	Cart carts;
	@Before
	public void setUp() throws Exception{
		this.mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		 carts =new Cart();
		carts.setCartId(96);
		carts.setCategory("snacks");
		carts.setDescription("Garlic bread with cheese");
		carts.setItemId(1);
		carts.setItemName("Bread Basket");
		carts.setQuantity(1);
		carts.setRate(50);
		carts.setTotalPrice(50);
		carts.setUsername("vishal");
		
		cart.add(carts);
		
	}
	
@Test
public void testgetCart() throws Exception
{
	Mockito.when(csi.getAllCart()).thenReturn(cart);
	RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/poc/cart")
			.contentType(MediaType.APPLICATION_JSON_UTF8);
	MvcResult result =  mvc.perform(requestBuilder).andReturn();
	String expected="[{\"cartId\":1,\"category\":\"medname\",\"description\":\"df\",\"itemId\":2,\"itemName\":\"krrk\",\"quantity\":1,\"rate\":200,\"totalPrice\":200,\"username\":\"vishal\"}]";
	assertEquals(expected, result.getResponse().getContentAsString());
	assertNotNull(cart);
	
	System.out.println("retrieveDetailsForMedicine successfully executed...");
	
}





	
}
