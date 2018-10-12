package com.infogain.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infogain.api.entity.Cart;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

}
