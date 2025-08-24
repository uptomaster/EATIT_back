package com.bapseguen.app.dto.view;

public class CommentListDTO {
	private int commentNumber;
    private int postNumber;
    private int memberNumber;
    private String commentContent;
    private String commentedDate; 
    private boolean commentDeleteState;
    private String memberId;
    
    
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
	
	@Override
	public String toString() {
		return "CommentListDTO [commentNumber=" + commentNumber + ", postNumber=" + postNumber + ", memberNumber="
				+ memberNumber + ", commentContent=" + commentContent + ", commentedDate=" + commentedDate
				+ ", commentDeleteState=" + commentDeleteState + ", memberId=" + memberId + "]";
	}
    
	
    
}
