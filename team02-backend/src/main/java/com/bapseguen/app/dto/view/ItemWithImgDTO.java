package com.bapseguen.app.dto.view;

/**
 * 상품 + 가게 + 이미지 DTO
 */
public class ItemWithImgDTO {
	// 상품 정보
	private int itemNumber; // 상품 번호
	private String businessNumber; // 가게 사업자 번호
	private String itemType; // 음식/재료 구분
	private String itemName; // 상품명
	private String itemPrice; // 상품가격
	private String itemContent; // 상품설명
	private int itemQuantity; // 상품 수량
	private String itemExpireDate; // 소비기한
	private String itemCreatedTime; // 등록일
	private String itemUpdatedTime; // 수정일
	private boolean itemSellState; // 판매 여부

	// ✅ 원산지 정보 (추가)
	private String itemOrigin;

	// 가게 정보
	private String storeName;
	private String storeAddress;
	private String storeAddressDetail;
	private String storeZipCode;
	private String storeTel;

	private String businessName; // 상호명 (조인)

	// 이미지 정보
	private int itemImageNumber; // 상품 이미지 번호
	private String itemImageSystemName; // 서버 저장 파일명
	private String itemImageOriginalName; // 원본 파일명

	// ===== Getter / Setter =====
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
				+ "]";
	}
}
