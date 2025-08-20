package com.bapseguen.app.dto;

public class MemberDTO {

	private int memberNumber;
	private String memberType;
	private String memberId;
	private String memberPassword;
	
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
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
	@Override
	public String toString() {
		return "MemberDTO [memberNumber=" + memberNumber + ", memberType=" + memberType + ", memberId=" + memberId
				+ ", memberPassword=" + memberPassword + "]";
	}
}