package com.infogain.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infogain.api.entity.BillDetails;
import com.infogain.api.repository.BillDetailsRepository;


@Service
public class BillDetailsService implements IBillDetailsService{

	@Autowired BillDetailsRepository billDetailsRepository;
	
	@Override
	public List<BillDetails> getAllBillDetails() {
		return (List<BillDetails>) billDetailsRepository.findAll();
	}

	@Override
	public BillDetails getBillDetailsBybillId(int billId) {
		return billDetailsRepository.findBybillId(billId);
	}

	@Override
	public BillDetails getBillDetailsByorderId(int orderId) {
		return billDetailsRepository.findByorderId(orderId);
	}

	@Override
	public BillDetails getBillDetailsBycouponApplied(String couponApplied) {
		return billDetailsRepository.findBycouponApplied(couponApplied);
	}

	@Override
	public BillDetails getBillDetailsBytotalAmount(float totalAmount) {
		return billDetailsRepository.findBytotalAmount(totalAmount);
	}

	@Override
	public BillDetails addBillDetails(BillDetails billDetails) {
		return billDetailsRepository.save(billDetails);
	}

	@Override
	public BillDetails updateBillDetails(BillDetails billDetails) {
		return billDetailsRepository.save(billDetails);
	}

	@Override
	public String deleteBillDetails(int billId) {
		billDetailsRepository.delete(billDetailsRepository.findBybillId(billId));
		return "Bill Details deleted";
	}

}
