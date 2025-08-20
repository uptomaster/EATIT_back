package com.bapseguen.app.dto;

public class CartItemDTO {
	
	private int cartItemNumber;
	private int cartNumber;
	private int itemNumber;
	private int cartItemPrice;
	private int cartItemQuantity;
	
	public int getCartItemNumber() {
		return cartItemNumber;
	}
	
	// getter & setter
	public void setCartItemNumber(int cartItemNumber) {
		this.cartItemNumber = cartItemNumber;
	}
	public int getCartNumber() {
		return cartNumber;
	}
	public void setCartNumber(int cartNumber) {
		this.cartNumber = cartNumber;
	}
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	public int getCartItemPrice() {
		return cartItemPrice;
	}
	public void setCartItemPrice(int cartItemPrice) {
		this.cartItemPrice = cartItemPrice;
	}
	public int getCartItemQuantity() {
		return cartItemQuantity;
	}
	public void setCartItemQuantity(int cartItemQuantity) {
		this.cartItemQuantity = cartItemQuantity;
	}
	
	@Override
	public String toString() {
		return "CartItemDTO [cartItemNumber=" + cartItemNumber + ", cartNumber=" + cartNumber + ", itemNumber="
				+ itemNumber + ", cartItemPrice=" + cartItemPrice + ", cartItemQuantity=" + cartItemQuantity + "]";
	}
	
	
	
}
