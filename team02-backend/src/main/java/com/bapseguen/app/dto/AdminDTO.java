package com.bapseguen.app.dto;

public class AdminDTO {
//	    MEMBER_NUMBER  NUMBER PRIMARY KEY
//      CONSTRAINT FK_ADMIN_MEMBER
//      REFERENCES TBL_MEMBER(MEMBER_NUMBER) ON DELETE CASCADE,
//);

	private int memberNumber; //관리자 번호
	private String adminTreeGrade; //관리자 등급
	
	// getter & setter
	public int getAdminMemberNumber() {
		return memberNumber;
	}
	public void setAdminMemberNumber(int adminMemberNumber) {
		this.memberNumber = adminMemberNumber;
	}
	public String getAdminTreeGrade() {
		return adminTreeGrade;
	}
	public void setAdminTreeGrade(String adminTreeGrade) {
		this.adminTreeGrade = adminTreeGrade;
	}
	
	// toString 오버라이딩
	@Override
	public String toString() {
		return "AdminDTO [MemberNumber=" + memberNumber + ", adminTreeGrade=" + adminTreeGrade + "]";
	}
	
	
}