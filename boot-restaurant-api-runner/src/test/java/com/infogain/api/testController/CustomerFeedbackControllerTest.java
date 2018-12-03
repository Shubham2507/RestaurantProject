package com.infogain.api.testController;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.infogain.api.controller.CustomerFeedbackController;
import com.infogain.api.entity.CustomerFeedback;
import com.infogain.api.service.ICustomerFeedbackService;

@SpringBootTest(classes = CustomerFeedbackController.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerFeedbackControllerTest {


	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wc;

	@MockBean
	private ICustomerFeedbackService customerFeedbackService;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();

	}

	private String customerFeedbackObjectJson = "{\"feedbackId\":1,\"username\":\"khushboo\",\"foodRating\":5,\"serviceRating\":4,\"ambienceRating\":5,\"comment\":\"Excellent Service\"}";

	private String customerFeedbackObjectJsonInvalid = "{\"feedbackId\":1,\"username\":\"khushboo\",\"foodRating\":8,\"serviceRating\":8,\"ambienceRating\":8,\"comment\":\"Excellent Service\"}";


	private String customerFeedbackObjectJsonInvalidNegative = "{\"feedbackId\":1,\"username\":\"khushboo\",\"foodRating\":-3,\"serviceRating\":-4,\"ambienceRating\":-5,\"comment\":\"Excellent Service\"}";

	
	private CustomerFeedback customerFeedbackObject() throws ParseException {
		
		return new CustomerFeedback("khushboo", 5, 4, 5, "Excellent Service");

	}


	private List<CustomerFeedback> customerFeedbackList() throws ParseException {

		List<CustomerFeedback> customerFeedbackList = new ArrayList<>();
		CustomerFeedback customerFeedback= customerFeedbackObject();
		customerFeedbackList.add(customerFeedback);
		return customerFeedbackList;

	}
	
	@Test
	public void testGetAllCustomerFeedback() throws Exception {
		Mockito.when(customerFeedbackService.getAllCustomerFeedback()).thenReturn(customerFeedbackList());

		mockMvc.perform(MockMvcRequestBuilders.get("/customerfeedback")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
                //.andExpect(jsonPath("$", hasSize(1)))
				.andDo(print());

}
	
	@Test
	public void testGetAllCustomerFeedbackNullList() throws Exception {
		Mockito.when(customerFeedbackService.getAllCustomerFeedback()).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/customerfeedback")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andDo(print());

	}
	
	@Test
	public void testGetAllCustomerFeedbackEmptyList() throws Exception {
		Mockito.when(customerFeedbackService.getAllCustomerFeedback()).thenReturn(new ArrayList<>());
		mockMvc.perform(MockMvcRequestBuilders.get("/customerfeedback")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andDo(print());

	}
	
	
	@Test
	public void testGetCustomerFeedbackByUserId() throws Exception {
		Mockito.when(customerFeedbackService.getCustomerFeedbackByUsername(Mockito.anyString())).thenReturn(customerFeedbackList());

		mockMvc.perform(MockMvcRequestBuilders.get("/customerfeedback/user/"+100)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());

}
	
	@Test
	public void testGetCustomerFeedbackByUserIdNullList() throws Exception {
		Mockito.when(customerFeedbackService.getAllCustomerFeedback()).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/customerfeedback/user/"+100)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andDo(print());

	}
	
	@Test
	public void testGetCustomerFeedbackByUserIdEmptyList() throws Exception {
		Mockito.when(customerFeedbackService.getAllCustomerFeedback()).thenReturn(new ArrayList<>());
		mockMvc.perform(MockMvcRequestBuilders.get("/customerfeedback/user/"+100)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andDo(print());

	}
	
	
	@Test
	public void testGetCustomerFeedbackByFeedbackId() throws Exception {

		Mockito.when(customerFeedbackService.getCustomerFeedbackByFeedbackId(Mockito.anyInt())).thenReturn(customerFeedbackObject());

		mockMvc.perform(get("/customerfeedback/" + customerFeedbackObject().getFeedbackId())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("response.feedbackId", is(customerFeedbackObject().getFeedbackId())))
				.andDo(print());
	}
	
	@Test
	public void testGetCustomerFeedbackByFeedbackIdNull() throws Exception {

		Mockito.when(customerFeedbackService.getCustomerFeedbackByFeedbackId(Mockito.anyInt())).thenReturn(null);

		mockMvc.perform(get("/customerfeedback/" + customerFeedbackObject().getFeedbackId())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
			//	.andExpect(jsonPath("response.feedbackId", is(customerFeedbackObject().getFeedbackId())))
				.andDo(print());
	}
	
	
	@Test
	public void testAddCustomerFeedback() throws Exception {

	//	Mockito.when(customerFeedbackService.addCustomerFeedback(Mockito.any())).thenReturn(customerFeedbackObject());

		mockMvc.perform(post("/customerfeedback")
				.contentType(MediaType.APPLICATION_JSON)
				.content(customerFeedbackObjectJson)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void testAddCustomerFeedbackNull() throws Exception {

		mockMvc.perform(post("/customerfeedback")
				.contentType(MediaType.APPLICATION_JSON)
				.content(" ")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andDo(print());
	}

	
	@Test 
	public void testAddCustomerFeedbackInvalid() throws Exception {
		//Mockito.when(customerFeedbackService.addCustomerFeedback(Mockito.any())).thenReturn(customerFeedbackObjectInvalid());

		mockMvc.perform(post("/customerfeedback")
				.contentType(MediaType.APPLICATION_JSON)
				.content(customerFeedbackObjectJsonInvalid)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andDo(print());
	}
	
	@Test 
	public void testAddCustomerFeedbackInvalidNegative() throws Exception {
		//Mockito.when(customerFeedbackService.addCustomerFeedback(Mockito.any())).thenReturn(customerFeedbackObjectInvalid());

		mockMvc.perform(post("/customerfeedback")
				.contentType(MediaType.APPLICATION_JSON)
				.content(customerFeedbackObjectJsonInvalidNegative)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andDo(print());
	}

	@Test 
	public void testUpdateCustomerFeedback() throws Exception {

		Mockito.when(customerFeedbackService.getCustomerFeedbackByFeedbackId(Mockito.anyInt())).thenReturn(customerFeedbackObject());

		mockMvc.perform(put("/customerfeedback")
				.contentType(MediaType.APPLICATION_JSON)
				.content(customerFeedbackObjectJson)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("response.feedbackId").exists())
				.andDo(print());

	}

	@Test 
	public void testUpdateCustomerFeedbackNull() throws Exception {

		Mockito.when(customerFeedbackService.getCustomerFeedbackByFeedbackId(Mockito.anyInt())).thenReturn(null);

		mockMvc.perform(put("/customerfeedback")
				.contentType(MediaType.APPLICATION_JSON)
				.content(customerFeedbackObjectJson)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andDo(print());

	}
	
		@Test 	
		public void testUpdateCustomerFeedbackInvalid() throws Exception {
		
		Mockito.when(customerFeedbackService.getCustomerFeedbackByFeedbackId(Mockito.anyInt())).thenReturn(customerFeedbackObject());
		
		mockMvc.perform(put("/customerfeedback")
				.contentType(MediaType.APPLICATION_JSON)
				.content(customerFeedbackObjectJsonInvalid)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andDo(print());
		
		}

	@Test
	public void testDeleteCustomerFeedback() throws Exception {

		Mockito.when(customerFeedbackService.getCustomerFeedbackByFeedbackId(Mockito.anyInt())).thenReturn(customerFeedbackObject());

		mockMvc.perform(delete("/customerfeedback/" + customerFeedbackObject().getFeedbackId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isGone())
				.andDo(print());

	}

	
	@Test// deleting a coupon with invalid coupon code
	public void testDeleteCustomerFeedbackNull() throws Exception {

		Mockito.when(customerFeedbackService.getCustomerFeedbackByFeedbackId(Mockito.anyInt())).thenReturn(null);

		mockMvc.perform(delete("/customerfeedback/" + customerFeedbackObject().getFeedbackId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andDo(print());

	}

	

}
