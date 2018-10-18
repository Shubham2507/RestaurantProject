package com.infogain.api.service;

import java.util.List;

import com.infogain.api.entity.Coupon;

public interface ICouponService {
	
	List<Coupon> getAllCoupon();
    Coupon getCouponByCode(String code);
    Coupon addCoupon(Coupon coupon);
    Coupon updateCoupon(Coupon coupon);
    String deleteCoupon(String code);

}
