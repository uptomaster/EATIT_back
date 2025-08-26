package com.bapseguen.app.dto;

public class PostReportDTO {

	private int postReportNumber;
	private int postNumber;
	private String postReportReason;
	private String postReportDate;
	private int PostReportCount;
	private int memberNumber;
	
	public int getPostReportNumber() {
		return postReportNumber;
	}
	public void setPostReportNumber(int postReportNumber) {
		this.postReportNumber = postReportNumber;
	}
	public int getPostNumber() {
		return postNumber;
	}
	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}
	public String getPostReportReason() {
		return postReportReason;
	}
	public void setPostReportReason(String postReportReason) {
		this.postReportReason = postReportReason;
	}
	public String getPostReportDate() {
		return postReportDate;
	}
	public void setPostReportDate(String postReportDate) {
		this.postReportDate = postReportDate;
	}
	public int getPostReportCount() {
		return PostReportCount;
	}
	public void setPostReportCount(int postReportCount) {
		PostReportCount = postReportCount;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	@Override
	public String toString() {
		return "PostReportDTO [postReportNumber=" + postReportNumber + ", postNumber=" + postNumber
				+ ", postReportReason=" + postReportReason + ", postReportDate=" + postReportDate + ", PostReportCount="
				+ PostReportCount + ", memberNumber=" + memberNumber + "]";
	}
	
	
	
}
