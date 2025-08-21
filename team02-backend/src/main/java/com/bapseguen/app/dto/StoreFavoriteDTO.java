package com.bapseguen.app.dto;

public class StoreFavoriteDTO {
	
	private int favoriteNumber;
	private int memberNumber;
	private String businessNumber;
	
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

	@Override
	public String toString() {
		return "StoreFavoriteDTO [favoriteNumber=" + favoriteNumber + ", memberNumber=" + memberNumber
				+ ", businessNumber=" + businessNumber + "]";
	}
	
	
}
