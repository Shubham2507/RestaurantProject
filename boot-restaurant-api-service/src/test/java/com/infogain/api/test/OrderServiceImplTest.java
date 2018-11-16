package com.infogain.api.test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
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
@SpringBootTest(classes=OrderServiceImpl.class)
public class OrderServiceImplTest {

	@Autowired
	private OrderServiceImpl osi;
	@MockBean
	private OrderRepository orderRepo;


	OrderPlaced orderPlaced=new OrderPlaced();
	List<OrderPlaced> order= new ArrayList<>();
	@Test
	public void testGetAllOrder()throws Exception
	{
		
		order.add(new OrderPlaced(1,123,"Arriving","vishal",2,"testitem",250,"snacks",2,"nothing new",500));
		order.add(new OrderPlaced(2,135,"Placed","vinod",3,"testitem2",100,"snacks",1,"nothing new in this",100));
		when(orderRepo.findAll()).thenReturn(order);
		List<OrderPlaced> orderLst = osi.getAllOrder();
		assertNotNull(orderLst);
	}
	@Test
	public void testAddOrderDetail()throws Exception
	{
		orderPlaced.setId(1);
		orderPlaced.setOrderId(12345);
		orderPlaced.setCategory("snacks");
		orderPlaced.setDescription("nothing new");
		orderPlaced.setItemId(2);
		orderPlaced.setItemName("new");
		orderPlaced.setOrderStatus("placed");
		orderPlaced.setQuantity(1);
		orderPlaced.setRate(100);
		orderPlaced.setTotalPrice(100);
		orderPlaced.setUsername("vishal");
		order.add(orderPlaced);
        when(orderRepo.save(orderPlaced)).thenReturn(orderPlaced);
        List<OrderPlaced> orderPlace=osi.getAllOrder();
        assertNotNull(orderPlace);
        
	}
	@Test
	public void testUpdateOrderDetails()throws Exception
	{
		orderPlaced.setId(1);
		orderPlaced.setOrderId(12345);
		orderPlaced.setCategory("snacks");
		orderPlaced.setDescription("nothing new");
		orderPlaced.setItemId(2);
		orderPlaced.setItemName("new");
		orderPlaced.setOrderStatus("placed");
		orderPlaced.setQuantity(1);
		orderPlaced.setRate(100);
		orderPlaced.setTotalPrice(100);
		orderPlaced.setUsername("vishal");
		 when(orderRepo.save(orderPlaced)).thenReturn(orderPlaced);
	       /*OrderPlaced orderPlace=osi.updateOrder(orderPlaced);
	        assertNotNull(orderPlace);*/
		
		
	}
	
}
