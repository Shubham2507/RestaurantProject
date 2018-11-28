package com.infogain.api.test;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import com.infogain.api.controller.TableBookingController;

import com.infogain.api.entity.TableBooking;

import com.infogain.api.service.TableBookingServiceImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes=TableBookingController.class)
public class TableBookingControllerTest {


	private MockMvc mvc;
	@Autowired
	private WebApplicationContext ctx;

	@MockBean
	private TableBookingServiceImpl tsi;

	List<TableBooking> tableBooking=new ArrayList<>();
	TableBooking table;
	String update="Update Successfully!";
	String delete="Deletion Successful";
	
	@Before
	public void setUp() throws Exception{
		this.mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		table = new TableBooking();
		table.setTableId(15);
		table.setBookingDate("10-11-2018");
		table.setCapacity(2);
		table.setDateTime("10-11-2018 10:00");
		table.setIsActive(0);
		table.setMobileNo(908326893);
		table.setNoOfGuests(3);
		table.setTime("10:00");
		table.setUsername("vishuu");
		tableBooking.add(table);

	}
	

	@Test
	public void testdeleteOneTableBooked() throws Exception
	{
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/poc/tablebooking/12").accept(MediaType.APPLICATION_JSON);
		mvc.perform(requestBuilder).andExpect(status().isOk());
	}

	@Test
	public void testUpdateCart()throws Exception
	{
		Mockito.when(tsi.updateTableBooking(Mockito.any())).thenReturn(update);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/poc/tablebooking")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"tableId\":11,\"time\":\"10:00\",\"noOfGuests\":4,\"capacity\":2,\"dateTime\":\"11-11-2018\",\"username\":\"vishu\",\"mobileNo\":9088504550,\"isActive\":0}")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		String expected="{\"code\":\"200\",\"message\":\"Following Data Found\",\"response\":\"Update Successfully!\"}";
		assertEquals(expected, result.getResponse().getContentAsString());
		System.out.println("updateMedicineForUser successfully executed...");
	}
	
	@Test
	public void testdeleteTableBooked() throws Exception
	{
	
		RequestBuilder requestBuilder1 = MockMvcRequestBuilders.delete("/poc/tablebooking/12").accept(MediaType.APPLICATION_JSON);
		mvc.perform(requestBuilder1).andExpect(status().isOk());	
		MvcResult result = mvc.perform(requestBuilder1).andReturn();
		String expected="Deletion Successful of cartId= 12";
		assertEquals(expected, result.getResponse().getContentAsString());
		System.out.println("Deletion Successful successfully executed...");
		
	}
	
	}

