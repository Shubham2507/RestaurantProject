package com.infogain.api.controller;

import java.util.List;

import org.boot.restaurant.api.dto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.api.entity.Menu;
import com.infogain.api.repo.MenuRepository;
import com.infogain.api.response.ResponseData;
import com.infogain.api.service.IMenuService;



@RestController("menuController")
@RequestMapping("/poc/restaurant")
public class MenuController {
	@Autowired 
	IMenuService menuService;
	@Autowired
	MenuRepository menuRepository;

	//get All Menu
	
	@GetMapping
	public ResponseData getMenu() {

		List<Menu> menu = menuRepository.findAll();
		return new ResponseData("200", "List of Menu", menu);

	}
	//get Menu by item id	
	
	@GetMapping(value = "/{id}")
	public ResponseData findOne(@PathVariable(value = "id") int menuId) {

		MenuDto menudto = menuService.findOneMenu(menuId);
		return new ResponseData("200", "Following Menu Found", menudto);

	}
	//add Menu	
@PostMapping
	public ResponseData addItemInMenu(@RequestBody MenuDto menuDto) {

		menuDto = menuService.addItem(menuDto);
		return new ResponseData("200", "Added successfuly", menuDto);

	}
	//delete one
	@DeleteMapping(value="/{id}")
	public String deleteDept(@PathVariable("id") int menuId) {

		menuService.deleteOneMenu(menuId);
		return "Deletion Successful";	
	}
	//	

}
