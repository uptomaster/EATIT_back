package com.bapseguen.app.dto.view;

public class MyPageDTO {
	
	private int memberNumber;
	private String memberId;
	private String memberPassword;
	private String generalName;
	private String generalPhoneNumber;
	private String generalBirthDate;
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
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public String getGeneralName() {
		return generalName;
	}
	public void setGeneralName(String generalName) {
		this.generalName = generalName;
	}
	public String getGeneralPhoneNumber() {
		return generalPhoneNumber;
	}
	public void setGeneralPhoneNumber(String generalPhoneNumber) {
		this.generalPhoneNumber = generalPhoneNumber;
	}
	public String getGeneralBirthDate() {
		return generalBirthDate;
	}
	public void setGeneralBirthDate(String generalBirthDate) {
		this.generalBirthDate = generalBirthDate;
	}
	@Override
	public String toString() {
		return "MyPage [memberNumber=" + memberNumber + ", memberId=" + memberId + ", memberPassword=" + memberPassword
				+ ", generalName=" + generalName + ", generalPhoneNumber=" + generalPhoneNumber + ", generalBirthDate="
				+ generalBirthDate + "]";
	}
	
}
