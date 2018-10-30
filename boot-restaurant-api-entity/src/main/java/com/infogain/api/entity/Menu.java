package com.infogain.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Menu")
public class Menu {
	@Id
	@Column(name = "Item_Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
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

}
