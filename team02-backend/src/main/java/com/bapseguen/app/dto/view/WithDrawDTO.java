package com.bapseguen.app.dto.view;

public class WithDrawDTO {
	private int memberNumber;
	private String memberPassword;
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	@Override
	public String toString() {
		return "WithDrawDTO [memberNumber=" + memberNumber + ", memberPassword=" + memberPassword + "]";
	}
	
	
}
