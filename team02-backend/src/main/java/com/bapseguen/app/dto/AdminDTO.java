package com.bapseguen.app.dto;

public class AdminDTO {
	private int adminNumber; // ADMIN_NUMBER (PK)
	private String adminId; // ADMIN_ID
	private String adminPassword; // ADMIN_PASSWORD
	private String adminTreeGrade; // ADMIN_TREE_GRADE (기본값 '관리자')

	public AdminDTO() {
		
	}

	// getter & setter
	public int getAdminNumber() {
		return adminNumber;
	}

	public void setAdminNumber(int adminNumber) {
		this.adminNumber = adminNumber;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminTreeGrade() {
		return adminTreeGrade;
	}

	public void setAdminTreeGrade(String adminTreeGrade) {
		this.adminTreeGrade = adminTreeGrade;
	}

	@Override
	public String toString() {
		return "AdminDTO [adminNumber=" + adminNumber + ", adminId=" + adminId + ", adminPassword=" + adminPassword
				+ ", adminTreeGrade=" + adminTreeGrade + "]";
	}
}
