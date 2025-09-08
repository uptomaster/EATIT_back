package com.bapseguen.app.dto;

public class MemberBlacklistDTO {
	private int blacklistNumber; // 블랙리스트 PK
	private int memberNumber; // 블랙리스트 대상 회원 번호
	private String memberName; // 회원 이름 (조인으로 가져옴)
	private String memberType; // 회원 유형 (GENERAL / SELLER)
	private String blacklistStartDate; // 블랙리스트 등록일 (문자열 포맷)

	public MemberBlacklistDTO() {
	}

	public int getBlacklistNumber() {
		return blacklistNumber;
	}

	public void setBlacklistNumber(int blacklistNumber) {
		this.blacklistNumber = blacklistNumber;
	}

	public int getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getBlacklistStartDate() {
		return blacklistStartDate;
	}

	public void setBlacklistStartDate(String blacklistStartDate) {
		this.blacklistStartDate = blacklistStartDate;
	}

	@Override
	public String toString() {
		return "MemberBlacklistDTO{" + "blacklistNumber=" + blacklistNumber + ", memberNumber=" + memberNumber
				+ ", memberName='" + memberName + '\'' + ", memberType='" + memberType + '\'' + ", blacklistStartDate='"
				+ blacklistStartDate + '\'' + '}';
	}
}
