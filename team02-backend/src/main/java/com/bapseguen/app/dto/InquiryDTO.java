package com.bapseguen.app.dto;

public class InquiryDTO {

	private int postNumber;
	private String inquiryContent;
	private String inquiryStatus;
	
	//+추가	
	private String postType; // ‘NOTICE’,’FREE’,’PROMOTION’,’RECIPE’,’INQUIRY’,’FAQ’
	private String treeGrade; //CHECK (MEMBER_TREE_GRADE IN ('씨앗','새싹','잎새','가지','나무'))
	private int memberNumber;
	private String memberId;
	private String postTitle;
	private String postCreatedDate;
	private String postUpdatedDate;
	private int postViewCount;
	private int postLikeCount;
	
	protected int getPostNumber() {
		return postNumber;
	}
	protected void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}
	protected String getInquiryContent() {
		return inquiryContent;
	}
	protected void setInquiryContent(String inquiryContent) {
		this.inquiryContent = inquiryContent;
	}
	protected String getInquiryStatus() {
		return inquiryStatus;
	}
	protected void setInquiryStatus(String inquiryStatus) {
		this.inquiryStatus = inquiryStatus;
	}
	protected String getPostType() {
		return postType;
	}
	protected void setPostType(String postType) {
		this.postType = postType;
	}
	protected String getTreeGrade() {
		return treeGrade;
	}
	protected void setTreeGrade(String treeGrade) {
		this.treeGrade = treeGrade;
	}
	protected int getMemberNumber() {
		return memberNumber;
	}
	protected void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	protected String getMemberId() {
		return memberId;
	}
	protected void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	protected String getPostTitle() {
		return postTitle;
	}
	protected void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	protected String getPostCreatedDate() {
		return postCreatedDate;
	}
	protected void setPostCreatedDate(String postCreatedDate) {
		this.postCreatedDate = postCreatedDate;
	}
	protected String getPostUpdatedDate() {
		return postUpdatedDate;
	}
	protected void setPostUpdatedDate(String postUpdatedDate) {
		this.postUpdatedDate = postUpdatedDate;
	}
	protected int getPostViewCount() {
		return postViewCount;
	}
	protected void setPostViewCount(int postViewCount) {
		this.postViewCount = postViewCount;
	}
	protected int getPostLikeCount() {
		return postLikeCount;
	}
	protected void setPostLikeCount(int postLikeCount) {
		this.postLikeCount = postLikeCount;
	}
	
	@Override
	public String toString() {
		return "InquiryDTO [postNumber=" + postNumber + ", inquiryContent=" + inquiryContent + ", inquiryStatus="
				+ inquiryStatus + ", postType=" + postType + ", treeGrade=" + treeGrade + ", memberNumber="
				+ memberNumber + ", memberId=" + memberId + ", postTitle=" + postTitle + ", postCreatedDate="
				+ postCreatedDate + ", postUpdatedDate=" + postUpdatedDate + ", postViewCount=" + postViewCount
				+ ", postLikeCount=" + postLikeCount + "]";
	}
	
	

	
}