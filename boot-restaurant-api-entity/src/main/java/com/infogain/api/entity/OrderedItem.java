/*package com.infogain.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OrderedItem")
public class OrderedItem {
	@Id
	@Column(name = "OrderedItem_Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int OrderedItemId;
	
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
	
	@Column(name = "username")
	private String username;
	
	

	public int getOrderedItemId() {
		return OrderedItemId;
	}

	public void setOrderedItemId(int orderedItemId) {
		OrderedItemId = orderedItemId;
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

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public OrderedItem(int orderedItemId, int itemId, String itemName, int rate, String category, int quantity,
			String description, int totalPrice, String username) {
		super();
		OrderedItemId = orderedItemId;
		this.itemId = itemId;
		this.itemName = itemName;
		this.rate = rate;
		this.category = category;
		this.quantity = quantity;
		this.description = description;
		this.totalPrice = totalPrice;
		this.username = username;
	}

	public OrderedItem() {
		super();
	}

	

	
	

}
*/