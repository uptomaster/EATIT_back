package com.bapseguen.app.dto;

public class MemberBlacklistDTO {

	private int blacklistNumber;
	private int memberNumber;
	private String blacklistStartDate;
	
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
		return "MemberBlacklistDTO [blacklistNumber=" + blacklistNumber + ", memberNumber=" + memberNumber
				+ ", blacklistStartDate=" + blacklistStartDate + "]";
	}
	
	
}
