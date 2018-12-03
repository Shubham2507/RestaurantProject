package com.infogain.api.testService;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.infogain.api.RestaurantApplication;
import com.infogain.api.entity.CustomerFeedback;
import com.infogain.api.repository.CustomerFeedbackRepository;
import com.infogain.api.service.CustomerFeedbackService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestaurantApplication.class)
public class CustomerFeedbackServiceTest {

	@Mock
	private CustomerFeedbackRepository customerFeedbackRepository;

	@InjectMocks
	private CustomerFeedbackService customerFeedbackService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

	}

	private CustomerFeedback customerFeedbackObject() throws ParseException {

		return new CustomerFeedback("khushboo", 5, 5, 5, "Excellent Service");
	}

	private List<CustomerFeedback> customerFeedbackList() throws ParseException {

		List<CustomerFeedback> customerFeedbackList = new ArrayList<>();
		CustomerFeedback customerFeedback = customerFeedbackObject();
		customerFeedbackList.add(customerFeedback);
		return customerFeedbackList;

	}

	@Test
	public void testGetCustomerFeedbackByFeedbackId() throws ParseException {
		Mockito.when(customerFeedbackRepository.findByFeedbackId(Mockito.anyInt()))
				.thenReturn(customerFeedbackObject());
		CustomerFeedback customerFeedback = customerFeedbackService.getCustomerFeedbackByFeedbackId(Mockito.anyInt());

		assertEquals(1, customerFeedback.getFeedbackId());

	}

	@Test
	public void testGetCustomerFeedbackByUserId() throws ParseException {
		Mockito.when(customerFeedbackRepository.findByUsername(Mockito.anyString()))
				.thenReturn(customerFeedbackList());
		List<CustomerFeedback> customerFeedback = customerFeedbackService.getCustomerFeedbackByUsername(Mockito.anyString());

		assertEquals(1, customerFeedback.size());

	}

	@Test
	public void testGetAllCustomerFeedback() throws ParseException {

		Mockito.when(customerFeedbackRepository.findAll()).thenReturn(customerFeedbackList());

		List<CustomerFeedback> customerFeedbackList = customerFeedbackService.getAllCustomerFeedback();

		assertEquals(1, customerFeedbackList.size());

	}

	@Test

	public void testAddCustomerFeedback() throws ParseException {

		Mockito.when(customerFeedbackRepository.save(Mockito.any())).thenReturn(customerFeedbackObject());

		CustomerFeedback customerFeedback = customerFeedbackService.addCustomerFeedback(Mockito.any());

		assertEquals(1, customerFeedback.getFeedbackId());

	}

	@Test

	public void testUpdateCustomerFeedback() throws ParseException {

		Mockito.when(customerFeedbackRepository.save(Mockito.any())).thenReturn(customerFeedbackObject());
		CustomerFeedback customerFeedback= customerFeedbackService.updateCustomerFeedback(Mockito.any());

		assertEquals(100, customerFeedback.getUsername());
	}

	@Test

	public void testDeleteCustomerFeedback() throws ParseException {

		Mockito.when(customerFeedbackRepository.findByFeedbackId(Mockito.anyInt())).thenReturn(customerFeedbackObject());
		String msg = customerFeedbackService.deleteCustomerFeedback(Mockito.anyInt());

		assertEquals("Customer Feedback deleted", msg);
	}

}
