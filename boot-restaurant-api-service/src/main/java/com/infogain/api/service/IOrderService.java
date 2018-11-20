package com.infogain.api.service;

import java.util.List;

import com.infogain.api.entity.Cart;
import com.infogain.api.entity.OrderPlaced;


public interface IOrderService {

	List<OrderPlaced> getAllOrder();

	List<OrderPlaced> findOrderById(int orderId);

	OrderPlaced addOrder(OrderPlaced order);

	OrderPlaced updateOrder(OrderPlaced order);
	/* Cart find(String username); */
}
