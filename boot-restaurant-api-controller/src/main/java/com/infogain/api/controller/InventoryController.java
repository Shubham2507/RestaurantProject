/*package com.infogain.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.infogain.api.entity.InventoryForTable;
import com.infogain.api.response.ResponseData;
import com.infogain.api.service.InventoryService;

@RestController("tableInventoryController")
@RequestMapping("/poc/inventory")
@EnableWebMvc
public class InventoryController {
	@Autowired
	private InventoryService iService;
	String msg = "Following Data Found";
	@CrossOrigin
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping
	
	public ResponseData getBookedTables()
	{
		List<InventoryForTable> tableBooking=iService.getAllTableInventory();
		return new ResponseData("200", msg, tableBooking);
		
	}
	@CrossOrigin
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@PostMapping()
	public ResponseData addItemInInventory(@RequestBody InventoryForTable table)
	{
		InventoryForTable table1=iService.addInventory(table);
		return new ResponseData("200",msg,table1);
		
	}
	@CrossOrigin
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@PutMapping
	public ResponseData updateInventoryTable( @RequestBody InventoryForTable table) {

		String tableModified=iService.updateInventory(table);

		return new ResponseData("200", msg, tableModified);
	}
	
	
}*/