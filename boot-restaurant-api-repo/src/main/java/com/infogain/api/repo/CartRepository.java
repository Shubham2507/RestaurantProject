package com.infogain.api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infogain.api.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	Cart findByItemIdAndUsername(int itemId, String username);
	 List<Cart> getAllByUsername(String username);
	 String deleteByUsername(String username);

}
