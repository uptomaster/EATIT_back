package com.bapseguen.app.dto.view;

public class InquiryCommentDTO {
    private int commentNumber;      // 댓글 번호
    private int postNumber;         // 문의글 번호 (FK)
    private int memberNumber;       // 작성자(관리자) 번호
    private String commentContent;  // 댓글 내용
    private String commentCreatedDate; // 작성일자

    // Getter & Setter
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
    public String getCommentCreatedDate() {
        return commentCreatedDate;
    }
    public void setCommentCreatedDate(String commentCreatedDate) {
        this.commentCreatedDate = commentCreatedDate;
    }
}
