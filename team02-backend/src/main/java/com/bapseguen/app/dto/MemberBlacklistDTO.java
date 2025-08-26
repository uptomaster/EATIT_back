package com.bapseguen.app.dto;

public class MemberBlacklistDTO {
	private int blacklistNumber;
	private int memberNumber;
	private String reason;
	private String createdDate;

	// getter & setter
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
