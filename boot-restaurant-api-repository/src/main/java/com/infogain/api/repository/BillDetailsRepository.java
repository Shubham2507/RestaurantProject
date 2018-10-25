package com.infogain.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.infogain.api.entity.BillDetails;

public interface BillDetailsRepository extends CrudRepository<BillDetails, Integer>{

	BillDetails findBybillId(int billId);
	BillDetails findByorderId(int orderId);
	BillDetails findBycouponApplied(String couponApplied);
	BillDetails findBytotalAmount(float totalAmount);
		
}
