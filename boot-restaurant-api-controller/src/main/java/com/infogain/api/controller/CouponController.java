package com.infogain.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.api.entity.Coupon;
import com.infogain.api.exceptionHandler.CouponAlreadyExistsException;
import com.infogain.api.exceptionHandler.CouponListEmptyException;
import com.infogain.api.exceptionHandler.CouponNotFoundException;
import com.infogain.api.service.CouponService;

@RestController
@RequestMapping(value="/coupon")
public class CouponController 
{
	
	@Autowired
	CouponService couponService;
	
	@GetMapping("/{code}")
	public ResponseEntity<Coupon> getCouponByCode(@PathVariable("code") String code) {
		Coupon couponObject= couponService.getCouponByCode(code);
		
		if (couponObject==null)
			throw new CouponNotFoundException("Coupon_Code: " + code);
		
		return new ResponseEntity<Coupon>(couponObject, HttpStatus.OK);
	}
	@GetMapping()
	public ResponseEntity<List<Coupon>> getAllCoupon() {
		List<Coupon> couponList = couponService.getAllCoupon();
		
		if (couponList.isEmpty())
			throw new CouponListEmptyException("Coupon-List : ");
		
		return new ResponseEntity<List<Coupon>>(couponList, HttpStatus.OK);
	}
	@PostMapping()
	public ResponseEntity<Coupon> addCoupon(@Valid @RequestBody Coupon coupon)
	{            	
		Coupon couponObject= couponService.getCouponByCode(coupon.getCode());

		if (couponObject!=null)
			throw new CouponAlreadyExistsException("Coupon : "+coupon.getCode());
        couponService.addCoupon(coupon);
        return new ResponseEntity<Coupon>(coupon,HttpStatus.OK);    
	}
	
	@PutMapping()
	public ResponseEntity<Coupon> updateCoupon(@RequestBody Coupon coupon) {
		Coupon couponObject= couponService.getCouponByCode(coupon.getCode());

		if (couponObject==null)
			throw new CouponNotFoundException("Coupon : "+coupon.getCode());
		couponService.updateCoupon(coupon);
		return new ResponseEntity<Coupon>(coupon, HttpStatus.OK);
	}
	@DeleteMapping("/{code}")
	public ResponseEntity<?> deleteCoupon(@PathVariable("code") String code) {
		Coupon couponObject= couponService.getCouponByCode(code);

		if (couponObject==null)
			throw new CouponNotFoundException("Coupon_Code: " + code);
		
		couponService.deleteCoupon(code);
		return new ResponseEntity<String>("Coupon Deleted",HttpStatus.GONE);
	}	


}
