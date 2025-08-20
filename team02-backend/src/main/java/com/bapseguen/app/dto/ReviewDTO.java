package com.bapseguen.app.dto;

public class ReviewDTO {

	private int reviewNumber;
	private int ordersNumber;
	private String businessNumber;
	private int MemberNumber;
	private int reviewRating;
	private String reviewContent;
	private String reviewCreateDate;
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
		return MemberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		MemberNumber = memberNumber;
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
	@Override
	public String toString() {
		return "ReviewDTO [reviewNumber=" + reviewNumber + ", ordersNumber=" + ordersNumber + ", businessNumber="
				+ businessNumber + ", MemberNumber=" + MemberNumber + ", reviewRating=" + reviewRating
				+ ", reviewContent=" + reviewContent + ", reviewCreateDate=" + reviewCreateDate + "]";
	}
	
	
}
