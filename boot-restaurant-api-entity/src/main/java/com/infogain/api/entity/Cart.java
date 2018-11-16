package com.infogain.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "Cart")
public class Cart  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cart_id")
	private int cartId;

	
	@Column(name = "username")
	private String username;

	@Column(name = "Item_Id")
	private int itemId;

	@Column(name = "Item_Name")
	private String itemName;

	@Column(name = "Rate")
	private int rate;

	@Column(name = "Category")
	private String category;

	@Column(name = "quantity")
	private int quantity;
	@Column(name = "total_price")
	private int totalPrice;

	@Column(name = "Item_Description")
	private String description;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Cart(Integer cartId, int totalPrice, String username, int itemId, String itemName, int rate,
			String category, int quantity, String description) {
		super();
		this.cartId = cartId;
		this.totalPrice = totalPrice;
		this.username = username;
		this.itemId = itemId;
		this.itemName = itemName;
		this.rate = rate;
		this.category = category;
		this.quantity = quantity;
		this.description = description;
	}

	public Cart() {
		super();
	}

}
