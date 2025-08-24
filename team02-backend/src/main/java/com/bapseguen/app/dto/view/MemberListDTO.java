package com.bapseguen.app.dto.view;

public class MemberListDTO {
	
	private int MemberNumber;
	private String MemberId;
	private String MemberType;
	private String GeneralName;
	private String SellerName;
	private String WarningCount;
	private String TreeGrade;
	private String MemberName;

	public String getMemberName() {
		return MemberName;
	}
	public void setMemberName(String memberName) {
		MemberName = memberName;
	}
	public int getMemberNumber() {
		return MemberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		MemberNumber = memberNumber;
	}
	public String getMemberId() {
		return MemberId;
	}
	public void setMemberId(String memberId) {
		MemberId = memberId;
	}
	public String getMemberType() {
		return MemberType;
	}
	public void setMemberType(String memberType) {
		MemberType = memberType;
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
	
	@Override
	public String toString() {
		return "MemberListDTO [MemberNumber=" + MemberNumber + ", MemberId=" + MemberId + ", MemberType=" + MemberType
				+ ", GeneralName=" + GeneralName + ", SellerName=" + SellerName + ", WarningCount=" + WarningCount
				+ ", TreeGrade=" + TreeGrade + ", MemberName=" + MemberName + "]";
	}
	
}
