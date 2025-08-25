package com.bapseguen.app.dto.view;

public class MemberDetailDTO {
	private int memberNumber; // 회원 번호
	private String memberId; // 아이디
	private String memberType; // 회원 유형 (GENERAL / SELLER)
	private String memberName; // 이름
	private int warningCount; // 경고 수
	private String treeGrade; // 등급

	// getter / setter
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

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getWarningCount() {
		return warningCount;
	}

	public void setWarningCount(int warningCount) {
		this.warningCount = warningCount;
	}

	public String getTreeGrade() {
		return treeGrade;
	}

	public void setTreeGrade(String treeGrade) {
		this.treeGrade = treeGrade;
	}

	@Override
	public String toString() {
		return "MemberDetailDTO [memberNumber=" + memberNumber + ", memberId=" + memberId + ", memberType=" + memberType
				+ ", memberName=" + memberName + ", warningCount=" + warningCount + ", treeGrade=" + treeGrade + "]";
	}
}
