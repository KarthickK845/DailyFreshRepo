package com.item.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="items")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_sequence")
	@SequenceGenerator(name="custom_sequence",sequenceName = "custom_sequence_name",initialValue = 100,allocationSize = 1)
	@Column
	private int itemId;
	
	@Column
	private String itemName;
	
	@Column
	private String category;
	
	@Column
	private double purchasePrice;
	
	@Column
	private double sellingPrice;
	
	public Item(int itemId, String itemName, String category, double purchasePrice, double sellingPrice) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.category = category;
		this.purchasePrice = purchasePrice;
		this.sellingPrice = sellingPrice;
	}
	public Item() {
		super();
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", category=" + category + ", purchasePrice="
				+ purchasePrice + ", sellingPrice=" + sellingPrice + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + itemId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Item))
			return false;
		Item other = (Item) obj;
		if (itemId != other.itemId)
			return false;
		return true;
	}
	
	
}
