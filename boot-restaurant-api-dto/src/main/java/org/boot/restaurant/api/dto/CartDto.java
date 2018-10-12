package org.boot.restaurant.api.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartDto {
	@NotNull(message = "CartId is required field")
	private Integer cartId;
	private String totalPrice;
	private String username;
	private int itemId;
	private String itemName;
	private int rate;
	private String category;
	private int quantity;
	private String description;

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

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

	public CartDto(@NotNull(message = "CartId is required field") Integer cartId, String totalPrice, String username,
			int itemId, String itemName, int rate, String category, int quantity, String description) {
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

	public CartDto() {
		super();
	}

}
