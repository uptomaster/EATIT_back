package com.bapseguen.app.dto;

public class AdminDTO {
//	    MEMBER_NUMBER  NUMBER PRIMARY KEY
//      CONSTRAINT FK_ADMIN_MEMBER
//      REFERENCES TBL_MEMBER(MEMBER_NUMBER) ON DELETE CASCADE,
//);

	private int MemberNumber; //관리자 번호
	private String adminTreeGrade; //관리자 등급
	
	public int getAdminMemberNumber() {
		return MemberNumber;
	}
	public void setAdminMemberNumber(int adminMemberNumber) {
		this.MemberNumber = adminMemberNumber;
	}
	public String getAdminTreeGrade() {
		return adminTreeGrade;
	}
	public void setAdminTreeGrade(String adminTreeGrade) {
		this.adminTreeGrade = adminTreeGrade;
	}
	@Override
	public String toString() {
		return "AdminDTO [MemberNumber=" + MemberNumber + ", adminTreeGrade=" + adminTreeGrade + "]";
	}
	
	
}