package com.bapseguen.app.dto;

public class ReviewDTO {

	private int reviewNumber;
	private int ordersNumber;
	private int businessNumber;
	private int MemberNumber;
	private int reviewRating;
	private String reviewContent;
	private String reviewCreateDate;
	
	//+)추가 리뷰조회
	private int itemPrice; 
	private int itemQuantity;
	
	protected int getReviewNumber() {
		return reviewNumber;
	}
	protected void setReviewNumber(int reviewNumber) {
		this.reviewNumber = reviewNumber;
	}
	protected int getOrdersNumber() {
		return ordersNumber;
	}
	protected void setOrdersNumber(int ordersNumber) {
		this.ordersNumber = ordersNumber;
	}
	protected int getBusinessNumber() {
		return businessNumber;
	}
	protected void setBusinessNumber(int businessNumber) {
		this.businessNumber = businessNumber;
	}
	protected int getMemberNumber() {
		return MemberNumber;
	}
	protected void setMemberNumber(int memberNumber) {
		MemberNumber = memberNumber;
	}
	protected int getReviewRating() {
		return reviewRating;
	}
	protected void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}
	protected String getReviewContent() {
		return reviewContent;
	}
	protected void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	protected String getReviewCreateDate() {
		return reviewCreateDate;
	}
	protected void setReviewCreateDate(String reviewCreateDate) {
		this.reviewCreateDate = reviewCreateDate;
	}
	protected List<itemImage> getFiles() {
		return files;
	}
	protected void setFiles(List<itemImage> files) {
		this.files = files;
	}
	protected int getItemPrice() {
		return itemPrice;
	}
	protected void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	protected int getItemQuantity() {
		return itemQuantity;
	}
	protected void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	
	@Override
	public String toString() {
		return "ReviewDTO [reviewNumber=" + reviewNumber + ", ordersNumber=" + ordersNumber + ", businessNumber="
				+ businessNumber + ", MemberNumber=" + MemberNumber + ", reviewRating=" + reviewRating
				+ ", reviewContent=" + reviewContent + ", reviewCreateDate=" + reviewCreateDate + ", itemPrice="
				+ itemPrice + ", itemQuantity=" + itemQuantity + "]";
	}
	
}
