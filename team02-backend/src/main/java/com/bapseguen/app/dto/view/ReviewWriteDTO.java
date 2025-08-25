package com.bapseguen.app.dto.view;

public class ReviewWriteDTO {
	//리뷰내용
	   private int reviewRating;
	   private String reviewContent;
	   private String reviewCreateDate;
	//구매내역
	   private String ordersDate;
	   private int ordersTotalAmount;
	// 구매품목
	   private int orderItemNumber;
	   private int orderItemUnitPrice;
	   private int orderItemQuantity;
	// 메뉴 정보 
	   private String itemType;
	   private String itemName;
	   // 가게 정보
	   private String storeName;
	   private String businessNumber;
	   	   
	public int getReviewRating() {
		return reviewRating;
	}
	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public String getReviewCreateDate() {
		return reviewCreateDate;
	}
	public void setReviewCreateDate(String reviewCreateDate) {
		this.reviewCreateDate = reviewCreateDate;
	}
	public String getOrdersDate() {
		return ordersDate;
	}
	public void setOrdersDate(String ordersDate) {
		this.ordersDate = ordersDate;
	}
	public int getOrdersTotalAmount() {
		return ordersTotalAmount;
	}
	public void setOrdersTotalAmount(int ordersTotalAmount) {
		this.ordersTotalAmount = ordersTotalAmount;
	}
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
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}
	@Override
	public String toString() {
		return "ReviewWriteDTO [reviewRating=" + reviewRating + ", reviewContent=" + reviewContent
				+ ", reviewCreateDate=" + reviewCreateDate + ", ordersDate=" + ordersDate + ", ordersTotalAmount="
				+ ordersTotalAmount + ", orderItemNumber=" + orderItemNumber + ", orderItemUnitPrice="
				+ orderItemUnitPrice + ", orderItemQuantity=" + orderItemQuantity + ", itemType=" + itemType
				+ ", itemName=" + itemName + ", storeName=" + storeName + ", businessNumber=" + businessNumber + "]";
	}
	   
}
