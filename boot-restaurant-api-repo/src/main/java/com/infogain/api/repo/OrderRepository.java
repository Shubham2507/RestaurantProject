package com.infogain.api.repo;

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
	
	@Query(value="SELECT * FROM Order_Placed WHERE manual_order_id = ?1",nativeQuery=true)
	List<OrderPlaced> getAllByorderId(int oId);
}