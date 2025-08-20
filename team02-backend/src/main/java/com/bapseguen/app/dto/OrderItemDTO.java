package com.bapseguen.app.dto;

public class OrderItemDTO {

	private int orderItemNumber;
	private int orderNumber;
	private int itemNumber;
	private int orderItemUnitPrice;
	private int orderItemQuantity;
	
	public int getOrderItemNumber() {
		return orderItemNumber;
	}
	
	// getter & setter
	public void setOrderItemNumber(int orderItemNumber) {
		this.orderItemNumber = orderItemNumber;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	public int getOrderItemUnitPrice() {
		return orderItemUnitPrice;
	}
	public void setOrderItemUnitPrice(int orderItemUnitPrice) {
		this.orderItemUnitPrice = orderItemUnitPrice;
	}
	public int getOrderItemQuantity() {
		return orderItemQuantity;
	}
	public void setOrderItemQuantity(int orderItemQuantity) {
		this.orderItemQuantity = orderItemQuantity;
	}
	
	@Override
	public String toString() {
		return "OrderItemDTO [orderItemNumber=" + orderItemNumber + ", orderNumber=" + orderNumber + ", itemNumber="
				+ itemNumber + ", orderItemUnitPrice=" + orderItemUnitPrice + ", orderItemQuantity=" + orderItemQuantity
				+ "]";
	}
	
	
	
}
