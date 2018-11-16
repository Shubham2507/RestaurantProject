package com.infogain.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infogain.api.entity.TableBooking;
import com.infogain.api.repo.TableBookingRepository;
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
	public TableBooking bookTable(TableBooking table) {
		
	TableBooking tableBooking=new TableBooking();
	tableBooking.setBookingDate(table.getBookingDate());
	tableBooking.setGuestName(table.getGuestName());
	tableBooking.setMobileNo(table.getMobileNo());
	tableBooking.setNoOfTables(table.getNoOfTables());
	tableBooking.setUsername(table.getUsername());
	tableBooking.setTableId(table.getTableId());
	tableBooking.setIsActive(0);
	
	bookingRepo.save(tableBooking);
	
		return tableBooking;
	}

	@Override
	public String updateTableBooking(TableBooking table) {
		TableBooking tab=bookingRepo.getOne(table.getTableId());
		tab.setBookingDate(table.getBookingDate());
		tab.setGuestName(table.getGuestName());
		tab.setMobileNo(table.getMobileNo());
		tab.setNoOfTables(table.getNoOfTables());
		
		tab.setUsername(table.getUsername());
		tab.setIsActive(0);
		bookingRepo.save(tab);
		return "Update Successfully!";
	}
	

}
