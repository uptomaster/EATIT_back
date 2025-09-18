package com.bapseguen.app.dto;

public class StoreImageDTO {
	private int storeImageNumber;
	private String storeImageSystemName;
	private String storeImageOriginalName;
	private String businessNumber;
	
	
	public int getStoreImageNumber() {
		return storeImageNumber;
	}
	public void setStoreImageNumber(int storeImageNumber) {
		this.storeImageNumber = storeImageNumber;
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
	public String getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}
	
	@Override
	public String toString() {
		return "StoreImageDTO [storeImageNumber=" + storeImageNumber + ", storeImageSystemName=" + storeImageSystemName
				+ ", storeImageOriginalName=" + storeImageOriginalName + ", businessNumber=" + businessNumber + "]";
	}
	
}
