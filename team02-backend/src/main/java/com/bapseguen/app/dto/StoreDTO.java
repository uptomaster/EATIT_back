package com.bapseguen.app.dto;

public class StoreDTO {
//	CREATE TABLE TBL_STORE (
//			  BUSINESS_NUMBER  VARCHAR2(50) PRIMARY KEY,
//			  MEMBER_NUMBER    NUMBER NOT NULL
//			                     CONSTRAINT UQ_STORE_MEMBER UNIQUE
//			                     CONSTRAINT FK_STORE_SELLER
//			                     REFERENCES TBL_SELLER_MEMBER(MEMBER_NUMBER),
//			  STORE_NAME       VARCHAR2(100) NOT NULL,
//			  STORE_OPEN_DATE        DATE,
//			  STORE_TEL        VARCHAR2(20),
//			  STORE_ADDRESS    VARCHAR2(200),
//			  STORE_ADDRESS_DETAIL   VARCHAR2(200),
//			  STORE_ZIP_CODE         VARCHAR2(10)
//			);
	
	private String businessNumber;
	private int memberNumber;
	private String storeName;
	private String storeOpenDate;
	private String storeTel;
	private String storeAddress;
	private String storeAddressDetail;
	private String storeZipCode;
	
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
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreOpenDate() {
		return storeOpenDate;
	}
	public void setStoreOpenDate(String storeOpenDate) {
		this.storeOpenDate = storeOpenDate;
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
	@Override
	public String toString() {
		return "StoreDTO [businessNumber=" + businessNumber + ", memberNumber=" + memberNumber + ", storeName="
				+ storeName + ", storeOpenDate=" + storeOpenDate + ", storeTel=" + storeTel + ", storeAddress="
				+ storeAddress + ", storeAddressDetail=" + storeAddressDetail + ", storeZipCode=" + storeZipCode + "]";
	}
	
	
}
