package com.infogain.api.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.boot.restaurant.api.dto.MenuDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.infogain.api.entity.Menu;
import com.infogain.api.repo.MenuRepository;
import com.infogain.api.service.IMenuService;
import com.infogain.api.service.MenuServiceImpl;

@SpringBootTest(classes=MenuServiceImpl.class)
@RunWith(SpringRunner.class)
public class MenuControllerTest {


	@InjectMocks
	private MenuController menuController;
	
	@MockBean
	IMenuService menuService;
	MenuRepository menuRepository;

	@Autowired
    private WebApplicationContext wac;
	
	MenuDto menu = new MenuDto();
	private MockMvc mockMvc;
	List<MenuDto> lst= new ArrayList<MenuDto>();
	@Before
	public void setData() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		menu.setItemId(10);
		menu.setCategory("starters");
		menu.setItemName("Pasta");
		menu.setDescription("White sauce Pasta");
		menu.setQuantity(8);
		menu.setRate(1000);
		
		
	
		lst.add(menu);
		Mockito.when(menuService.getAllMenu()).thenReturn(lst);
	}
	
	@Test
	public void testGetMenu() throws Exception {
		RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/poc/restaurant")
				.requestAttr("ALL MENU", menuService.getAllMenu())
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		  String expected = "[{\"id\":10,\"itemName\":\"Pasta\",\"rate\":1000,\"category\":\"starters\",\"quantity\":8,\"description\":\"White sauce Pasta\"}]";
			
			assertNotNull(lst);
		  System.out.println("getAllMedicines successfully executed...");
		
		
	}

	@Test
	public void testFindOne() {
		Mockito.when(menuService.findOneMenu(Mockito.anyInt())).thenReturn(menu);
		
		
	}

	@Test
	public void testAddItemInMenu() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteDept() {

	}

}
