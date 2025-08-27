package com.bapseguen.app.dto.view;

public class MainStoreListDTO {
	
	private String storeName;
	private String storeOpenTime;
	private String storeCloseTime;
	private String itemName;
	private int itemPrice;
	private int itemImageNumber;
	private String itemImageSystemName;
	private String itemImageOriginalName;
	private int itemNumber;
	
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreOpenTime() {
		return storeOpenTime;
	}
	public void setStoreOpenTime(String storeOpenTime) {
		this.storeOpenTime = storeOpenTime;
	}
	public String getStoreCloseTime() {
		return storeCloseTime;
	}
	public void setStoreCloseTime(String storeCloseTime) {
		this.storeCloseTime = storeCloseTime;
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
	public int getItemImageNumber() {
		return itemImageNumber;
	}
	public void setItemImageNumber(int itemImageNumber) {
		this.itemImageNumber = itemImageNumber;
	}
	public String getItemImageSystemName() {
		return itemImageSystemName;
	}
	public void setItemImageSystemName(String itemImageSystemName) {
		this.itemImageSystemName = itemImageSystemName;
	}
	public String getItemImageOriginalName() {
		return itemImageOriginalName;
	}
	public void setItemImageOriginalName(String itemImageOriginalName) {
		this.itemImageOriginalName = itemImageOriginalName;
	}
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	
	@Override
	public String toString() {
		return "MainStoreListDTO [storeName=" + storeName + ", storeOpenTime=" + storeOpenTime + ", storeCloseTime="
				+ storeCloseTime + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemImageNumber="
				+ itemImageNumber + ", itemImageSystemName=" + itemImageSystemName + ", itemImageOriginalName="
				+ itemImageOriginalName + ", itemNumber=" + itemNumber + "]";
	}

}
