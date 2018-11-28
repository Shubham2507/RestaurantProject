package com.infogain.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.infogain.api.entity.OrderPlaced;


public interface IOrderService {

	List<OrderPlaced> getAllOrder();

	OrderPlaced findOrderById(int orderId);

	List<OrderPlaced> addOrder(OrderPlaced order);

	OrderPlaced updateOrder(OrderPlaced order);
	
	List<OrderPlaced> getAllUsers(String username);
	/*
	int getManualId(int orderId);*/

	List<List<OrderPlaced>> getUserDetails(String username);
	
	List<Map<Integer, ArrayList<OrderPlaced>>> getUsers(String username);




}
