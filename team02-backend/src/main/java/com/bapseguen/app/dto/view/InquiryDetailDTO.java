package com.bapseguen.app.dto.view;

import java.util.List;
import com.bapseguen.app.dto.PostImageDTO;

public class InquiryDetailDTO {
	
	//고객센터
	private int postNumber;
	private String inquiryContent;
	private String inquiryStatus;
	
	//게시글 정보
	private String postType; // ‘NOTICE’,’FREE’,’PROMOTION’,’RECIPE’,’INQUIRY’,’FAQ’
	private String treeGrade; //CHECK (MEMBER_TREE_GRADE IN ('씨앗','새싹','잎새','가지','나무'))
	private int memberNumber;
	private String memberId;
	private String postTitle;
	private String postCreatedDate;
	private String postUpdatedDate;
	private int postViewCount;
	private int postLikeCount;
	private List<PostImageDTO> files;
	
	public int getPostNumber() {
		return postNumber;
	}
	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}
	public String getInquiryContent() {
		return inquiryContent;
	}
	public void setInquiryContent(String inquiryContent) {
		this.inquiryContent = inquiryContent;
	}
	public String getInquiryStatus() {
		return inquiryStatus;
	}
	public void setInquiryStatus(String inquiryStatus) {
		this.inquiryStatus = inquiryStatus;
	}
	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	public String getTreeGrade() {
		return treeGrade;
	}
	public void setTreeGrade(String treeGrade) {
		this.treeGrade = treeGrade;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
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
	public List<PostImageDTO> getFiles() {
		return files;
	}
	public void setFiles(List<PostImageDTO> files) {
		this.files = files;
	}
	
	@Override
	public String toString() {
		return "InquiryDetailDTO [postNumber=" + postNumber + ", inquiryContent=" + inquiryContent + ", inquiryStatus="
				+ inquiryStatus + ", postType=" + postType + ", treeGrade=" + treeGrade + ", memberNumber="
				+ memberNumber + ", memberId=" + memberId + ", postTitle=" + postTitle + ", postCreatedDate="
				+ postCreatedDate + ", postUpdatedDate=" + postUpdatedDate + ", postViewCount=" + postViewCount
				+ ", postLikeCount=" + postLikeCount + "]";
	}
		
}
