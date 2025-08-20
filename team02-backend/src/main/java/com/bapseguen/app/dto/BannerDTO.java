package com.bapseguen.app.dto;

public class BannerDTO {

	private int bannerNumber;
	private String bannerTitle;
	private int imageNumber;
	private String bannerEndDate;
	private boolean bannerIsActive;
	private int bannerCreatedBy;
	private int memberNumber;
	private String bannerCreatedDate;
	private String bannerUpdatedDate;
	
	// getter & setter
	public int getBannerNumber() {
		return bannerNumber;
	}
	public void setBannerNumber(int bannerNumber) {
		this.bannerNumber = bannerNumber;
	}
	public String getBannerTitle() {
		return bannerTitle;
	}
	public void setBannerTitle(String bannerTitle) {
		this.bannerTitle = bannerTitle;
	}
	public int getImageNumber() {
		return imageNumber;
	}
	public void setImageNumber(int imageNumber) {
		this.imageNumber = imageNumber;
	}
	public String getBannerEndDate() {
		return bannerEndDate;
	}
	public void setBannerEndDate(String bannerEndDate) {
		this.bannerEndDate = bannerEndDate;
	}
	public boolean isBannerIsActive() {
		return bannerIsActive;
	}
	public void setBannerIsActive(boolean bannerIsActive) {
		this.bannerIsActive = bannerIsActive;
	}
	public int getBannerCreatedBy() {
		return bannerCreatedBy;
	}
	public void setBannerCreatedBy(int bannerCreatedBy) {
		this.bannerCreatedBy = bannerCreatedBy;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getBannerCreatedDate() {
		return bannerCreatedDate;
	}
	public void setBannerCreatedDate(String bannerCreatedDate) {
		this.bannerCreatedDate = bannerCreatedDate;
	}
	public String getBannerUpdatedDate() {
		return bannerUpdatedDate;
	}
	public void setBannerUpdatedDate(String bannerUpdatedDate) {
		this.bannerUpdatedDate = bannerUpdatedDate;
	}
	
	@Override
	public String toString() {
		return "BannerDTO [bannerNumber=" + bannerNumber + ", bannerTitle=" + bannerTitle + ", imageNumber="
				+ imageNumber + ", bannerEndDate=" + bannerEndDate + ", bannerIsActive=" + bannerIsActive
				+ ", bannerCreatedBy=" + bannerCreatedBy + ", memberNumber=" + memberNumber + ", bannerCreatedDate="
				+ bannerCreatedDate + ", bannerUpdatedDate=" + bannerUpdatedDate + "]";
	}
		
	
	
}
