package com.bapseguen.app.dto.view;

public class ReviewWithUserDTO {
	private int reviewNumber;
	private int ordersNumber;
	private String businessNumber;
	private int memberNumber;
	private int reviewRating;
	private String reviewContent;
	private String reviewCreateDate;

	// 유저 닉네임/아이디 (조인해서 가져오기)
	private String memberId;

	// 상품명 (선택: 주문 아이템과 조인)
	private String itemName;

	// getter/setter
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
