package com.infogain.api.service;

import java.util.List;

import com.infogain.api.dto.OrderHistoryOfUser;
import com.infogain.api.entity.OrderPlaced;


public interface IOrderService {

	List<OrderPlaced> getAllOrder();

	/*OrderPlaced findOrderById(int orderId);*/

	List<OrderPlaced> addOrder(OrderPlaced order);

	OrderPlaced updateOrder(OrderPlaced order);
	
	List<OrderPlaced> getAllUsers(String username);
	/*
	int getManualId(int orderId);*/

	List<OrderHistoryOfUser> getUserDetails();

}
