package com.infogain.api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infogain.api.entity.OrderPlaced;


@Repository
public interface OrderRepository extends JpaRepository<OrderPlaced, Integer> {
	List<OrderPlaced> findByOrderId(int id);
	
	
	@Query(value="SELECT DISTINCT manual_order_id FROM Order_Placed WHERE Username = ?1",nativeQuery=true)
	List<Integer>  findDistinctByUsername(String userName);
	
	
	@Query(value="SELECT sum(total_price) FROM Order_Placed WHERE Username = ?1 and manual_order_id= ?2 ",nativeQuery=true)
	int totalPrice(String username,int id);
	
	@Query(value="SELECT count(manual_order_id) FROM Order_Placed WHERE manual_order_id = ?1",nativeQuery=true)
	int numberOfItem(int manual_order_id);
	
	
	
}
