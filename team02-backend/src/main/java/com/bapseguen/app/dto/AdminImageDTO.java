package com.bapseguen.app.dto;

public class AdminImageDTO {

	private int adminImageNumber; // 이미지 번호
	private String adminImageSystemName; // 서버에 저장되는 실제 파일명
//	new DefaultFileRenamePolicy() : 파일명이 중복될 경우 자동으로 이름 변경해주는 정책(수업시간 참고 -> BoardWriteOkController)
	private String adminImageOriginalName; //  원본 파일명
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
