package com.infogain.api.testService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.boot.restaurant.api.dto.MenuDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


import com.infogain.api.repo.MenuRepository;
import com.infogain.api.service.MenuServiceImpl;



@SpringBootTest
@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = RestaurantApplication.class)

public class MenuServiceImplTest{

	@Autowired
	private MenuServiceImpl menuService;
	
	@MockBean
	private MenuRepository menuRepo;
	
	@Test
	public void testGetAllMenu() {
		List<MenuDto> lst = new ArrayList<>();
		lst.add(new MenuDto(3, "Honey Almond", 100, "snacks", 1, "Almond dipped in honey"));
		when(menuService.getAllMenu()).thenReturn(lst);
		List<MenuDto> menu = menuService.getAllMenu();
		assertNotNull(menu);
	}
	@Test
public void testdeleteOneMenu(int itemId)
{
		List<MenuDto> lst = new ArrayList<>();
		lst.add(new MenuDto(3, "Honey Almond", 100, "snacks", 1, "Almond dipped in honey"));
		lst.add(new MenuDto(4, "Honey Almond", 100, "snacks", 1, "Almond dipped in honey"));
		
		when(menuService.deleteOneMenu(Mockito.anyInt())).thenReturn("Deletion Successful");
		String response = menuService.deleteOneMenu(4);
		assertEquals("Deletion Successful", response);
}

	@Test
public void testfindOneMenu(int itemId)
{
		List<MenuDto> lst = new ArrayList<>();
		MenuDto dto=new MenuDto(4, "Almond", 200, "snacks", 4, "Almond");
		lst.add(new MenuDto(3, "Honey Almond", 100, "snacks", 1, "Almond dipped in honey"));
		lst.add(new MenuDto(5, "Honey Almond", 100, "snacks", 1, "Almond dipped in honey"));
		
		Mockito.when(menuService.findOneMenu(Mockito.anyInt())).thenReturn(dto);
		MenuDto mdto = menuService.findOneMenu(4);
		assertNotNull(mdto);
		
}
	
	@Test
	public void testadd()
	{
		MenuDto mdto= new MenuDto();
		mdto.setItemId(1);
		mdto.setCategory("snacks");
		mdto.setDescription("");
		mdto.setItemName("Almond Roasted");
		mdto.setQuantity(2);
		
		when(menuService.addItem(Mockito.any(MenuDto.class))).thenReturn(mdto);
		MenuDto mDto = menuService.addItem(mdto);
		assertNotNull(mDto);
	}
}
