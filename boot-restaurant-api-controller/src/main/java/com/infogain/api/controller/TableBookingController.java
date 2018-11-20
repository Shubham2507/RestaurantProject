package com.infogain.api.controller;

import java.util.List;

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
import com.infogain.api.entity.TableBooking;
import com.infogain.api.response.ResponseData;
import com.infogain.api.service.ITableBookingService;


@RestController("tableBookingController")
@RequestMapping("/poc/tablebooking")
@EnableWebMvc
public class TableBookingController {
	
	@Autowired
	private ITableBookingService tableBookService;
	
	String msg = "Following Data Found";
	@CrossOrigin
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping
	
	public ResponseData getBookedTables()
	{
		List<TableBooking> tableBooking=tableBookService.getAllBookedTables();
		return new ResponseData("200", msg, tableBooking);
		
	}
	@CrossOrigin
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@PostMapping()
	
	public ResponseData addBookedTable(@RequestBody TableBooking table) 
	{
		String table1=tableBookService.bookTable(table);

		return new ResponseData("200",msg,table1);
		
	}
	@CrossOrigin
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@DeleteMapping(value="/{id}")
	public String deleteOneTableBooked(@PathVariable("id") int tableId) {


		tableBookService.deleteOneTableBooking(tableId);
		return "Deletion Successful of tableId = "+tableId;		
	}
	@CrossOrigin
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@PutMapping
	public ResponseData updateBookedTable( @RequestBody TableBooking table) {

		String tableModified=tableBookService.updateTableBooking(table);

		return new ResponseData("200", msg, tableModified);
	}
}