package com.infogain.api.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.infogain.api.entity.Coupon;
import com.infogain.api.exceptionHandler.CouponAlreadyExistsException;
import com.infogain.api.exceptionHandler.CouponExpiredException;
import com.infogain.api.exceptionHandler.CouponListEmptyException;
import com.infogain.api.exceptionHandler.CouponNotFoundException;
import com.infogain.api.response.ResponseData;
import com.infogain.api.service.CouponService;
import com.infogain.api.service.ICouponService;

@RestController
@EnableWebMvc
@RequestMapping(value = "/coupon")
public class CouponController {

	@Autowired
	ICouponService couponService;	

	@GetMapping()
	public ResponseEntity<?> getAllCoupon()
	{
		List<Coupon> couponList = couponService.getAllCoupon();

		if (couponList==null || couponList.isEmpty() )
			throw new CouponListEmptyException("Coupon-List : ");

		return new ResponseEntity<> (new ResponseData("200",couponList),HttpStatus.OK);
	}
	
/*	@GetMapping(value = "/filter/{amount}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Coupon>> getByBillAmount(@PathVariable ("billAmount") int billAmount)
	{
		List<Coupon> couponListByBillAmount = couponService.getByBillAmount(billAmount);

		if (couponListByBillAmount==null || couponListByBillAmount.isEmpty() )
			throw new CouponListEmptyException("Coupon-List : ");

		return new ResponseEntity<List<Coupon>>(couponListByBillAmount, HttpStatus.OK);
	}*/

	@GetMapping(value = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCouponByCode(@PathVariable("code") String code)
	{
		Coupon couponObject = couponService.getCouponByCode(code);

		if (couponObject == null)
			throw new CouponNotFoundException("Coupon_Code: " + code);

		System.out.println("release date: "+couponObject.getReleaseDate());
		System.out.println("expiry date: "+couponObject.getExpiryDate());
		return new ResponseEntity<> (new ResponseData("200",couponObject),HttpStatus.OK);
	}
	
	
		@GetMapping(value="/filter/{billAmount}", produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> getByBillAmount(@PathVariable ("billAmount") int billAmount)
		{
		
		List<Coupon> eligibleCouponList= couponService.getByBillAmount(billAmount);
	
			if(eligibleCouponList==null || eligibleCouponList.isEmpty())
			{			
				//System.out.println("List size is 0");
				throw new CouponListEmptyException("Coupon-List : ");
			}
				
			else
			{	
				//System.out.println("List size is more than 0");
				return  new ResponseEntity<>(new ResponseData("200",eligibleCouponList),HttpStatus.OK);
			}
	
		}
		
		@GetMapping(value="/{code}/{billAmount}", produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> applyCoupon(@PathVariable ("code") String code,@PathVariable ("billAmount") int billAmount)
		{
			float maxOffer = 0;
			float minOffer = 0;
			float off = 0;
			float netAmount=0;
			Coupon couponObject = couponService.getCouponByCode(code);
			System.out.println("Got coupon with code: "+code);
			
			if (couponObject != null)
			{

				Date expiryDate = couponObject.getExpiryDate();
				System.out.println("expiry date in controller: " + expiryDate);
				Calendar rightNow = Calendar.getInstance();
				rightNow.clear(Calendar.HOUR);
				rightNow.clear(Calendar.MINUTE);
				rightNow.clear(Calendar.SECOND);
				rightNow.clear(Calendar.MILLISECOND);
				Date today = rightNow.getTime();
				System.out.println("today in controller: " + today);
				if (expiryDate.compareTo(today) >= 0) 
				{
					System.out.println("yes coupon has not yet expired");
					
					if(couponObject.getQuantity()<=0)
						throw new CouponNotFoundException("Coupon with code: " + code);
	
					/*int quantity = couponObject.getQuantity();
					quantity -= 1;
					couponObject.setQuantity(quantity);*/
	
					minOffer = (billAmount) * (couponObject.getDiscountPercentage() / 100);
					 System.out.println("Discount value: "+minOffer);
					maxOffer = couponObject.getMaximumDiscount();
					 System.out.println("Maximum Offer: "+maxOffer);
					if (minOffer <= maxOffer) {
						netAmount = billAmount - minOffer;
						 System.out.println("Net amount after discount is: "+netAmount);
					} else {
						netAmount = billAmount - maxOffer;
						 System.out.println("Net amount after discount is: "+netAmount);
					}
					couponService.updateCoupon(couponObject);
	
					return new ResponseEntity<>(new ResponseData("200",netAmount),HttpStatus.OK);
				}
	
				else
				{
					System.out.println("Coupon has expired");
					//return new ResponseEntity<String>("Coupon has expired", HttpStatus.NOT_FOUND);
					throw new CouponExpiredException("Coupon with code: " + code);

				}
	
			} else {
				throw new CouponNotFoundException("Coupon with code: " + code);
	
			}
	
		}

	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addCoupon(@RequestBody @Valid Coupon coupon) {
		Coupon couponObject = couponService.getCouponByCode(coupon.getCode());

		if (couponObject != null)
			throw new CouponAlreadyExistsException("Coupon : " + coupon.getCode());
		couponService.addCoupon(coupon);
		return new ResponseEntity<> (new ResponseData("200",coupon),HttpStatus.OK);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCoupon(@RequestBody @Valid Coupon coupon) {
		Coupon couponObject = couponService.getCouponByCode(coupon.getCode());

		if (couponObject == null)
			throw new CouponNotFoundException("Coupon : " + coupon.getCode());
		couponService.updateCoupon(coupon);
		return new ResponseEntity<>(new ResponseData("200",coupon),HttpStatus.OK);
	}

	@DeleteMapping(value = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteCouponByCode(@PathVariable("code") String code) {
		Coupon couponObject = couponService.getCouponByCode(code);

		if (couponObject == null)
			throw new CouponNotFoundException("Coupon_Code: " + code);

		couponService.deleteCoupon(code);
		return new ResponseEntity<>(new ResponseData("410",couponObject),HttpStatus.GONE);
	}

}

