package com.infogain.api.repository;

import org.springframework.stereotype.Repository;

import com.infogain.api.entity.OrderPlaced;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface OrderRepository extends JpaRepository<OrderPlaced, Integer> {
	OrderPlaced findByorderId(int id);

	
	@Query(value="SELECT DISTINCT manual_order_id FROM Order_Placed WHERE Username= ?1 ", nativeQuery= true)
	List<Integer>  findByorderId1(String username);
	/*
	@Query(value="SELECT * FROM Order_Placed WHERE manual_order_id = ?1",nativeQuery=true)
	List<OrderPlaced> getAllByorderId(int oId);
	*/
	@Query(value="SELECT * FROM Order_Placed WHERE manual_order_id = ?1 HAVING COUNT(auto_id)>1",nativeQuery=true)
	List<OrderPlaced> getAllByorderId1(int oId);
	

	@Query(value="SELECT * FROM Order_Placed WHERE manual_order_id = ?1",nativeQuery=true)
	List<List<OrderPlaced>> getAllByorderId(int oId);
	
}