package com.bapseguen.app.dto;

/** TBL_CART_ITEM DTO */
public class CartItemDTO {
	private int cartItemNumber; // 장바구니 항목번호(PK)
	private int cartNumber; // FK -> TBL_CART(CART_NUMBER)
	private int itemNumber; // FK -> TBL_ITEM(ITEM_NUMBER)
	private int cartItemPrice; // 담을 당시 가격
	private int cartItemQuantity; // 수량

	// 이미지 파일명 (조인해서 가져올 값)
	private String imagePath;
	
	// 상품명 필드 추가
    private String itemName;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	// getter & setter
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

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
				+ itemNumber + ", cartItemPrice=" + cartItemPrice + ", cartItemQuantity=" + cartItemQuantity
				+ ", imagePath=" + imagePath + ", itemName=" + itemName + "]";
	}

	

	
}
