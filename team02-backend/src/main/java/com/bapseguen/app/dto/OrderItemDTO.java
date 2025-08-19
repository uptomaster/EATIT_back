package com.bapseguen.app.dto;

public class OrderItemDTO {

	private int orderItemNumber; // ORDER_ITEM_NUMBER
	private int itemNumber; // ITEM_NUMBER
	private int unitPrice; // UNIT_PRICE
	private int quantity; // QUANTITY

	// Getter & Setter
	public int getOrderItemNumber() {
		return orderItemNumber;
	}

	public void setOrderItemNumber(int orderItemNumber) {
		this.orderItemNumber = orderItemNumber;
	}

	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
