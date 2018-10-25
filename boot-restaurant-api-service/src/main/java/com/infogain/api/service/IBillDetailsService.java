package com.infogain.api.service;

import java.util.List;

import com.infogain.api.entity.BillDetails;

public interface IBillDetailsService {
	
	List<BillDetails> getAllBillDetails();
	
    BillDetails getBillDetailsBybillId(int billId);
    BillDetails getBillDetailsByorderId(int orderId);
    BillDetails getBillDetailsBycouponApplied(String couponApplied);
    BillDetails getBillDetailsBytotalAmount(float totalAmount);
    BillDetails addBillDetails(BillDetails billDetails);
    BillDetails updateBillDetails(BillDetails billDetails);
    String deleteBillDetails(int billId);


}
