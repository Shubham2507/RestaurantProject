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
	public OrderPlaced findOrderById(int orderId) {
		OrderPlaced newOrder=orderRepo.findByorderId(orderId);
		return newOrder;
	}

	@Override
	@Transactional
	public int addOrder(OrderPlaced order) {
		/*CartServiceImpl cartService=new CartServiceImpl();
		Cart cart=cartService.find(order.getUsername());
		OrderedItemServiceImpl temp=new OrderedItemServiceImpl();
		OrderedItem orderItem=null;
		Set<OrderedItem> orderedItemset=new HashSet<>();
		for (Cart cartList : cart)
		{
			orderItem=temp.addOrderedItem(cart);	
			orderedItemset.add(orderItem);
			
		//}
		OrderPlaced orderP=new OrderPlaced();
		orderP.setOrderStatus("Arrived");
		orderP.setOrderedItem(orderedItemset);
		orderP.setUsername(order.getUsername());
		
		
		OrderPlaced newOrder=orderRepo.save(order);*/
		
		
		List<Cart >cartItems=cartRepo.getAllByUsername(order.getUsername());
		
		int order_id = (int) (System.currentTimeMillis() & 0xfffffff);
		
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
			temp.setOrderId(order_id);
			
			orderRepo.save(temp);
			
			cartRepo.deleteByUsername(order.getUsername());
			
		}
	
		return order_id ;//newOrder;
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
