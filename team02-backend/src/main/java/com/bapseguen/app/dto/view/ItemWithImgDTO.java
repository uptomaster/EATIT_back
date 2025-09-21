package com.bapseguen.app.dto.view;

/**
 * 상품 + 가게 + 이미지 DTO
 */
public class ItemWithImgDTO {
	// 상품 정보
	private int itemNumber;
	private String businessNumber;
	private String itemType;
	private String itemName;
	private String itemPrice;
	private String itemContent;
	private int itemQuantity;
	private String itemExpireDate;
	private String itemCreatedTime;
	private String itemUpdatedTime;
	private boolean itemSellState;

	// 원산지
	private String itemOrigin;

	// 가게 정보
	private String storeName;
	private String storeAddress;
	private String storeAddressDetail;
	private String storeZipCode;
	private String storeTel;
	private String businessName;

	// 상품 이미지
	private int itemImageNumber;
	private String itemImageSystemName;
	private String itemImageOriginalName;

	// 가게 이미지
	private String storeImageSystemName;
	private String storeImageOriginalName;
	
	private double latitude;   // 위도
	private double longitude;  // 경도
	private double distance;
	

	// ===== Getter / Setter =====
	// 상품
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
	public String getItemOrigin() {
		return itemOrigin;
	}
	public void setItemOrigin(String itemOrigin) {
		this.itemOrigin = itemOrigin;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	public String getStoreAddressDetail() {
		return storeAddressDetail;
	}
	public void setStoreAddressDetail(String storeAddressDetail) {
		this.storeAddressDetail = storeAddressDetail;
	}
	public String getStoreZipCode() {
		return storeZipCode;
	}
	public void setStoreZipCode(String storeZipCode) {
		this.storeZipCode = storeZipCode;
	}
	public String getStoreTel() {
		return storeTel;
	}
	public void setStoreTel(String storeTel) {
		this.storeTel = storeTel;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
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
	public String getStoreImageSystemName() {
		return storeImageSystemName;
	}
	public void setStoreImageSystemName(String storeImageSystemName) {
		this.storeImageSystemName = storeImageSystemName;
	}
	public String getStoreImageOriginalName() {
		return storeImageOriginalName;
	}
	public void setStoreImageOriginalName(String storeImageOriginalName) {
		this.storeImageOriginalName = storeImageOriginalName;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	@Override
	public String toString() {
		return "ItemWithImgDTO [itemNumber=" + itemNumber + ", businessNumber=" + businessNumber + ", itemType="
				+ itemType + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemContent=" + itemContent
				+ ", itemQuantity=" + itemQuantity + ", itemExpireDate=" + itemExpireDate + ", itemCreatedTime="
				+ itemCreatedTime + ", itemUpdatedTime=" + itemUpdatedTime + ", itemSellState=" + itemSellState
				+ ", itemOrigin=" + itemOrigin + ", storeName=" + storeName + ", storeAddress=" + storeAddress
				+ ", storeAddressDetail=" + storeAddressDetail + ", storeZipCode=" + storeZipCode + ", storeTel="
				+ storeTel + ", businessName=" + businessName + ", itemImageNumber=" + itemImageNumber
				+ ", itemImageSystemName=" + itemImageSystemName + ", itemImageOriginalName=" + itemImageOriginalName
				+ ", storeImageSystemName=" + storeImageSystemName + ", storeImageOriginalName="
				+ storeImageOriginalName + ", latitude=" + latitude + ", longitude=" + longitude + ", distance="
				+ distance + "]";
	}

}