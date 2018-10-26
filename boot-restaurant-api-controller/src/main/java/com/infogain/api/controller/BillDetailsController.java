package com.infogain.api.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.api.entity.BillDetails;
import com.infogain.api.entity.Coupon;
import com.infogain.api.exceptionHandler.BillDetailsAlreadyExistsException;
import com.infogain.api.exceptionHandler.BillDetailsListEmptyException;
import com.infogain.api.exceptionHandler.BillDetailsNotFoundException;
import com.infogain.api.exceptionHandler.CouponNotFoundException;
import com.infogain.api.service.BillDetailsService;
import com.infogain.api.service.CouponService;

@RestController
@RequestMapping(value = "/billDetails")
public class BillDetailsController {

	@Autowired
	BillDetailsService billDetailsService;

	@Autowired
	CouponService couponService;

	@GetMapping("/{billId}")
	public ResponseEntity<BillDetails> getBillDetailsBybillId(@PathVariable("billId") int billId) {
		BillDetails billDetailsObject = billDetailsService.getBillDetailsBybillId(billId);

		if (billDetailsObject == null)
			throw new BillDetailsNotFoundException("Bill with Id: " + billId);

		return new ResponseEntity<BillDetails>(billDetailsObject, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<BillDetails>> getAllBillDetails() {
		List<BillDetails> billDetailsList = billDetailsService.getAllBillDetails();

		if (billDetailsList.isEmpty())
			throw new BillDetailsListEmptyException("Bill-Details List : ");

		return new ResponseEntity<List<BillDetails>>(billDetailsList, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<?> addBillDetails(@RequestParam(value = "orderid") int orderid,
			@RequestParam(value = "amount") float totalAmount,
			@RequestParam(name = "coupon", required = false) String couponApplied) {

		float maxOffer = 0;
		float minOffer = 0;
		float off = 0;
		float discount = 0;

		BillDetails billDetailsObject = billDetailsService.getBillDetailsByorderId(orderid);

		if (billDetailsObject != null)
			throw new BillDetailsAlreadyExistsException("Bill for Order Id : " + orderid);
		if (orderid > 0 && totalAmount > 0) {
			if (couponApplied != null) {
				Coupon couponObject = couponService.getCouponByCode(couponApplied);
				/*
				 * LocalDate date = LocalDate.now();
				 * 
				 * Instant instant = Instant.ofEpochMilli(expiryDate.getTime()); LocalDateTime
				 * localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
				 * LocalDate localDate = localDateTime.toLocalDate();
				 * 
				 * 
				 * System.out.println("Today's date: "+date);
				 * System.out.println("Coupon expiry date: "+expiryDate);
				 * 
				 * int dateDiff=localDate.compareTo(date);
				 * System.out.println("difference of dates: "+dateDiff);
				 */

				if (couponObject != null) {
					
					Date expiryDate = couponObject.getExpiryDate();
					Calendar rightNow = Calendar.getInstance();
					rightNow.clear(Calendar.HOUR);
					rightNow.clear(Calendar.MINUTE);
					rightNow.clear(Calendar.SECOND);
					rightNow.clear(Calendar.MILLISECOND);
					Date today = rightNow.getTime();
					
					if (expiryDate.compareTo(today) >= 0)
					{

						BillDetails billDetails = new BillDetails(orderid, totalAmount, couponApplied);
						int quantity = couponObject.getQuantity();
						quantity -= 1;
						couponObject.setQuantity(quantity);

						minOffer = (billDetails.getTotalAmount()) * (couponObject.getDiscountPercentage() / 100);
						// System.out.println("Discount value: "+minOffer);
						maxOffer = couponObject.getMaximumDiscount();
						// System.out.println("Maximum Offer: "+maxOffer);
						if (minOffer <= maxOffer) {
							off = billDetails.getTotalAmount() - minOffer;
							billDetails.setNetAmount(off);
							// System.out.println("Net amount after discount is: "+off);
						} else {
							off = billDetails.getTotalAmount() - maxOffer;
							billDetails.setNetAmount(off);
							// System.out.println("Net amount after discount is: "+off);
						}
						couponService.updateCoupon(couponObject);
						billDetailsService.addBillDetails(billDetails);
						return new ResponseEntity<BillDetails>(billDetails, HttpStatus.OK);
					}
					
					else
					{
						return new ResponseEntity<String>("The Coupon has expired",
								HttpStatus.BAD_REQUEST); 
					}

				} else {
					throw new CouponNotFoundException("Coupon with code: " + couponApplied);

				}

			} else {
				BillDetails billDetails = new BillDetails(orderid, totalAmount);
				billDetails.setNetAmount(billDetails.getTotalAmount());
				billDetailsService.addBillDetails(billDetails);
				return new ResponseEntity<BillDetails>(billDetails, HttpStatus.OK);

			}
		} else {
			return new ResponseEntity<String>("Please enter positive/greater than zero Order Id and Bill Amount",
					HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping()
	public ResponseEntity<BillDetails> updateBillDetails(@RequestBody BillDetails billDetails) {
		BillDetails billDetailsValidIdObject = billDetailsService.getBillDetailsBybillId(billDetails.getBillId());
		BillDetails billDetailsValidOrderObject = billDetailsService.getBillDetailsByorderId(billDetails.getOrderId());

		if (billDetailsValidIdObject == null)
			throw new BillDetailsNotFoundException("Bill with Id: " + billDetails.getBillId());

		if (billDetailsValidOrderObject == null)
			throw new BillDetailsNotFoundException("Order Id: " + billDetails.getOrderId());

		billDetailsService.updateBillDetails(billDetails);
		return new ResponseEntity<BillDetails>(billDetails, HttpStatus.OK);
	}

	@DeleteMapping("/{billId}")
	public ResponseEntity<?> deleteBillDetails(@PathVariable("billId") int billId) {
		BillDetails billDetailsObject = billDetailsService.getBillDetailsBybillId(billId);

		if (billDetailsObject == null)
			throw new BillDetailsNotFoundException("Bill with Id: " + billId);

		billDetailsService.deleteBillDetails(billId);
		return new ResponseEntity<String>("Bill Details Deleted", HttpStatus.GONE);
	}

}
