package com.bapseguen.app.dto.view;

public class StoreReivewsDTO {
	// 리뷰 테이블
	private int reviewNumber;
	private int ordersNumber;
	private int businessNumber;
	private int memberNumber;
	private int reviewRating;
	private String reviewContent;
	private String reviewCreateDate;
	// 구매자 아이디
	private String memberId;
	// 구매일
	private String ordersDate;
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
	public int getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(int businessNumber) {
		this.businessNumber = businessNumber;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
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
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getOrdersDate() {
		return ordersDate;
	}
	public void setOrdersDate(String ordersDate) {
		this.ordersDate = ordersDate;
	}
	@Override
	public String toString() {
		return "StoreReivewsDTO [businessNumber=" + businessNumber + ", memberId=" + memberId + ", memberNumber="
				+ memberNumber + ", ordersDate=" + ordersDate + ", ordersNumber=" + ordersNumber + ", reviewContent="
				+ reviewContent + ", reviewCreateDate=" + reviewCreateDate + ", reviewNumber=" + reviewNumber
				+ ", reviewRating=" + reviewRating + "]";
	}

}
