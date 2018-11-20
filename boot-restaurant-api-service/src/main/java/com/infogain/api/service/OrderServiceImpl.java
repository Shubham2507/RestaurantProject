package com.infogain.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.infogain.api.entity.Cart;
import com.infogain.api.entity.OrderPlaced;
import com.infogain.api.repo.CartRepository;
import com.infogain.api.repo.OrderRepository;

@EnableWebMvc
@Service("orderService")
public class OrderServiceImpl implements IOrderService {
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private CartRepository cartRepo;
	

	@Override
	public List<OrderPlaced> getAllOrder() {
		return orderRepo.findAll();
		
	}

	@Override
	public OrderPlaced findOrderById(int orderId) {
		return orderRepo.findByorderId(orderId);
		
	}

	@Override
	@Transactional
	public List<OrderPlaced> addOrder(OrderPlaced order) {
		
		
		List<Cart>cartItems=cartRepo.getAllByUsername(order.getUsername());
		List<OrderPlaced> orderplaced=new ArrayList<>();
		int orderId = (int) (System.currentTimeMillis() & 0xfffffff);
		OrderPlaced temp=null;
		for(Cart cart:cartItems)
		{
			 temp=new OrderPlaced();
			temp.setCategory(cart.getCategory());
			temp.setDescription(cart.getDescription());
			temp.setItemId(cart.getItemId());
			temp.setItemName(cart.getItemName());
			temp.setOrderStatus("Arriving");
			temp.setQuantity(cart.getQuantity());
			temp.setRate(cart.getRate());
			temp.setTotalPrice(cart.getTotalPrice());
			temp.setUsername(cart.getUsername());
			temp.setOrderId(orderId);
			orderplaced.add(temp);
			orderRepo.save(temp);
			
			cartRepo.deleteByUsername(order.getUsername());
			
		}
	
		return orderplaced ;
	}

	@Override
	@Transactional
	public OrderPlaced updateOrder(OrderPlaced order) {
		OrderPlaced newOrder= orderRepo.getOne(order.getId());
		newOrder.setOrderStatus(order.getOrderStatus());
		orderRepo.save(newOrder);
		return newOrder;
	}

	@Override
	public List<OrderPlaced> getAllUsers(String username) {
		List<OrderPlaced> orderplaced=new ArrayList<>();
		List<OrderPlaced> orderplaced1=new ArrayList<>();
	List<Integer> manualId=orderRepo.findByorderId1(username);
	for(Integer oid:manualId)
	{
		System.out.println(oid);
		
		orderplaced1= orderRepo.getAllByorderId(oid);
		orderplaced.addAll(orderplaced1);

	
	}
	
        return orderplaced;
	}

	

}