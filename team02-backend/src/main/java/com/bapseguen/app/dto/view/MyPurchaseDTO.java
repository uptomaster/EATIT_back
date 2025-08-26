package com.bapseguen.app.dto.view;

public class MyPurchaseDTO {
	// 구매 목록에 관한 DTO
	// 재료 구매 목록
	//orders
	private int ordersNumber; // 주문번호
	private String ordersDate; // 구매날짜
	private int ordersTotalAmount;// 금액
	//order_item
	private int orderItemNumber; 
	private int orderItemUnitPrice;
    private int orderItemQuantity;	
	//item
	private String itemType; // 음식인지 재료인지
	private String itemName; //상품명
	private String businessNumber; // order의 사업자 번호
	//store
	private String storeName;// 가게명
	
	public int getOrdersNumber() {
		return ordersNumber;
	}
	public void setOrdersNumber(int ordersNumber) {
		this.ordersNumber = ordersNumber;
	}
	public String getOrdersDate() {
		return ordersDate;
	}
	public void setOrdersDate(String ordersDate) {
		this.ordersDate = ordersDate;
	}
	public String getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getOrdersTotalAmount() {
		return ordersTotalAmount;
	}
	public void setOrdersTotalAmount(int ordersTotalAmount) {
		this.ordersTotalAmount = ordersTotalAmount;
	}
	////
	public int getOrderItemNumber() {
		return orderItemNumber;
	}
	public void setOrderItemNumber(int orderItemNumber) {
		this.orderItemNumber = orderItemNumber;
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
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	
	@Override
	public String toString() {
		return "MyPurchaseDTO [ordersNumber=" + ordersNumber + ", ordersDate=" + ordersDate + ", ordersTotalAmount="
				+ ordersTotalAmount + ", orderItemNumber=" + orderItemNumber + ", orderItemUnitPrice="
				+ orderItemUnitPrice + ", orderItemQuantity=" + orderItemQuantity + ", itemType=" + itemType
				+ ", itemName=" + itemName + ", businessNumber=" + businessNumber + ", storeName=" + storeName + "]";
	}
	
}
