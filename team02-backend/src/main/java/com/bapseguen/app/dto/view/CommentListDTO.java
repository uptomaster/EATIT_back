package com.bapseguen.app.dto.view;

public class CommentListDTO {
	private int commentNumber;
    private int postNumber;
    private int memberNumber;
    private int adminNumber;
    private String commentContent;
    private String commentedDate; 
    private boolean commentDeleteState;
    private String memberId;
    //게시글 정보
    private String postTitle;
    private String postType; // ‘NOTICE’,’FREE’,’PROMOTION’,’RECIPE’,’INQUIRY’,’FAQ’
    private int commentCount;
	public int getCommentNumber() {
		return commentNumber;
	}
	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}
	public int getPostNumber() {
		return postNumber;
	}
	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public int getAdminNumber() {
		return adminNumber;
	}
	public void setAdminNumber(int adminNumber) {
		this.adminNumber = adminNumber;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentedDate() {
		return commentedDate;
	}
	public void setCommentedDate(String commentedDate) {
		this.commentedDate = commentedDate;
	}
	public boolean isCommentDeleteState() {
		return commentDeleteState;
	}
	public void setCommentDeleteState(boolean commentDeleteState) {
		this.commentDeleteState = commentDeleteState;
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
	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	@Override
	public String toString() {
		return "CommentListDTO [commentNumber=" + commentNumber + ", postNumber=" + postNumber + ", memberNumber="
				+ memberNumber + ", adminNumber=" + adminNumber + ", commentContent=" + commentContent
				+ ", commentedDate=" + commentedDate + ", commentDeleteState=" + commentDeleteState + ", memberId="
				+ memberId + ", postTitle=" + postTitle + ", postType=" + postType + ", commentCount=" + commentCount
				+ "]";
	}
    

	  
	
    
}
