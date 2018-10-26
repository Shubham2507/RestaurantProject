package com.infogain.api.testService;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import com.infogain.api.repo.MenuRepository;
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
	@Test
	public void testGetAllMenu() {
		List<MenuDto> lst = new ArrayList<>();
		lst.add(new MenuDto(3, "Honey Almond", 100, "snacks", 1, "Almond dipped in honey"));
		when(menuService.getAllMenu()).thenReturn(lst);
		List<MenuDto> menu = menuService.getAllMenu();
		assertNotNull(menu);
	}
	/*@Test
public void testdeleteOneMenu(int itemId)
{
		List<MenuDto> lst = new ArrayList<>();
		lst.add(new MenuDto(3, "Honey Almond", 100, "snacks", 1, "Almond dipped in honey"));
		lst.add(new MenuDto(4, "Honey Almond", 100, "snacks", 1, "Almond dipped in honey"));
		
		when(menuService.deleteOneMenu(Mockito.anyInt())).thenReturn("Deletion Successful");
		String response = menuService.deleteOneMenu(4);
		assertEquals("Deletion Successful", response);
}*/

	
/*public void testfindOneMenu(int itemId)
{
		List<MenuDto> lst = new ArrayList<>();
		MenuDto dto=new MenuDto(4, "Almond", 200, "snacks", 4, "Almond");
		lst.add(new MenuDto(3, "Honey Almond", 100, "snacks", 1, "Almond dipped in honey"));
		lst.add(new MenuDto(5, "Honey Almond", 100, "snacks", 1, "Almond dipped in honey"));
		
		Mockito.when(menuService.findOneMenu(Mockito.anyInt())).thenReturn(dto);
		MenuDto mdto = menuService.findOneMenu(4);
		assertNotNull(mdto);
		
}*/
	
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
		
		//when(menuService.addItem(Mockito.any(MenuDto.class))).thenReturn(mdto);
		MenuDto mDto = menuService.addItem(mdto);
		assertNotNull(mDto);
		
	}
	
	 @Test
		public void testDeleteMedicinePresent() throws Exception {
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/poc/restaurant/{id}")
					
					.accept(MediaType.APPLICATION_JSON);
			mockMvc.perform(requestBuilder)
			.andExpect(status().isOk());
			
		}
}
