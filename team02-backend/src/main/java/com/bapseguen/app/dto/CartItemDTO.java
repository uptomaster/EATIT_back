package com.bapseguen.app.dto;

public class CartItemDTO {

    private int cartItemNumber;  // 장바구니 아이템 번호
    private int cartNumber;      // 장바구니 번호
    private int itemNumber;      // 상품 번호
    private int unitPrice;       // 단가
    private int quantity;        // 수량
    
	public int getCartItemNumber() {
		return cartItemNumber;
	}
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
    
    
	
}
