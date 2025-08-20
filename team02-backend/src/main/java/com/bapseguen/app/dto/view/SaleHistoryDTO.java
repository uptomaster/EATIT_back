package com.bapseguen.app.dto.view;

public class SaleHistoryDTO {
	// 주문내역 정보 - 판매금액
	private int ordersNumber;
	private int ordersMemberNumber;
	private String ordersDate;
	private int ordersTotalAmount;
	private String ordersPaymentStatus;
	// 상품에 관한 정보
	private int orderItemNumber;
	// private int orderNumber;
	private int itemNumber;
	private int orderItemUnitPrice;
	private int orderItemQuantity;

	private String businessNumber;
	private String itemType;
	private String itemName;
	public int getOrdersNumber() {
		return ordersNumber;
	}
	public void setOrdersNumber(int ordersNumber) {
		this.ordersNumber = ordersNumber;
	}
	public int getOrdersMemberNumber() {
		return ordersMemberNumber;
	}
	public void setOrdersMemberNumber(int ordersMemberNumber) {
		this.ordersMemberNumber = ordersMemberNumber;
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
	public String getOrdersPaymentStatus() {
		return ordersPaymentStatus;
	}
	public void setOrdersPaymentStatus(String ordersPaymentStatus) {
		this.ordersPaymentStatus = ordersPaymentStatus;
	}
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
	public String getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
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
	public String getItemExpireDate() {
		return itemExpireDate;
	}
	public void setItemExpireDate(String itemExpireDate) {
		this.itemExpireDate = itemExpireDate;
	}
	public String getMemberPhoneNumber() {
		return memberPhoneNumber;
	}
	public void setMemberPhoneNumber(String memberPhoneNumber) {
		this.memberPhoneNumber = memberPhoneNumber;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	private String itemExpireDate;

	// 회원에 관련된 정보
	private String memberPhoneNumber;
	private String memberId;
	@Override
	public String toString() {
		return "SaleHistoryDTO [ordersNumber=" + ordersNumber + ", ordersMemberNumber=" + ordersMemberNumber
				+ ", ordersDate=" + ordersDate + ", ordersTotalAmount=" + ordersTotalAmount + ", ordersPaymentStatus="
				+ ordersPaymentStatus + ", orderItemNumber=" + orderItemNumber + ", itemNumber=" + itemNumber
				+ ", orderItemUnitPrice=" + orderItemUnitPrice + ", orderItemQuantity=" + orderItemQuantity
				+ ", businessNumber=" + businessNumber + ", itemType=" + itemType + ", itemName=" + itemName
				+ ", itemExpireDate=" + itemExpireDate + ", memberPhoneNumber=" + memberPhoneNumber + ", memberId="
				+ memberId + "]";
	}
}
