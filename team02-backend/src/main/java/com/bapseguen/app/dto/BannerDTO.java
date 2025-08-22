package com.bapseguen.app.dto;

public class BannerDTO {

	private int bannerNumber; // 배너 번호
	private String bannerTitle; // 배너 제목
	private int imageNumber; // 이미지 번호
	private String bannerEndDate; // 배너 마감 날짜
	private boolean bannerIsActive; // 배너 상태
	private int memberNumber; // 사용자 번호
	private String bannerCreatedDate; // 배너 생성 일자
	private String bannerUpdatedDate; // 배너 수정 일자
	
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
				+ ", memberNumber=" + memberNumber + ", bannerCreatedDate=" + bannerCreatedDate + ", bannerUpdatedDate="
				+ bannerUpdatedDate + "]";
	}
	
}
