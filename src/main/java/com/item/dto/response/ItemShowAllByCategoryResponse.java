package com.item.dto.response;

import java.util.List;

import com.item.model.Item;

public class ItemShowAllByCategoryResponse {
	int statusCode;
	String description;
	List<Item> items;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "ItemShowAllByCategoryResponse [statusCode=" + statusCode + ", description=" + description + ", items="
				+ items + "]";
	}
	
	
}
