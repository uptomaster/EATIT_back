package com.bapseguen.app.dto.view;

public class MemberListDTO {
	
	private int MemberNumber;
	private String MemberId;
	private String MemberType;
	private String MemberName;
	private String WarningCount;
	private String TreeGrade;
	
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
	public String getMemberName() {
		return MemberName;
	}
	public void setMemberName(String memberName) {
		MemberName = memberName;
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
				+ ", MemberName=" + MemberName + ", WarningCount=" + WarningCount + ", TreeGrade=" + TreeGrade + "]";
	}
		
}
