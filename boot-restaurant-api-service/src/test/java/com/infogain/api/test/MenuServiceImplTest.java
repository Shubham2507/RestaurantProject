package com.infogain.api.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.boot.restaurant.api.dto.MenuDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

import com.infogain.api.entity.Menu;
import com.infogain.api.repository.MenuRepository;
import com.infogain.api.service.MenuServiceImpl;



@SpringBootTest(classes=MenuServiceImpl.class)
@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = RestaurantApplication.class)

public class MenuServiceImplTest{

	@Autowired
	private MenuServiceImpl menuService;

	@MockBean
	private MenuRepository menuRepo;


	private MockMvc mockMvc;

	Menu menu=new Menu();

	@Test
	public void testGetAllMenu() {
		List<MenuDto> lst = new ArrayList<>();
		lst.add(new MenuDto(11, "Honey Almond", 100, "snacks", 1, "Almond dipped in honey"));
		lst.add(new MenuDto(12, "Roasted Peanuts", 200, "snacks", 2, "Peanuts"));

		
		
		when(menuService.getAllMenu()).thenReturn(lst);
		List<Menu> menu = menuRepo.findAll();
		assertNotNull(menu);
	}
	@Test
	public void testdeleteOneMenu() throws IllegalArgumentException
	{
		List<MenuDto> lst = new ArrayList<>();
		lst.add(new MenuDto(3, "Honey Almond", 100, "snacks", 1, "Almond dipped in honey"));
		lst.add(new MenuDto(4, "Honey Almond", 100, "snacks", 1, "Almond dipped in honey"));

		menuRepo.deleteById(Mockito.anyInt());
		String response = menuService.deleteOneMenu(3);
		assertEquals("Deletion Successful", response);

	}




	@Test
	public void testadd()
	{
		MenuDto mdto= new MenuDto();
		Menu menu= new Menu();
		mdto.setItemId(27);
		mdto.setCategory("snacks");
		mdto.setDescription("Maggie Masala");
		mdto.setItemName("Maggi");
		mdto.setQuantity(2);
		mdto.setRate(100);
		MenuDto mDto = menuService.addItem(mdto);
		assertNotNull(mDto);
		
	}

	@Test
	public void testDeleteMenuPresent() throws Exception {


		List<MenuDto> lst = new ArrayList<>();
		MenuDto dto=new MenuDto(4, "Almond", 200, "snacks", 4, "Almond");
		lst.add(new MenuDto(3, "Honey Almond", 100, "snacks", 1, "Almond dipped in honey"));
		String response = menuService.deleteAllMenu();
		assertEquals("Deletion Successful", response);

	}


	@Test
	public void testDeleteMenuNotPresent() throws Exception {
		menu.setCategory("nothing");
		menu.setDescription("nothing new");
		menu.setItemId(1);
		menu.setItemName("hhh");
		menu.setQuantity(2);
		menu.setRate(100);
		String response = menuService.deleteAllMenu();
		assertNotEquals("Deletion Not Successful", response);

		

	}






	@Test
	public void testfindOneMenu() throws Exception
	{
		List<MenuDto> lst = new ArrayList<>();
		MenuDto dto=new MenuDto(4, "Almond", 200, "snacks", 5, "Almond");
		lst.add(new MenuDto(3, "Honey Almond", 100, "snacks", 1, "Almond dipped in honey"));
		//lst.add(new MenuDto(4, "Roasted Almond", 100, "snacks", 1, "Almond Roasted in honey"));



	
		Optional<Menu> mdto = menuRepo.findById(3);
		assertNotNull(mdto);
		//assertNull(mdto);

	}

}
