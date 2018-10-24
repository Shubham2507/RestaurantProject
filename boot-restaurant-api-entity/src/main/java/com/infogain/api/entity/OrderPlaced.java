package com.infogain.api.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "OrderPlaced")
public class OrderPlaced {

	@Id
	@Column(name = "Auto_Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    
	
	@Column(name="Manual_OrderId")
	private int orderId; 
	
	@Column(name = "Order_Status")
	private String orderStatus;


	
	@Column(name = "Username")
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

	@Column(name = "Item_Description")
	private String description;
	
	@Column(name = "total_price")
	private int totalPrice;

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

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	
	/*@OneToMany(cascade = CascadeType.ALL)
	private Set<OrderedItem> orderedItem;*/

	
	public String getOrderStatus() {
		return orderStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}



	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public OrderPlaced() {
		super();
	}

}
