package com.infogain.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infogain.api.entity.Coupon;
import com.infogain.api.repository.CouponRepository;

@Service
public class CouponService implements ICouponService {

	@Autowired
	private CouponRepository couponRepository;

	@Override
	public List<Coupon> getAllCoupon() {
		return (List<Coupon>) couponRepository.findAll();
	}

	@Override
	public Coupon getCouponByCode(String code) {
		return couponRepository.findByCode(code);
	}

	@Override
	public Coupon addCoupon(Coupon coupon) {
		return couponRepository.save(coupon);
	}

	@Override
	public Coupon updateCoupon(Coupon coupon) {
		return couponRepository.save(coupon);

	}

	@Override
	public String deleteCoupon(String code) {
		couponRepository.delete(couponRepository.findByCode(code));
		return "Coupon deleted";

	}

}
