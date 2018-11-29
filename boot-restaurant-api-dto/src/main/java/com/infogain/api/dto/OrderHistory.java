package com.infogain.api.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderHistory {
	private int orderId;
	private int totalPrice;
	private List<OrderDto> orderdto;

}
