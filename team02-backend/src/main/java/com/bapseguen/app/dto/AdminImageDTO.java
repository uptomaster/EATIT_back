package com.bapseguen.app.dto;

public class AdminImageDTO {

	private int adminImageNumber;
	private String adminImageSystemName;
	private String adminImageOriginalName;
	private int memberNumber; // 관리자 회원번호
	
	public int getAdminImageNumber() {
		return adminImageNumber;
	}
	public void setAdminImageNumber(int adminImageNumber) {
		this.adminImageNumber = adminImageNumber;
	}
	public String getAdminImageSystemName() {
		return adminImageSystemName;
	}
	public void setAdminImageSystemName(String adminImageSystemName) {
		this.adminImageSystemName = adminImageSystemName;
	}
	public String getAdminImageOriginalName() {
		return adminImageOriginalName;
	}
	public void setAdminImageOriginalName(String adminImageOriginalName) {
		this.adminImageOriginalName = adminImageOriginalName;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	
	@Override
	public String toString() {
		return "AdminImageDTO [adminImageNumber=" + adminImageNumber + ", adminImageSystemName=" + adminImageSystemName
				+ ", adminImageOriginalName=" + adminImageOriginalName + ", memberNumber=" + memberNumber + "]";
	}
	
	
	
}
