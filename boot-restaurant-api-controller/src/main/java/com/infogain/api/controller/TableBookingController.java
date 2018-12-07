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

import com.infogain.api.ExceptionHandler.TableEntriesNullException;
import com.infogain.api.entity.TableBooking;
import com.infogain.api.response.ResponseData;
import com.infogain.api.service.ITableBookingService;

@RestController("tableBookingController")
@RequestMapping("/tablebooking")
@EnableWebMvc
public class TableBookingController {
	
	@Autowired
	private ITableBookingService tableBookService;
	
	String msg = "Following Data Found";
	@CrossOrigin
	
	@GetMapping
	
	public ResponseData getBookedTables()
	{
		List<TableBooking> tableBooking=tableBookService.getAllBookedTables();
		return new ResponseData("200", msg, tableBooking);
		
	}
	@CrossOrigin

	@PostMapping()
	
	public ResponseData addBookedTable(@RequestBody TableBooking table) 
	{
		if(table.getBookingDate()==null||table.getMobileNo()==0 || table.getNoOfGuests()==0 || table.getTime()==null|| table.getUsername()==null)
		{
			throw new TableEntriesNullException("Enter details properly!!");
		}
		String table1=tableBookService.bookTable(table);
		
		return new ResponseData("200",msg,table1);
	}
	@CrossOrigin

	@DeleteMapping(value="/{id}")
	public String deleteOneTableBooked(@PathVariable("id") int tableId) {


		tableBookService.deleteOneTableBooking(tableId);
		return "Deletion Successful of tableId = "+tableId;		
	}
	@CrossOrigin

	@PutMapping
	public ResponseData updateBookedTable( @RequestBody TableBooking table) {

		String tableModified=tableBookService.updateTableBooking(table);

		return new ResponseData("200", msg, tableModified);
	}
}
