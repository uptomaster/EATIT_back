package com.bapseguen.app.dto;

public class ItemDTO {

	private int itemNumber;
	private String businessNumber;
	private String itemType;
	private String itemName;
	private String itemPrice;
	private String itemContent;
	private int itemQuantity;
	private String itemOrigin;
	private String itemExpireDate;
	private String itemCreatedTime;
	private String itemUpdatedTime;
	private boolean itemSellState;
	
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
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
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemContent() {
		return itemContent;
	}
	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}
	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public String getItemOrigin() {
		return itemOrigin;
	}
	public void setItemOrigin(String itemOrigin) {
		this.itemOrigin = itemOrigin;
	}
	public String getItemExpireDate() {
		return itemExpireDate;
	}
	public void setItemExpireDate(String itemExpireDate) {
		this.itemExpireDate = itemExpireDate;
	}
	public String getItemCreatedTime() {
		return itemCreatedTime;
	}
	public void setItemCreatedTime(String itemCreatedTime) {
		this.itemCreatedTime = itemCreatedTime;
	}
	public String getItemUpdatedTime() {
		return itemUpdatedTime;
	}
	public void setItemUpdatedTime(String itemUpdatedTime) {
		this.itemUpdatedTime = itemUpdatedTime;
	}
	public boolean isItemSellState() {
		return itemSellState;
	}
	public void setItemSellState(boolean itemSellState) {
		this.itemSellState = itemSellState;
	}
	
	@Override
	public String toString() {
		return "ItemDTO [itemNumber=" + itemNumber + ", businessNumber=" + businessNumber + ", itemType=" + itemType
				+ ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemContent=" + itemContent
				+ ", itemQuantity=" + itemQuantity + ", itemOrigin=" + itemOrigin + ", itemExpireDate=" + itemExpireDate
				+ ", itemCreatedTime=" + itemCreatedTime + ", itemUpdatedTime=" + itemUpdatedTime + ", itemSellState="
				+ itemSellState + "]";
	}
}
