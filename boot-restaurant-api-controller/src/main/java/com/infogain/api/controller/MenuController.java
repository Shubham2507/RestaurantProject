package com.infogain.api.controller;

import java.util.List;
import java.util.Optional;

import org.boot.restaurant.api.dto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.infogain.api.ExceptionHandler.ItemAlreadyExistsInMenuException;
import com.infogain.api.ExceptionHandler.MenuItemNotFoundException;

import com.infogain.api.entity.Menu;
import com.infogain.api.repo.MenuRepository;
import com.infogain.api.response.ResponseData;
import com.infogain.api.service.IMenuService;




@RestController("menuController")
@RequestMapping("/poc/restaurant")
@EnableWebMvc
public class MenuController {
	
	@Autowired 
	private IMenuService menuService;
	@Autowired
	private MenuRepository menuRepository;

	//get All Menu
    @CrossOrigin
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping
	public ResponseData getMenu() {

		List<MenuDto> menu = menuService.getAllMenu();
		if(menu==null)
			throw new MenuItemNotFoundException("Menu is Empty");
		
		return new ResponseData("200", "List of Menu", menu);
		

	}
	//get Menu by item id	

	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@CrossOrigin
	@GetMapping(value = "/{id}")
	public ResponseData findOne(@PathVariable(value = "id") int menuId) {


		Optional<Menu> x=menuRepository.findById(menuId);
		if(!x.isPresent())
		throw new MenuItemNotFoundException("Item with ID : "+menuId+" Not Found");
		else
		return new ResponseData("200", "Following Menu Found", x);

	}


	//add Menu
   
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@CrossOrigin
    @PostMapping
	public ResponseData addItemInMenu(@RequestBody MenuDto menuDto) {
		String name= menuDto.getItemName();
		Optional<Menu> obj=menuRepository.findByItemName(name);
		if(obj.isPresent())
			throw new ItemAlreadyExistsInMenuException("Item with name "+name+" Already exists");
		menuDto = menuService.addItem(menuDto);
		
			
		return new ResponseData("200", "Added successfuly", menuDto);

	}
	//delete one

   
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@CrossOrigin
	@DeleteMapping(value="/{id}")
	public String deleteDept(@PathVariable("id") int menuId) {

		menuService.deleteOneMenu(menuId);
		return "Deletion Successful";	
	}
	//	update
	@CrossOrigin
	@PutMapping(value="/{id}")
	public ResponseData updateMenu(@PathVariable("id") int itemId,@RequestBody MenuDto menuDto)
	{	Optional<Menu> menu = menuRepository.findById(itemId);
	    if(!menu.isPresent())
	    	throw new MenuItemNotFoundException("Item with Id : "+itemId+" not found");
	    
		MenuDto menuDto1=menuService.updateMenuURL(itemId, menuDto);
	
		return new ResponseData("200", "Added successfuly", menuDto1);
		
	}

}
