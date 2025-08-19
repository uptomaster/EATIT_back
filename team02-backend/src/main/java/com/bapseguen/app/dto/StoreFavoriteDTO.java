package com.bapseguen.app.dto;

public class StoreFavoriteDTO {
//	CREATE TABLE TBL_STORE_FAVORITE (
//			  MEMBER_NUMBER    NUMBER
//			                     CONSTRAINT FK_SFAV_MEMBER
//			                     REFERENCES TBL_MEMBER(MEMBER_NUMBER) ON DELETE CASCADE,
//			  BUSINESS_NUMBER  VARCHAR2(50)
//			                     CONSTRAINT FK_SFAV_STORE
//			                     REFERENCES TBL_STORE(BUSINESS_NUMBER) ON DELETE CASCADE,
//			  STORE_FAVORITE_CREATED_DATE     DATE DEFAULT SYSDATE NOT NULL,
//			  CONSTRAINT PK_STORE_FAVORITE PRIMARY KEY (MEMBER_NUMBER, BUSINESS_NUMBER)
//			);
	private int memberNumber;
	private String businessNumber;
	private String storeFavoriteCreatedDate;
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
		return storeFavoriteCreatedDate;
	}
	public void setStoreFavoriteCreatedDate(String storeFavoriteCreatedDate) {
		this.storeFavoriteCreatedDate = storeFavoriteCreatedDate;
	}
	@Override
	public String toString() {
		return "StoreFavoriteDTO [memberNumber=" + memberNumber + ", businessNumber=" + businessNumber
				+ ", storeFavoriteCreatedDate=" + storeFavoriteCreatedDate + "]";
	}
	
	
}
