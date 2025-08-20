package com.bapseguen.app.dto;

import java.util.List;

public class CartDTO {

	private int cartNumber;
	private int memberNumber;
	private String businessNumber;
	private String cartOrderStatus;
	private int ordersNumber;
	
	public int getCartNumber() {
		return cartNumber;
	}
	
	// getter & setter
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
	
	public String getCartOrderStatus() {
		return cartOrderStatus;
	}
	public void setCartOrderStatus(String cartOrderStatus) {
		this.cartOrderStatus = cartOrderStatus;
	}
	public int getOrdersNumber() {
		return ordersNumber;
	}
	public void setOrdersNumber(int ordersNumber) {
		this.ordersNumber = ordersNumber;
	}
	
	@Override
	public String toString() {
		return "CartDTO [cartNumber=" + cartNumber + ", memberNumber=" + memberNumber + ", businessNumber="
				+ businessNumber + ", cartOrderStatus=" + cartOrderStatus + ", ordersNumber=" + ordersNumber + "]";
	}
	
	
	
}
