package com.bapseguen.app.dto.view;

public class ItemSnapshotDTO {
	private Integer itemNumber;
	private String businessNumber;
	private String itemName; // 메뉴명
	private Integer itemPrice; 
	private Integer itemQuantity; // 재고 (null도 가능)
	private String itemSellState; // 'Y' / 'N'

	
	
	// getter & setter
	public Integer getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Integer getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getItemSellState() {
		return itemSellState;
	}

	public void setItemSellState(String itemSellState) {
		this.itemSellState = itemSellState;
	}
}
