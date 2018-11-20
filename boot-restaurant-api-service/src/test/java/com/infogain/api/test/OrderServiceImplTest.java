package com.infogain.api.test;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.model.InitializationError;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringRunner;

import com.infogain.api.entity.OrderPlaced;
import com.infogain.api.repo.OrderRepository;
import com.infogain.api.service.OrderServiceImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest
public class OrderServiceImplTest {

	@Autowired
	private OrderServiceImpl osi;

	@MockBean
	private OrderRepository orderRepo;	

	@Test
	public void testgetAllOrder()throws InitializationError 
	{

		List<OrderPlaced> orderPlaced=new ArrayList<>();
		orderPlaced.add(new OrderPlaced(1,1345,"Arriving","vishal",4,"Garlic bread",200,"snacks",2,"Gralicdipped",400));
		orderPlaced.add(new OrderPlaced(2,134567,"Arriving","vishal",6,"Garlic bread",200,"snacks",2,"Gralicdipped",400));
		Mockito.when(osi.getAllOrder()).thenReturn(orderPlaced);
		List<OrderPlaced> order=osi.getAllOrder();
		assertNotNull(order);

	}
	@Test
	public void testfindOrderById()
	{
		List<OrderPlaced> orderPlaced=new ArrayList<>();
		orderPlaced.add(new OrderPlaced(1,1345,"Arriving","vishal",4,"Garlic bread",200,"snacks",2,"Gralicdipped",400));
		orderPlaced.add(new OrderPlaced(2,134567,"Arriving","vishal",6,"Garlic bread",200,"snacks",2,"Gralicdipped",400));
		Mockito.when(osi.findOrderById(Mockito.anyInt())).thenReturn(orderPlaced);
		List<OrderPlaced> order=osi.getAllOrder();
		assertNotNull(order);
	}
	@Test
	public void testaddOrder()
	{
		OrderPlaced order=new OrderPlaced();
		order.setId(1);
		order.setOrderId(1234589);
		order.setOrderStatus("Arriving");
		order.setUsername("priyanka");
		order.setItemId(4);
		order.setItemName("Bread");
		order.setRate(300);
		order.setCategory("snacks");
		order.setQuantity(2);
		order.setDescription("");
		order.setTotalPrice(600);
		Mockito.when(osi.addOrder(Mockito.any())).thenReturn(order);
		OrderPlaced order1=osi.addOrder(order);
		assertNotNull(order1);
	}
	@Test
	public void testupdateOrder()
	{
		OrderPlaced order=new OrderPlaced();
		order.setId(1);
		order.setOrderId(1234589);
		order.setOrderStatus("Arriving");
		order.setUsername("priyanka");
		order.setItemId(4);
		order.setItemName("Bread");
		order.setRate(300);
		order.setCategory("snacks");
		order.setQuantity(2);
		order.setDescription("");
		order.setTotalPrice(600);
		Mockito.when(osi.addOrder(Mockito.any())).thenReturn(order);
		OrderPlaced order1=osi.addOrder(order);
		assertNotNull(order1);
	}
}
