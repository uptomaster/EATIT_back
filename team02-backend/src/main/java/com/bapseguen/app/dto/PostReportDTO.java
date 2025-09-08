package com.bapseguen.app.dto;

public class PostReportDTO {
	private int postReportNumber; // 신고 번호
	private int postNumber; // 신고된 게시글 번호
	private String postReportReason;// 신고 사유
	private String postReportDate; // 신고 일자
	private int postReportCount; // 신고 횟수
	private int memberNumber; // 신고자 회원 번호
	
	private String postTitle; // 신고된 게시글 제목
	private String reporterId; // 신고자 ID
	private int reportedNumber; // 피신고자 회원 번호
	private String reportedName; // 피신고자 이름
	private String reportedType; // 피신고자 타입(GENERAL/SELLER)

	public PostReportDTO() {
	}

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

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
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
		return postReportCount;
	}

	public void setPostReportCount(int postReportCount) {
		this.postReportCount = postReportCount;
	}

	public int getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getReporterId() {
		return reporterId;
	}

	public void setReporterId(String reporterId) {
		this.reporterId = reporterId;
	}

	public int getReportedNumber() {
		return reportedNumber;
	}

	public void setReportedNumber(int reportedNumber) {
		this.reportedNumber = reportedNumber;
	}

	public String getReportedName() {
		return reportedName;
	}

	public void setReportedName(String reportedName) {
		this.reportedName = reportedName;
	}

	public String getReportedType() {
		return reportedType;
	}

	public void setReportedType(String reportedType) {
		this.reportedType = reportedType;
	}

	@Override
	public String toString() {
		return "PostReportDTO{" + "postReportNumber=" + postReportNumber + ", postNumber=" + postNumber
				+ ", postTitle='" + postTitle + '\'' + ", postReportReason='" + postReportReason + '\''
				+ ", postReportDate='" + postReportDate + '\'' + ", postReportCount=" + postReportCount
				+ ", memberNumber=" + memberNumber + ", reporterId='" + reporterId + '\'' + ", reportedNumber="
				+ reportedNumber + ", reportedName='" + reportedName + '\'' + ", reportedType='" + reportedType + '\''
				+ '}';
	}
}
