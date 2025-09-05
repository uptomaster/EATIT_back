package com.bapseguen.app.dto;

public class AdminImageDTO {
	private int adminImageNumber; // ADMIN_IMAGE_NUMBER (PK)
	private String adminImageSystemName; // ADMIN_IMAGE_SYSTEM_NAME
	private String adminImageOriginalName; // ADMIN_IMAGE_ORIGINAL_NAME
	private int adminNumber; // FK → TBL_ADMIN.ADMIN_NUMBER
	private int postNumber; // FK → TBL_POST.POST_NUMBER
	private String postTitle;

	public AdminImageDTO() {
	}

	// getter & setter
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

	public int getAdminNumber() {
		return adminNumber;
	}

	public void setAdminNumber(int adminNumber) {
		this.adminNumber = adminNumber;
	}

	public int getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	@Override
	public String toString() {
		return "AdminImageDTO [adminImageNumber=" + adminImageNumber + ", adminImageSystemName=" + adminImageSystemName
				+ ", adminImageOriginalName=" + adminImageOriginalName + ", adminNumber=" + adminNumber
				+ ", postNumber=" + postNumber + ", postTitle=" + postTitle + "]";
	}

}
