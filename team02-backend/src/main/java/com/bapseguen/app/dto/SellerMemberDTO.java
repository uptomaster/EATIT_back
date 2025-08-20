package com.bapseguen.app.dto;

public class SellerMemberDTO {

	private int memberNumber;
	private int sellerReportCount;
	private int sellerWarningCount;
	private boolean sellerBlackState;
	private String sellerName;
	private String sellerBirthdate;
	private String sellerPhoneNumber;
	private String sellerJoinDate;
	private String sellerUpdatedDate;
	private String sellerTreeGrade;
	private int sellerPaymentAmount;
	
	
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public int getSellerReportCount() {
		return sellerReportCount;
	}
	public void setSellerReportCount(int sellerReportCount) {
		this.sellerReportCount = sellerReportCount;
	}
	public int getSellerWarningCount() {
		return sellerWarningCount;
	}
	public void setSellerWarningCount(int sellerWarningCount) {
		this.sellerWarningCount = sellerWarningCount;
	}
	public boolean isSellerBlackState() {
		return sellerBlackState;
	}
	public void setSellerBlackState(boolean sellerBlackState) {
		this.sellerBlackState = sellerBlackState;
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
	public String getSellerJoinDate() {
		return sellerJoinDate;
	}
	public void setSellerJoinDate(String sellerJoinDate) {
		this.sellerJoinDate = sellerJoinDate;
	}
	public String getSellerUpdatedDate() {
		return sellerUpdatedDate;
	}
	public void setSellerUpdatedDate(String sellerUpdatedDate) {
		this.sellerUpdatedDate = sellerUpdatedDate;
	}
	public String getSellerTreeGrade() {
		return sellerTreeGrade;
	}
	public void setSellerTreeGrade(String sellerTreeGrade) {
		this.sellerTreeGrade = sellerTreeGrade;
	}
	public int getSellerPaymentAmount() {
		return sellerPaymentAmount;
	}
	public void setSellerPaymentAmount(int sellerPaymentAmount) {
		this.sellerPaymentAmount = sellerPaymentAmount;
	}
	@Override
	public String toString() {
		return "SellerMemberDTO [memberNumber=" + memberNumber + ", sellerReportCount=" + sellerReportCount
				+ ", sellerWarningCount=" + sellerWarningCount + ", sellerBlackState=" + sellerBlackState
				+ ", sellerName=" + sellerName + ", sellerBirthdate=" + sellerBirthdate + ", sellerPhoneNumber="
				+ sellerPhoneNumber + ", sellerJoinDate=" + sellerJoinDate + ", sellerUpdatedDate=" + sellerUpdatedDate
				+ ", sellerTreeGrade=" + sellerTreeGrade + ", sellerPaymentAmount=" + sellerPaymentAmount + "]";
	}
	
	
	
}
