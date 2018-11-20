package com.infogain.api.repo;

import org.springframework.stereotype.Repository;

import com.infogain.api.entity.OrderPlaced;

import com.infogain.api.entity.Cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface OrderRepository extends JpaRepository<OrderPlaced, Integer> {
	List<OrderPlaced> findByorderId(int id);
	
	
	
}
