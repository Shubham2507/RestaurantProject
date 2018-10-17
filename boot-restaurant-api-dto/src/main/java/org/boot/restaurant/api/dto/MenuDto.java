package org.boot.restaurant.api.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuDto {
	@NotNull(message = "ItemId is required field")
	private Integer itemId;
	private String itemName;
	private Integer rate;
	private String category;
	private Integer quantity;
	private String description;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MenuDto(Integer itemId, String itemName, Integer rate, String category, Integer quantity,
			String description) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.rate = rate;
		this.category = category;
		this.quantity = quantity;
		this.description = description;
	}

	public MenuDto() {
		super();
	}

}
