package com.bapseguen.app.dto.view;

/**
 * 
 */
public class SellerInfoDTO {
	// member
	private int memberNumber;
	private String memberId;
	private String memberPassword;
	// seller
	private String sellerName;
	private String sellerBirthdate;
	private String sellerPhoneNumber;
	private String sellerUpdatedDate;
	// store
	private String businessNumber;
	private String storeName;
	private String storeOpenDate;
	private String storeOpenTime;
	private String storeCloseTime;
	private String storeTel;
	private String storeAddress;
	private String storeAddressDetail;
	private String storeZipCode;
	private float storeLatitude;
	private float storeLongitude;
	
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getSellerBirthdate() {
		return sellerBirthdate;
	}
	public void setSellerBirthdate(String sellerBirthdate) {
		this.sellerBirthdate = sellerBirthdate;
	}
	public String getSellerPhoneNumber() {
		return sellerPhoneNumber;
	}
	public void setSellerPhoneNumber(String sellerPhoneNumber) {
		this.sellerPhoneNumber = sellerPhoneNumber;
	}
	public String getSellerUpdatedDate() {
		return sellerUpdatedDate;
	}
	public void setSellerUpdatedDate(String sellerUpdatedDate) {
		this.sellerUpdatedDate = sellerUpdatedDate;
	}
	public String getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
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
	
	public float getStoreLatitude() {
		return storeLatitude;
	}
	public void setStoreLatitude(float storeLatitude) {
		this.storeLatitude = storeLatitude;
	}
	public float getStoreLongitude() {
		return storeLongitude;
	}
	public void setStoreLongitude(float storeLongitude) {
		this.storeLongitude = storeLongitude;
	}
	@Override
	public String toString() {
		return "SellerInfoDTO [memberNumber=" + memberNumber + ", memberId=" + memberId + ", memberPassword="
				+ memberPassword + ", sellerName=" + sellerName + ", sellerBirthdate=" + sellerBirthdate
				+ ", sellerPhoneNumber=" + sellerPhoneNumber + ", sellerUpdatedDate=" + sellerUpdatedDate
				+ ", businessNumber=" + businessNumber + ", storeName=" + storeName + ", storeOpenDate=" + storeOpenDate
				+ ", storeOpenTime=" + storeOpenTime + ", storeCloseTime=" + storeCloseTime + ", storeTel=" + storeTel
				+ ", storeAddress=" + storeAddress + ", storeAddressDetail=" + storeAddressDetail + ", storeZipCode="
				+ storeZipCode + ", storeLatitude=" + storeLatitude + ", storeLongitude=" + storeLongitude + "]";
	}
	
}
