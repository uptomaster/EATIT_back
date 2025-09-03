package com.bapseguen.app.dto;

public class MemberBlacklistDTO {
	private int blacklistNumber; // 블랙리스트 PK
	private int memberNumber; // 블랙리스트 대상 회원 번호
	private String blacklistStartDate; // 블랙리스트 등록일

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

	public String getBlacklistStartDate() {
		return blacklistStartDate;
	}

	public void setBlacklistStartDate(String blacklistStartDate) {
		this.blacklistStartDate = blacklistStartDate;
	}

	@Override
	public String toString() {
		return "MemberBlacklistDTO{" + "blacklistNumber=" + blacklistNumber + ", memberNumber=" + memberNumber
				+ ", blacklistStartDate='" + blacklistStartDate + '\'' + '}';
	}
}
