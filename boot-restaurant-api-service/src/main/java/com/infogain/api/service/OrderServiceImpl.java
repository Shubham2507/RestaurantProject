package com.infogain.api.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.infogain.api.config.JwtAuthenticationFilter;
import com.infogain.api.dto.OrderDto;
import com.infogain.api.dto.OrderHistory;
import com.infogain.api.dto.OrderHistoryOfUser;
import com.infogain.api.entity.Cart;
import com.infogain.api.entity.OrderPlaced;
import com.infogain.api.repo.CartRepository;
import com.infogain.api.repo.OrderRepository;

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

	/*@Override
	public OrderPlaced findOrderById(int orderId) {
		return orderRepo.findByorderId(orderId);

	}*/

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
		List<Integer> manualId=orderRepo.findDistinctByUsername(username);

		for(Integer oid:manualId)
		{

			System.out.println(oid);


			orderplaced.addAll(orderplaced1);


		}

		return orderplaced;
	}



	public List<OrderHistoryOfUser> getUserDetails()
	{
		List<Integer> id=orderRepo.findDistinctByUsername(jaf.getUsername());

		OrderHistoryOfUser odhi=null;
		List<OrderHistory> listofOrder= new ArrayList<>();
		List<OrderHistoryOfUser> listofOrderHistory= null;
		OrderHistory orderhistory = null;
		List<OrderPlaced> od=null;
		List<OrderDto> orderDto=null;
		for(Integer i:id)
		{
			listofOrderHistory= new ArrayList<>();

			orderhistory=new OrderHistory();
			odhi= new OrderHistoryOfUser();
			orderhistory.setOrderId(i);
			od=orderRepo.findByOrderId(i);
			orderDto=orderPlacedtoDto(od);
			orderhistory.setOrderdto(orderDto);
			int totalPrice=orderRepo.totalPrice(jaf.getUsername(),i);
			orderhistory.setTotalPrice(totalPrice);
			listofOrder.add(orderhistory);
			/*odhi.setOrders(listofOrder);*/
			listofOrderHistory.add(odhi);

		}

		List<OrderHistoryOfUser> it=iterate(listofOrder);
		return it;
	}

	public List<OrderDto> orderPlacedtoDto(List<OrderPlaced> op)
	{
		List<OrderDto> dtoList=new ArrayList<>();
		OrderDto od=null;
		for(OrderPlaced o:op)
		{
			od=new OrderDto();
			od.setCategory(o.getCategory());
			od.setDescription(o.getDescription());
			od.setItemName(o.getItemName());
			od.setOrderStatus(o.getOrderStatus());
			od.setQuantity(o.getQuantity());
			dtoList.add(od);
		}

		return dtoList;
	}
	public List<OrderHistoryOfUser> iterate(List<OrderHistory> list)
	{
		OrderHistoryOfUser ou=null;
		List<OrderHistoryOfUser> al=new ArrayList<>();

		OrderHistory[] orders=null;

		for(OrderHistory o:list)
		{
			orders=new OrderHistory[1];
			ou=new OrderHistoryOfUser();

			orders[0]=o;

			ou.setOrders(orders);

			al.add(ou);	
		}


		return al;
	}}

