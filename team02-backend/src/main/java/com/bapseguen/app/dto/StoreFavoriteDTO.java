package com.bapseguen.app.dto;

public class StoreFavoriteDTO {
	
	private int favoriteNumber;
	private int memberNumber;
	private String businessNumber;
	private String StoreFavoriteCreatedDate;
	public int getFavoriteNumber() {
		return favoriteNumber;
	}
	public void setFavoriteNumber(int favoriteNumber) {
		this.favoriteNumber = favoriteNumber;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}
	public String getStoreFavoriteCreatedDate() {
		return StoreFavoriteCreatedDate;
	}
	public void setStoreFavoriteCreatedDate(String storeFavoriteCreatedDate) {
		StoreFavoriteCreatedDate = storeFavoriteCreatedDate;
	}
	@Override
	public String toString() {
		return "StoreFavoriteDTO [favoriteNumber=" + favoriteNumber + ", memberNumber=" + memberNumber
				+ ", businessNumber=" + businessNumber + ", StoreFavoriteCreatedDate=" + StoreFavoriteCreatedDate + "]";
	}
	
	
}
