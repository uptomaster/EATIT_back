package com.bapseguen.app.dto;

public class StoreDTO {

	private String businessNumber;
	private int memberNumber;
	private String storeName;
	private String storeOpenDate;
	private String storeTel;
	private String storeAddress;
	private String storeAddressDetail;
	private String storeZipCode;
	private String storeOpenTime;
	private String storeCloseTime;
	private double latitude;
	private double longitude;
	private double distance;
	
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
	public String getStoreOpenTime() {
		return storeOpenTime;
	}
	public void setStoreOpenTime(String storeOpenTime) {
		this.storeOpenTime = storeOpenTime;
	}
	public String getStoreCloseTime() {
		return storeCloseTime;
	}
	public void setStoreCloseTime(String storeCloseTime) {
		this.storeCloseTime = storeCloseTime;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	@Override
	public String toString() {
		return "StoreDTO [businessNumber=" + businessNumber + ", memberNumber=" + memberNumber + ", storeName="
				+ storeName + ", storeOpenDate=" + storeOpenDate + ", storeTel=" + storeTel + ", storeAddress="
				+ storeAddress + ", storeAddressDetail=" + storeAddressDetail + ", storeZipCode=" + storeZipCode
				+ ", storeOpenTime=" + storeOpenTime + ", storeCloseTime=" + storeCloseTime + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", distance=" + distance + "]";
	}

	
}
