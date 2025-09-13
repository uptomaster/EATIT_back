package com.bapseguen.app.dto.view;

public class ReviewWithUserDTO {
	private int reviewNumber;
	private int ordersNumber;
	private String businessNumber;
	private int memberNumber;
	private String memberId;
	private int reviewRating;
	private String reviewContent;
	private String reviewCreateDate;
	private String itemName;
	private String storeName;
	private String storeTel;
	private String storeAddress;
	private String storeImageSystemName;
	private String storeImageOriginalName; 

	// ===== Getter / Setter =====
	public int getReviewNumber() {
		return reviewNumber;
	}

	public void setReviewNumber(int reviewNumber) {
		this.reviewNumber = reviewNumber;
	}

	public int getOrdersNumber() {
		return ordersNumber;
	}

	public void setOrdersNumber(int ordersNumber) {
		this.ordersNumber = ordersNumber;
	}

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	public int getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getReviewCreateDate() {
		return reviewCreateDate;
	}

	public void setReviewCreateDate(String reviewCreateDate) {
		this.reviewCreateDate = reviewCreateDate;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreTel() {
		return storeTel;
	}

	public void setStoreTel(String storeTel) {
		this.storeTel = storeTel;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
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
}
