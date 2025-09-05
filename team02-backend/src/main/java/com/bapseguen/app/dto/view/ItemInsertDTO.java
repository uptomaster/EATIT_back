package com.bapseguen.app.dto.view;

public class ItemInsertDTO {
	
	private int itemNumber; //상품 번호
	private String businessNumber; // 가게 의 사업자 번호
	private String itemType; // 음식인지 재료인지
	private String itemName; //상품명
	private int itemPrice; // 상품가격
	private String itemContent; //상품설명
	private int itemQuantity; // 상품 수량
	private String itemExpireDate; // 소비기한
	private String itemCreatedTime; //상품 등록일
	private String itemUpdatedTime; // 상품 수정일
	private String itemSellState; // 판매 여부  "판매중(y)","보관중"(n)
	 
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
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
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
	public String getItemSellState() {
		return itemSellState;
	}
	public void setItemSellState(String itemSellState) {
		this.itemSellState = itemSellState;
	}
	@Override
	public String toString() {
		return "ItemInsertDTO [itemNumber=" + itemNumber + ", businessNumber=" + businessNumber + ", itemType="
				+ itemType + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemContent=" + itemContent
				+ ", itemQuantity=" + itemQuantity +  ", itemExpireDate=" + itemExpireDate
				+ ", itemCreatedTime=" + itemCreatedTime + ", itemUpdatedTime=" + itemUpdatedTime + ", itemSellState="
				+ itemSellState + "]";
	}
	    

	    
}
