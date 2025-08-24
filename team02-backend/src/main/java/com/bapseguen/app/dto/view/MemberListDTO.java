package com.bapseguen.app.dto.view;

public class MemberListDTO {
	
	private int memberNumber;
	private String memberId;
	private String generalName;
	private String sellerName;
	private String memberType;
	private int generalWarningCount;
	private String generalTreeGrade;
	private int sellerWarningCount;
	private String sellerTreeGrade;
	
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
	public String getGeneralMemberName() {
		return generalName;
	}
	public void setGeneralMemberName(String generalMemberName) {
		this.generalName = generalMemberName;
	}
	public String getSellerMemberName() {
		return sellerName;
	}
	public void setSellerMemberName(String sellerMemberName) {
		this.sellerName = sellerMemberName;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public int getGeneralWarningCount() {
		return generalWarningCount;
	}
	public void setGeneralWarningCount(int generalWarningCount) {
		this.generalWarningCount = generalWarningCount;
	}
	public String getGeneralTreeGrade() {
		return generalTreeGrade;
	}
	public void setGeneralTreeGrade(String generalTreeGrade) {
		this.generalTreeGrade = generalTreeGrade;
	}
	public int getSellerWarningCount() {
		return sellerWarningCount;
	}
	public void setSellerWarningCount(int sellerWarningCount) {
		this.sellerWarningCount = sellerWarningCount;
	}
	public String getSellerTreeGrade() {
		return sellerTreeGrade;
	}
	public void setSellerTreeGrade(String sellerTreeGrade) {
		this.sellerTreeGrade = sellerTreeGrade;
	}
	
	@Override
	public String toString() {
		return "MemberListDTO [memberNumber=" + memberNumber + ", memberId=" + memberId + ", generalMemberName="
				+ generalName + ", sellerMemberName=" + sellerName + ", memberType=" + memberType
				+ ", generalWarningCount=" + generalWarningCount + ", generalTreeGrade=" + generalTreeGrade
				+ ", sellerWarningCount=" + sellerWarningCount + ", sellerTreeGrade=" + sellerTreeGrade + "]";
	}
	
}
