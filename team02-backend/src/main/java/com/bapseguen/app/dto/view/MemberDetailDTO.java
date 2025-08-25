package com.bapseguen.app.dto.view;

public class MemberDetailDTO {
	
	private String MemberId;
	private String GeneralName;
	private String SellerName;
	private String MemberName;
	private String GeneralPhoneNumber;
	private String SellerPhoneNumber;
	private String MemberPhoneNumber;
	private String MemberType;
	private String WarningCount;
	private String TreeGrade;
	private String StoreName;
	private String StoreTel;
	private String StoreAddress;
	private String StoreAddressDetail;
	private String StoreOpenDate;
	private String BusinessNumber;
	private String SuspendStartDate;
	private String SuspendEndDate;
	
	public String getMemberId() {
		return MemberId;
	}
	public void setMemberId(String memberId) {
		MemberId = memberId;
	}
	public String getGeneralName() {
		return GeneralName;
	}
	public void setGeneralName(String generalName) {
		GeneralName = generalName;
	}
	public String getSellerName() {
		return SellerName;
	}
	public void setSellerName(String sellerName) {
		SellerName = sellerName;
	}
	public String getMemberName() {
		return MemberName;
	}
	public void setMemberName(String memberName) {
		MemberName = memberName;
	}
	public String getGeneralPhoneNumber() {
		return GeneralPhoneNumber;
	}
	public void setGeneralPhoneNumber(String generalPhoneNumber) {
		GeneralPhoneNumber = generalPhoneNumber;
	}
	public String getSellerPhoneNumber() {
		return SellerPhoneNumber;
	}
	public void setSellerPhoneNumber(String sellerPhoneNumber) {
		SellerPhoneNumber = sellerPhoneNumber;
	}
	public String getMemberPhoneNumber() {
		return MemberPhoneNumber;
	}
	public void setMemberPhoneNumber(String memberPhoneNumber) {
		MemberPhoneNumber = memberPhoneNumber;
	}
	public String getMemberType() {
		return MemberType;
	}
	public void setMemberType(String memberType) {
		MemberType = memberType;
	}
	public String getWarningCount() {
		return WarningCount;
	}
	public void setWarningCount(String warningCount) {
		WarningCount = warningCount;
	}
	public String getTreeGrade() {
		return TreeGrade;
	}
	public void setTreeGrade(String treeGrade) {
		TreeGrade = treeGrade;
	}
	public String getStoreName() {
		return StoreName;
	}
	public void setStoreName(String storeName) {
		StoreName = storeName;
	}
	public String getStoreTel() {
		return StoreTel;
	}
	public void setStoreTel(String storeTel) {
		StoreTel = storeTel;
	}
	public String getStoreAddress() {
		return StoreAddress;
	}
	public void setStoreAddress(String storeAddress) {
		StoreAddress = storeAddress;
	}
	public String getStoreAddressDetail() {
		return StoreAddressDetail;
	}
	public void setStoreAddressDetail(String storeAddressDetail) {
		StoreAddressDetail = storeAddressDetail;
	}
	public String getStoreOpenDate() {
		return StoreOpenDate;
	}
	public void setStoreOpenDate(String storeOpenDate) {
		StoreOpenDate = storeOpenDate;
	}
	public String getBusinessNumber() {
		return BusinessNumber;
	}
	public void setBusinessNumber(String businessNumber) {
		BusinessNumber = businessNumber;
	}
	public String getSuspendStartDate() {
		return SuspendStartDate;
	}
	public void setSuspendStartDate(String suspendStartDate) {
		SuspendStartDate = suspendStartDate;
	}
	public String getSuspendEndDate() {
		return SuspendEndDate;
	}
	public void setSuspendEndDate(String suspendEndDate) {
		SuspendEndDate = suspendEndDate;
	}
	
	@Override
	public String toString() {
		return "MemberDetailDTO [MemberId=" + MemberId + ", GeneralName=" + GeneralName + ", SellerName=" + SellerName
				+ ", MemberName=" + MemberName + ", GeneralPhoneNumber=" + GeneralPhoneNumber + ", SellerPhoneNumber="
				+ SellerPhoneNumber + ", MemberPhoneNumber=" + MemberPhoneNumber + ", MemberType=" + MemberType
				+ ", WarningCount=" + WarningCount + ", TreeGrade=" + TreeGrade + ", StoreName=" + StoreName
				+ ", StoreTel=" + StoreTel + ", StoreAddress=" + StoreAddress + ", StoreAddressDetail="
				+ StoreAddressDetail + ", StoreOpenDate=" + StoreOpenDate + ", BusinessNumber=" + BusinessNumber
				+ ", SuspendStartDate=" + SuspendStartDate + ", SuspendEndDate=" + SuspendEndDate + "]";
	}
		
}
