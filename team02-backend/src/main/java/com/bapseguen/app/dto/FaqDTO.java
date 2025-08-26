package com.bapseguen.app.dto;

public class FaqDTO {

	private int postNumber;
	private String faqContent;
	
	//+추가	
	private String postType; // ‘NOTICE’,’FREE’,’PROMOTION’,’RECIPE’,’INQUIRY’,’FAQ’private int adminMemberNumber;
	private String adminTreeGrade;
	private String postTitle;
	private String postCreatedDate;
	private String postUpdatedDate;
	private int postViewCount;
	private int postLikeCount;
	
	// getter & setter
	public int getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}

	public String getFaqContent() {
		return faqContent;
	}

	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public String getAdminTreeGrade() {
		return adminTreeGrade;
	}

	public void setAdminTreeGrade(String adminTreeGrade) {
		this.adminTreeGrade = adminTreeGrade;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostCreatedDate() {
		return postCreatedDate;
	}

	public void setPostCreatedDate(String postCreatedDate) {
		this.postCreatedDate = postCreatedDate;
	}

	public String getPostUpdatedDate() {
		return postUpdatedDate;
	}

	public void setPostUpdatedDate(String postUpdatedDate) {
		this.postUpdatedDate = postUpdatedDate;
	}

	public int getPostViewCount() {
		return postViewCount;
	}

	public void setPostViewCount(int postViewCount) {
		this.postViewCount = postViewCount;
	}

	public int getPostLikeCount() {
		return postLikeCount;
	}

	public void setPostLikeCount(int postLikeCount) {
		this.postLikeCount = postLikeCount;
	}

	@Override
	public String toString() {
		return "FaqDTO [postNumber=" + postNumber + ", faqContent=" + faqContent + ", postType=" + postType
				+ ", adminTreeGrade=" + adminTreeGrade + ", postTitle=" + postTitle + ", postCreatedDate="
				+ postCreatedDate + ", postUpdatedDate=" + postUpdatedDate + ", postViewCount=" + postViewCount
				+ ", postLikeCount=" + postLikeCount + "]";
	}


	
}
