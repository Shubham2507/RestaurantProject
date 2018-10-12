package com.infogain.api.controller;

import java.util.List;

import org.boot.restaurant.api.dto.MenuDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.api.entity.Menu;
import com.infogain.api.repo.MenuRepository;
import com.infogain.api.response.ResponseData;
import com.infogain.api.service.IMenuService;



@RestController("menuController")
@RequestMapping("/poc/restaurant")
public class MenuController {
	@Autowired IMenuService menuService;
	@Autowired
	MenuRepository menuRepository;
	
//get All Menu
	@RequestMapping(method = RequestMethod.GET)
	public ResponseData getDept() {
		
		/*List<MenuDto> dept = menuService.getAllMenu();*/
		
		List<Menu> dept = menuRepository.findAll();
		return new ResponseData("200", "List of Menu", dept);
		
	}
//get Menu by item id	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseData findOne(@PathVariable(value = "id") int mId) {

		MenuDto ddto = menuService.findOneMenu(mId);
		return new ResponseData("200", "Following Menu Found", ddto);
		 
	}
//add Menu	
	@RequestMapping(method = RequestMethod.POST, consumes= {MediaType.APPLICATION_JSON_VALUE})
public ResponseData addNew(@RequestBody MenuDto newDto) {
		
		newDto = menuService.addNewMenu(newDto);
		return new ResponseData("200", "Added successfuly", newDto);
		
	}
//delete one
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	public String deleteDept(@PathVariable("id") int dId) {
		
		menuService.deleteOneMenu(dId);
		return "Deletion Successful";	
	}
//	
	
}
