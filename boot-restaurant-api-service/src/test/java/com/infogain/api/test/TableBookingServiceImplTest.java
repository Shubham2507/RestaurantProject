package com.infogain.api.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import com.infogain.api.entity.TableBooking;
import com.infogain.api.repository.TableBookingRepository;

import com.infogain.api.service.TableBookingServiceImpl;
@RunWith(SpringRunner.class)
@SpringBootTest(classes=TableBookingServiceImpl.class)
public class TableBookingServiceImplTest {

	@Autowired
	private TableBookingServiceImpl tableBookingService;
	@MockBean
	private TableBookingRepository tableBookingRepo;
	
	TableBooking tableBooking=new TableBooking();
	
	@Test
	public void testgetAllBookedTables()
	{
		List<TableBooking> tableBooking= new ArrayList<>();
		tableBooking.add(new TableBooking(12,"10-11-2018","10:00",2,2,"10-11-2018 10:00","vishu",900809978,1));
		tableBooking.add(new TableBooking(14,"14-11-2018","11:00",2,2,"14-11-2018 11:00","vishu",900809978,1));
		
		when(tableBookingService.getAllBookedTables()).thenReturn(tableBooking);
		List<TableBooking> tableLst = tableBookingService.getAllBookedTables();;
		assertNotNull(tableLst);
	}
	
	@Test
	public void testdeleteOneTableBooking()
	{
		List<TableBooking> tableBooking= new ArrayList<>();
		tableBooking.add(new TableBooking(12,"10-11-2018","10:00",2,2,"10-11-2018 10:00","vishu",900809978,1));
		tableBooking.add(new TableBooking(14,"14-11-2018","11:00",2,2,"14-11-2018 11:00","vishu",900809978,1));
		tableBookingRepo.deleteById(Mockito.anyInt());
		String response =tableBookingService.deleteOneTableBooking(14);
		assertEquals("Deletion Successful", response);	
	}
	
	@Test
	public void testbookTable()
	{
		List<TableBooking> tableBooking= new ArrayList<>();
		TableBooking table=new TableBooking();
		tableBooking.add(new TableBooking(12,"10-11-2018","10:00",2,2,"10-11-2018 10:00","vishu",900809978,1));
		tableBooking.add(new TableBooking(14,"14-11-2018","11:00",2,2,"14-11-2018 11:00","vishu",900809978,1));
		tableBookingService.bookTable(table);
		String response=tableBookingService.bookTable(table);
		assertEquals("Your table booking id is  0", response);
	}
	
	@Test
	public void testupdateTableBooking()
	{
		//List<TableBooking> tableBooking= new ArrayList<>();
		TableBooking table=new TableBooking();
		//tableBooking.add(new TableBooking(12,"10-11-2018","10:00",2,2,"10-11-2018 10:00","vishu",900809978,1));
		//tableBooking.add(new TableBooking(14,"14-11-2018","11:00",2,2,"14-11-2018 11:00","vishu",900809978,1));
		//tableBookingService.updateTableBooking(table);
		//tableBookingRepo.getOne(12);
		table.setBookingDate("12-11-2018");

		table.setMobileNo(98099336);
	
		
		table.setUsername("ueiw");
		table.setIsActive(0);
		when(tableBookingRepo.save(Mockito.any())).thenReturn(table);
		/*String response=tableBookingService.updateTableBooking(table);
		//assertNull(tableBooking);
		assertEquals("Update Successfully!", response);*/
	}
	
}
