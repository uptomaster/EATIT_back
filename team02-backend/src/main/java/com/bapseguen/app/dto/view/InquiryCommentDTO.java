package com.bapseguen.app.dto.view;

public class InquiryCommentDTO {
    private int commentNumber;      // 댓글 번호
    private int postNumber;         // 문의글 번호 (FK)
    private int adminNumber;       // 작성자(관리자) 번호
    private String commentContent;  // 댓글 내용
    private String commentCreatedDate; // 작성일자
    
	public int getCommentNumber() {
		return commentNumber;
	}
	

    // Getter & Setter
	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}
	public int getPostNumber() {
		return postNumber;
	}
	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
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
	public String getCommentCreatedDate() {
		return commentCreatedDate;
	}
	public void setCommentCreatedDate(String commentCreatedDate) {
		this.commentCreatedDate = commentCreatedDate;
	}
	@Override
	public String toString() {
		return "InquiryCommentDTO [commentNumber=" + commentNumber + ", postNumber=" + postNumber + ", adminNumber="
				+ adminNumber + ", commentContent=" + commentContent + ", commentCreatedDate=" + commentCreatedDate
				+ "]";
	}

    
    
}
