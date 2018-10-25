package com.infogain.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Bill_Details")
public class BillDetails 
{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Bill_Id")
	private int billId;
	
	@NotNull
	@Column(nullable=false, name="Order_Number")
	private int orderId;
	
	@NotNull
	@Column(nullable=false,name="Total_Amount")
	private float totalAmount;
	
	@Column(name="Coupon_Applied")
	private String couponApplied;
	
	@Column(name="Net_Amount")
	private float netAmount;

	public BillDetails() {
		super();
	}

	public BillDetails(int orderId, float totalAmount) {  
		this.orderId = orderId;
		this.totalAmount = totalAmount;
	}

	public BillDetails(int orderId, float totalAmount, String couponApplied) { 
		this.orderId = orderId;
		this.totalAmount = totalAmount;
		this.couponApplied = couponApplied;
	}



	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCouponApplied() {
		return couponApplied;
	}

	public void setCouponApplied(String couponApplied) {
		this.couponApplied = couponApplied;
	}

	public float getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(float netAmount) {
		this.netAmount = netAmount;
	}

	@Override
	public String toString() {
		return "BillDetails [billId=" + billId + ", orderId=" + orderId + ", totalAmount=" + totalAmount
				+ ", couponApplied=" + couponApplied + ", netAmount=" + netAmount + "]";
	}
	
	
}
