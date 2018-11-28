package com.infogain.api.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infogain.api.entity.TableBooking;
import com.infogain.api.repository.TableBookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@EnableWebMvc
@Service
public class TableBookingServiceImpl implements ITableBookingService{

	@Autowired
	private TableBookingRepository bookingRepo;
	@Override
	public List<TableBooking> getAllBookedTables() {
		return bookingRepo.findAll();
	}

	@Override
	public String deleteOneTableBooking(int tableId) {
		bookingRepo.delete(bookingRepo.getOne(tableId));
		return "Deletion Successful";
	}

	@Override
	public String bookTable(TableBooking table)  {
		
		
	TableBooking tableBooking=new TableBooking();
	String dateTime=table.getBookingDate()+table.getTime();
    String returnType=null;
	tableBooking.setDateTime(dateTime);
	
	tableBooking.setBookingDate(table.getBookingDate());
	tableBooking.setTime(table.getTime());
	
	tableBooking.setMobileNo(table.getMobileNo());
	tableBooking.setUsername(table.getUsername());
	tableBooking.setIsActive(0);
	tableBooking.setNoOfGuests(table.getNoOfGuests());
	int guest=table.getNoOfGuests();
	if(guest==1||guest==2)
	{
		tableBooking.setCapacity(2);
		
	}
	else if(guest==3||guest==4)
	{
		tableBooking.setCapacity(4);
		
	}
	else if(guest==5||guest==6)
	{
		tableBooking.setCapacity(6);
		
	}
	else if(guest==7||guest==8)
	{
		tableBooking.setCapacity(8);
		
	}	
	int temp=bookingRepo.findBybookingDateandcapacity(tableBooking.getBookingDate(), tableBooking.getCapacity());
					
	if(tableBooking.getCapacity()==2 && temp==4)
	{
		returnType="Sorry,Tables are not available!";
	}
	else if(tableBooking.getCapacity()==4 && temp==5)
	{
		returnType="Sorry,Tables are not available!";
	
	}
	else if(tableBooking.getCapacity()==6 && temp==7)
	{
		returnType="Sorry,Tables are not available!";
	
	}
	else if(tableBooking.getCapacity()==8 && temp==2)
	{
		
		returnType="Sorry,Tables are not available!";
	}
	else
	{
	tableBooking.setIsActive(1);
	bookingRepo.save(tableBooking);
	returnType="Your table booking id is  "+tableBooking.getTableId();
	}
		return returnType;

	}

	@Override
	public String updateTableBooking(TableBooking table) {
		TableBooking tab=bookingRepo.getOne(table.getTableId());

		tab.setTime(table.getTime());
	

		bookingRepo.save(tab);
		return "Update Successfully!";
	}
	

}
