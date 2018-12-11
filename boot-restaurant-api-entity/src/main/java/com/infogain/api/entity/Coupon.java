package com.infogain.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Coupon")
public class Coupon {
	
	@Id
	@Column(name="Coupon_Code")
	private String code;
	
	@NotNull
	@Column(nullable=false,name="Discount_Percentage")
	private float discountPercentage;
	
	@NotNull
	@Column(nullable=false,name="Maximum_Discount")
	private float maximumDiscount;
	
	@NotNull
	@Column(nullable=false,name="BillAmount")
	public int billAmount;
	
	
	@NotNull
	@Column(nullable=false,name="Release_Date") 
	@Temporal(TemporalType.DATE)
	//@DateTimeFormat(pattern = "yyy-MM-dd")
	private Date releaseDate;
	
	@NotNull
	@Column(nullable=false,name="Expiry_Date")
	@Temporal(TemporalType.DATE)
	//@DateTimeFormat(pattern = "yyy-MM-dd")	
	private Date expiryDate;

	@NotNull
	@Column(nullable=false,name="Number_Of_Coupons")
	private int quantity;

	@NotNull
	@Column(nullable=false,name="Terms_and_Conditions")
	private String termsAndConditions;

	
	public Coupon() { 
	}

	
	public Coupon(String code, @NotNull float discountPercentage, @NotNull float maximumDiscount,
			@NotNull int billAmount, @NotNull Date releaseDate, @NotNull Date expiryDate, @NotNull int quantity,
			@NotNull String termsAndConditions) {
		super();
		this.code = code;
		this.discountPercentage = discountPercentage;
		this.maximumDiscount = maximumDiscount;
		this.billAmount = billAmount;
		this.releaseDate = releaseDate;
		this.expiryDate = expiryDate;
		this.quantity = quantity;
		this.termsAndConditions = termsAndConditions;
	}





	public int getBillAmount() {
		return billAmount;
	}





	public void setBillAmount(int billAmount) {
		this.billAmount = billAmount;
	}





	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public float getDiscountPercentage() {
		return discountPercentage;
	}



	public void setDiscountPercentage(float discountPercentage) {
		this.discountPercentage = discountPercentage;
	}



	public float getMaximumDiscount() {
		return maximumDiscount;
	}



	public void setMaximumDiscount(float maximumDiscount) {
		this.maximumDiscount = maximumDiscount;
	}



	public Date getReleaseDate() {
		return releaseDate;
	}



	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}



	public Date getExpiryDate() {
		return expiryDate;
	}



	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public String getTermsAndConditions() {
		return termsAndConditions;
	}



	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}


	@Override
	public String toString() {
		return "Coupon [code=" + code + ", discountPercentage=" + discountPercentage + ", maximumDiscount="
				+ maximumDiscount + ", billAmount=" + billAmount + ", releaseDate=" + releaseDate + ", expiryDate="
				+ expiryDate + ", quantity=" + quantity + ", termsAndConditions=" + termsAndConditions + "]";
	}



	

}