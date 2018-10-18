package com.infogain.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Coupon")
public class Coupon {
	
	@Id
	@Column(name="Coupon_Code")
	private String code;
	
	@Column(name="Coupon_Value")
	private int value;
	
	@Column(name="Release_Date")
//	@DateTimeFormat(pattern = "dd-MM-yy")
	@Temporal(TemporalType.DATE)
	private Date releaseDate;
	
	@Column(name="Expiry_Date")
	//@DateTimeFormat(pattern = "dd-MM-yy")
	@Temporal(TemporalType.DATE)
	private Date expiryDate;

	@Column(name="Number_Of_Coupons")
	private int quantity;
	

	public Coupon() {
		super();
	}


	public Coupon(String code, int value, Date releaseDate, Date expiryDate, int quantity) {
		super();
		this.code = code;
		this.value = value;
		this.releaseDate = releaseDate;
		this.expiryDate = expiryDate;
		this.quantity = quantity;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}



//	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}


	//@JsonSerialize(using=JsonDateSerializer.class)
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


	@Override
	public String toString() {
		return "Coupon [code=" + code + ", value=" + value + ", releaseDate=" + releaseDate + ", expiryDate="
				+ expiryDate + ", quantity=" + quantity + "]";
	}
	
	
	

}
