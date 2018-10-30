package com.infogain.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infogain.api.entity.Cart;
import com.infogain.api.entity.OrderPlaced;
import com.infogain.api.repo.CartRepository;
import com.infogain.api.repo.OrderRepository;


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
	public int addOrder(OrderPlaced order) {
		
		
		List<Cart >cartItems=cartRepo.getAllByUsername(order.getUsername());
		
		int orderId = (int) (System.currentTimeMillis() & 0xfffffff);
		
		for(Cart cart:cartItems)
		{
			OrderPlaced temp=new OrderPlaced();
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
			
			orderRepo.save(temp);
			
			cartRepo.deleteByUsername(order.getUsername());
			
		}
	
		return orderId ;
	}

	@Override
	@Transactional
	public OrderPlaced updateOrder(OrderPlaced order) {
		OrderPlaced newOrder= orderRepo.getOne(order.getId());
		newOrder.setOrderStatus(order.getOrderStatus());
		return newOrder;
	}
	

}
