/*package com.infogain.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infogain.api.entity.Cart;
import com.infogain.api.entity.OrderedItem;

import com.infogain.api.repo.OrderedItemRepository;

@Service("orderedItemService")
public class OrderedItemServiceImpl implements IOrderedItemService{
	@Autowired
	private OrderedItemRepository oitemRepo;
	
	@Override
	public List<OrderedItem> getAllOrderedItem() {
		List<OrderedItem> orderedItem = oitemRepo.findAll();
		return orderedItem;
	}

	@Override
	public List<OrderedItem> findByUsername(String username) {
		List<OrderedItem> orderedItem =oitemRepo.findByUsername(username);
		return orderedItem;
	}

	@Override
	public OrderedItem addOrderedItem(OrderedItem orderItem) {
		OrderedItem newOrderedItem = new OrderedItem();
		List<OrderedItem> orderList= oitemRepo.findByUsername(orderItem.getUsername());
		for(OrderedItem tempOrderedItem: orderList)
		{
		newOrderedItem.setItemId(tempOrderedItem.getItemId());
		newOrderedItem.setItemName(tempOrderedItem.getItemName());
		newOrderedItem.setCategory(tempOrderedItem.getCategory());
		newOrderedItem.setDescription(tempOrderedItem.getDescription());
		newOrderedItem.setQuantity(tempOrderedItem.getQuantity());
		newOrderedItem.setRate(tempOrderedItem.getRate());
		newOrderedItem.setTotalPrice(tempOrderedItem.getTotalPrice());
		newOrderedItem.setUsername(tempOrderedItem.getUsername());
		oitemRepo.save(newOrderedItem);
	     }
		return newOrderedItem;
	}

}
*/