package com.infogain.api.dto;

import java.util.List;

public class OrderHistory {
	private int orderId;
	private int totalPrice;
	private List<OrderDto> orderdto;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public List<OrderDto> getOrderdto() {
		return orderdto;
	}
	public void setOrderdto(List<OrderDto> orderdto) {
		this.orderdto = orderdto;
	}
	

}
