package com.infogain.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.infogain.api.config.JwtAuthenticationFilter;
import com.infogain.api.entity.Cart;
import com.infogain.api.entity.OrderPlaced;
import com.infogain.api.repository.CartRepository;
import com.infogain.api.repository.OrderRepository;

@EnableWebMvc
@Service("orderService")
public class OrderServiceImpl implements IOrderService {
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private CartRepository cartRepo;
	 @Autowired
		private JwtAuthenticationFilter jaf;
	

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
		
		
		List<Cart>cartItems=cartRepo.getAllByUsername(jaf.getUsername());
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
			temp.setUsername(jaf.getUsername());
			temp.setOrderId(orderId);
			orderplaced.add(temp);
			orderRepo.save(temp);
			
			cartRepo.deleteByUsername(jaf.getUsername());
			
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
		
		//orderplaced1= orderRepo.getAllByorderId(oid);
		orderplaced.addAll(orderplaced1);

	
	}
	
        return orderplaced;
	}

	public List<List<OrderPlaced>> getUserDetails(String username)
	{
		List<List<OrderPlaced>> listOLists = new ArrayList<List<OrderPlaced>>();
		//List<List<OrderPlaced>> listOLists1 = new ArrayList<List<OrderPlaced>>();
		List<OrderPlaced> singleList = new ArrayList<OrderPlaced>();
		List<OrderPlaced> singleList1 = new ArrayList<OrderPlaced>();
		List<OrderPlaced> singleList2 = new ArrayList<OrderPlaced>();
		List<OrderPlaced> singleList3 = new ArrayList<OrderPlaced>();
		List<Integer> manualId=orderRepo.findByorderId1(username);
		
		for(Integer oid:manualId)
		{
			System.out.println(oid);
		
			singleList1=orderRepo.getAllByorderId1(oid);
			singleList.addAll(singleList1);
		/*	listOLists1=orderRepo.getAllByorderId(oid);
			listOLists.addAll(listOLists1);*/
		//	singleList2=orderRepo.getAllByorderId(oid);
			singleList3.addAll(singleList2);
			listOLists.add(singleList);
			//listOLists.add(singleList3);
			//listOLists.addAll( orderRepo.getAllByorderId(oid));
		
		}
		
		
		return listOLists;
		

	}

	@Override
	public List<Map<Integer, ArrayList<OrderPlaced>>> getUsers(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*List<Integer> singleList = new ArrayList<Integer>();
	List<OrderPlaced> orderHistory = new ArrayList<OrderPlaced>();
	
public List<Integer> getAllOredrId(String username)
{
	
	singleList=orderRepo.findByorderId1(username);
	return singleList;
	
}

public List<OrderPlaced> getAllOredrHistory(List<Integer> id)
{
	
	orderHistory=orderRepo.getAllByorderId(id);
	return orderHistory;
	
}

@Override
public List<List<OrderPlaced>> getUserDetails(String username) {
	// TODO Auto-generated method stub
	return null;
}
*/
	/*@Override
	public List<Map<Integer, ArrayList<OrderPlaced>>> getUsers(String username)
	{
		

		Map<Integer,ArrayList<OrderPlaced>> multiMap = new HashMap<>();
		List<Integer> manualId=orderRepo.findByorderId1(username);
		for(Integer oid:manualId)
		{
			System.out.println(oid);
			
			int tot=orderRepo.totalPrice(oid);
		Historyu h= new Historyu(oid,tot);
	
			multiMap.put(h.getoId(), orderRepo.getAllByorderId3(oid));
		}
		List<Map<Integer, ArrayList<OrderPlaced>>>  list1= new ArrayList<>();
		list1.add(multiMap);
		return list1;
		
	}
*/
	

}
