package com.infogain.api.test;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.boot.restaurant.api.dto.MenuDto;
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

import com.infogain.api.controller.MenuController;
import com.infogain.api.entity.Cart;
import com.infogain.api.entity.Menu;
import com.infogain.api.service.CartServiceImpl;
import com.infogain.api.service.MenuServiceImpl;


@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes=MenuController.class)
public class MenuControllerTest {
	
	private MockMvc mvc;
	@Autowired
	private WebApplicationContext ctx;
	@MockBean
	private MenuServiceImpl msi;
	
	
	
	List<MenuDto> menu=new ArrayList<>();
	String menuAdd;
	
	MenuDto menus;
	@Before
	public void setUp() throws Exception{
		this.mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		menus= new MenuDto();
		menus.setItemId(17);
		menus.setCategory("snacks");
		menus.setDescription("tasty");
		menus.setItemName("lays");
		menus.setQuantity(6);
		menus.setRate(1000);
		menu.add(menus);
		
		
	}
	
	@Test
	public void testgetMenu() throws Exception
	{
		Mockito.when(msi.getAllMenu()).thenReturn(menu); 
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/poc/restaurant")
				.contentType(MediaType.APPLICATION_JSON_UTF8);
		MvcResult result =  mvc.perform(requestBuilder).andReturn();
		String expected="{\"code\":\"200\",\"message\":\"List of Menu\",\"response\":[{\"itemId\":17,\"itemName\":\"lays\",\"rate\":1000,\"category\":\"snacks\",\"quantity\":6,\"description\":\"tasty\"}]}";
		assertEquals(expected, result.getResponse().getContentAsString());
		assertNotNull(expected, result.getResponse().getContentAsString());
		System.out.println("getMenu successfully executed...");
		
	}

	@Test
		public void testfindone() throws Exception
		{
		Mockito.when(msi.findOneMenu(Mockito.anyInt())).thenReturn(menus);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/poc/restaurant/17")
				.contentType(MediaType.APPLICATION_JSON_UTF8);
		MvcResult result =  mvc.perform(requestBuilder).andReturn();
		String expected="{\"code\":\"200\","
				+ "\"message\":\"List of Menu\",\"response\":"
				+ "[{\"itemId\":17,\"itemName\":\"lays\",\"rate\":1000,"
				+ "\"category\":\"snacks\",\"quantity\":6,\"description\":\"tasty\"}]}";
	
	assertNotEquals(expected, result.getResponse().getContentAsString());
		}
	
	@Test
	public void testaddItemInMenu() throws Exception
	{
		
		Mockito.when(msi.addItem(Mockito.any())).thenReturn(menus);
	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/poc/restaurant")
				.contentType(MediaType.APPLICATION_JSON_UTF8);
		MvcResult result =  mvc.perform(requestBuilder).andReturn();
		String expected="{\"code\":\"200\",\"message\":\"List of Menu\",\"response\""
				+ ":[{\"itemId\":17,\"itemName\":\"lays\","
				+ "\"rate\":1000,\"category\":\"snacks\",\"quantity\":6,\"description\":\"tasty\"}]}";
	assertNotEquals(expected, result.getResponse().getContentAsString());
	}
	
	@Test
	public void testdeleteDept() throws Exception
	{/*
		Mockito.when(msi.deleteOneMenu(Mockito.any())).thenReturn("");
	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/poc/restaurant/17")
				.contentType(MediaType.APPLICATION_JSON_UTF8);
		MvcResult result =  mvc.perform(requestBuilder).andReturn();
		mvc.perform(requestBuilder).andExpect(status().isNoContent());
		*/
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/poc/restaurant/17").accept(MediaType.APPLICATION_JSON);
		mvc.perform(requestBuilder).andExpect(status().isOk());
	}
	
}
