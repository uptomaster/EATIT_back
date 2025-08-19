package com.bapseguen.app.dto;

import java.util.List;

public class CartDTO {
	private int cartNumber; // CART_NUMBER
	private int memberNumber; // MEMBER_NUMBER
	private String businessNumber; // BUSINESS_NUMBER
	private String orderStatus; // ORDER_STATUS (OPEN, ORDERED, CANCELLED)
	private Integer orderNumber; // 연결된 주문번호 (결제 완료시)
	private List<CartItemDTO> items; // 장바구니 아이템 리스트
	
	public int getCartNumber() {
		return cartNumber;
	}
	
	public void setCartNumber(int cartNumber) {
		this.cartNumber = cartNumber;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	
	public String getBusinessNumber() {
		return businessNumber;
	}
	
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getOrderNumber() {
		return orderNumber;
	}
	
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public List<CartItemDTO> getItems() {
		return items;
	}
	
	public void setItems(List<CartItemDTO> items) {
		this.items = items;
	}
	
	
	
}
