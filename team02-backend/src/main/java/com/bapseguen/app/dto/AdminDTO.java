package com.bapseguen.app.dto;

public class AdminDTO {
//	  ADMIN_MEMBER_NUMBER  NUMBER PRIMARY KEY
//      CONSTRAINT FK_ADMIN_MEMBER
//      REFERENCES TBL_MEMBER(MEMBER_NUMBER) ON DELETE CASCADE,
//);

	private int adminMemberNumber;
	private String adminTreeGrade;
	
	public int getAdminMemberNumber() {
		return adminMemberNumber;
	}
	public void setAdminMemberNumber(int adminMemberNumber) {
		this.adminMemberNumber = adminMemberNumber;
	}
	public String getAdminTreeGrade() {
		return adminTreeGrade;
	}
	public void setAdminTreeGrade(String adminTreeGrade) {
		this.adminTreeGrade = adminTreeGrade;
	}
	@Override
	public String toString() {
		return "AdminDTO [adminMemberNumber=" + adminMemberNumber + ", adminTreeGrade=" + adminTreeGrade + "]";
	}
	
	
}