package com.infogain.api.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		List<OrderPlaced> order = orderRepo.findAll();
		return order;
	}

	@Override
	public List<OrderPlaced> findOrderById(int orderId) {
		List<OrderPlaced> newOrder=orderRepo.findByorderId(orderId);
		return newOrder;
	}

	@Override
	@Transactional
	public OrderPlaced addOrder(OrderPlaced order) {

		List<Cart >cartItems=cartRepo.getAllByUsername(order.getUsername());

		int order_id = (int) (System.currentTimeMillis() & 0xfffffff);
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
			temp.setOrderId(order_id);

			orderRepo.save(temp);

			cartRepo.deleteByUsername(order.getUsername());

		}

		return temp;//newOrder;
	}

	@Override
	@Transactional
	public OrderPlaced updateOrder(OrderPlaced Order) {
		OrderPlaced newOrder= orderRepo.getOne(Order.getId());
		newOrder.setOrderStatus(Order.getOrderStatus());
		return newOrder;
	}
	/*@Override
	public Cart find(String username)
	{
		Cart tempCart=cartRepo.findByUsername(username);
		return tempCart;
	}*/

}
