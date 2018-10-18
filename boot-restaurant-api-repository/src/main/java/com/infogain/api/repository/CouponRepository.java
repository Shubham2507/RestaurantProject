package com.infogain.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.infogain.api.entity.Coupon;

public interface CouponRepository extends CrudRepository<Coupon, String>{

	Coupon findByCode(String code);
}
