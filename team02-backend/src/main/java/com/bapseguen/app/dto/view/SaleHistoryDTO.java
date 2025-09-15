package com.bapseguen.app.dto.view;

public class SaleHistoryDTO {
	// 주문
    private int ordersNumber;          // 주문번호
    private String ordersDate;         // 주문일자
    private int ordersTotalAmount;     // 총액

    // 주문 상세
    private int orderItemQuantity;     // 수량
    private int orderItemUnitPrice;    // 단가
    private int quantity;     // 수량
    private int unitPrice;    // 단가

    // 상품
    private String itemName;           // 상품명
    private String itemType;           // 상품종류

    // 구매자
    private String memberId;           // 구매자 ID
    private String memberPhoneNumber;  // 구매자 전화번호

    // 리뷰
    private Integer reviewRating;      // 평점 (없을 수 있으므로 Integer)

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

	public int getOrdersTotalAmount() {
		return ordersTotalAmount;
	}

	public void setOrdersTotalAmount(int ordersTotalAmount) {
		this.ordersTotalAmount = ordersTotalAmount;
	}

	public int getOrderItemQuantity() {
		return orderItemQuantity;
	}

	public void setOrderItemQuantity(int orderItemQuantity) {
		this.orderItemQuantity = orderItemQuantity;
	}

	public int getOrderItemUnitPrice() {
		return orderItemUnitPrice;
	}

	public void setOrderItemUnitPrice(int orderItemUnitPrice) {
		this.orderItemUnitPrice = orderItemUnitPrice;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPhoneNumber() {
		return memberPhoneNumber;
	}

	public void setMemberPhoneNumber(String memberPhoneNumber) {
		this.memberPhoneNumber = memberPhoneNumber;
	}

	public Integer getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(Integer reviewRating) {
		this.reviewRating = reviewRating;
	}

	@Override
	public String toString() {
		return "SaleHistoryDTO [ordersNumber=" + ordersNumber + ", ordersDate=" + ordersDate + ", ordersTotalAmount="
				+ ordersTotalAmount + ", orderItemQuantity=" + orderItemQuantity + ", orderItemUnitPrice="
				+ orderItemUnitPrice + ", itemName=" + itemName + ", itemType=" + itemType + ", memberId=" + memberId
				+ ", memberPhoneNumber=" + memberPhoneNumber + ", reviewRating=" + reviewRating + "]";
	}
 
}
