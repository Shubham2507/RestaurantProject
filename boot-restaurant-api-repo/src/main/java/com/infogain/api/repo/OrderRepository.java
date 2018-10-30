package com.infogain.api.repo;

import org.springframework.stereotype.Repository;

import com.infogain.api.entity.OrderPlaced;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface OrderRepository extends JpaRepository<OrderPlaced, Integer> {
	OrderPlaced findByorderId(int id);
	
	
	
}