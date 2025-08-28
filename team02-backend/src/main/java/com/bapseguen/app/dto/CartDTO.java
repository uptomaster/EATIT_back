package com.bapseguen.app.dto;

import java.util.List;

public class CartDTO {

	private int cartNumber; // 장바구니번호(PK)
	private int memberNumber; // 회원번호(FK)
	private String businessNumber; // 사업자번호(FK)
	private String cartOrderStatus;
	private Integer ordersNumber; // 주문번호(FK) => 삭제되면 Null값

	
	// 장바구니 한개에 담긴 아이템 리스트
	private List<CartItemDTO> cartItems;


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


	public String getCartOrderStatus() {
		return cartOrderStatus;
	}


	public void setCartOrderStatus(String cartOrderStatus) {
		this.cartOrderStatus = cartOrderStatus;
	}


	public Integer getOrdersNumber() {
		return ordersNumber;
	}


	public void setOrdersNumber(Integer ordersNumber) {
		this.ordersNumber = ordersNumber;
	}


	public List<CartItemDTO> getCartItems() {
		return cartItems;
	}


	public void setCartItems(List<CartItemDTO> cartItems) {
		this.cartItems = cartItems;
	}


	@Override
	public String toString() {
		return "CartDTO [cartNumber=" + cartNumber + ", memberNumber=" + memberNumber + ", businessNumber="
				+ businessNumber + ", cartOrderStatus=" + cartOrderStatus + ", ordersNumber=" + ordersNumber
				+ ", cartItems=" + cartItems + "]";
	}


	public Object getItemPrice() {
		// TODO Auto-generated method stub
		return null;
	}


	public int getItemQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getItemNumber() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	

}
