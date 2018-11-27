package com.infogain.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infogain.api.entity.Coupon;

@Repository
public interface CouponRepository extends CrudRepository<Coupon, String>{

	Coupon findByCode(String code);
	
	@Query("SELECT c FROM Coupon c WHERE c.billAmount<=:billAmount")
    List<Coupon> findByBillAmount(@Param("billAmount") int billAmount);
}
