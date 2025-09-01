package com.bapseguen.app.dto.view;

/**
 * 
 */
public class ItemWithImgDTO {
	// 상품 정보 itemDTO
	private int itemNumber; // 상품 번호
	private String businessNumber; // 가게 의 사업자 번호
	private String itemType; // 음식인지 재료인지
	private String itemName; // 상품명
	private String itemPrice; // 상품가격
	private String itemContent; // 상품설명
	private int itemQuantity; // 상품 수량
	private String itemOrigin; //상품 원산지
	private String itemExpireDate; // 소비기한
	private String itemCreatedTime; // 상품 등록일
	private String itemUpdatedTime; // 상품 수정일
	private boolean itemSellState; // 판매 여부 "판매중(y)","보관중"(n),

    // 가게 정보
    private String storeName;
    private String storeAddress;
    private String storeAddressDetail;
    private String storeZipCode;
    private String storeTel;

	
	
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

	public String getItemOrigin() {
		return itemOrigin;
	}

	public void setItemOrigin(String itemOrigin) {
		this.itemOrigin = itemOrigin;
	}

	private String businessName; // 상호명 -> tbl_store랑 조인할거임

	// 이미지 정보 itemImageDTO
	private int itemImageNumber; // 상품 이미지 번호
	private String itemImageSystemName; // 상품 이미지 서버의 파이명
	private String itemImageOriginalName; // 상품 이미지 원본파일명

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

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	@Override
	public String toString() {
		return "ItemWithImgDTO [itemNumber=" + itemNumber + ", businessNumber=" + businessNumber + ", itemType="
				+ itemType + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemContent=" + itemContent
				+ ", itemQuantity=" + itemQuantity + ", itemOrigin=" + itemOrigin + ", itemExpireDate=" + itemExpireDate
				+ ", itemCreatedTime=" + itemCreatedTime + ", itemUpdatedTime=" + itemUpdatedTime + ", itemSellState="
				+ itemSellState + ", storeName=" + storeName + ", storeAddress=" + storeAddress
				+ ", storeAddressDetail=" + storeAddressDetail + ", storeZipCode=" + storeZipCode + ", storeTel="
				+ storeTel + ", businessName=" + businessName + ", itemImageNumber=" + itemImageNumber
				+ ", itemImageSystemName=" + itemImageSystemName + ", itemImageOriginalName=" + itemImageOriginalName
				+ "]";
	}




	


}
