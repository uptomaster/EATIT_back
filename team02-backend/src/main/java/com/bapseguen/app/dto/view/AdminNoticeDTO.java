package com.bapseguen.app.dto.view;

public class AdminNoticeDTO {
    private int postNumber;          // 글 번호
    private int memberNumber;        // 작성자 번호 (관리자)
    private String postTitle;        // 제목
    private String postCreatedDate;  // 작성일자
    private String noticeContent;    // 본문
    private String noticeCategory;   // 공지 or 이벤트

    // Getter & Setter
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

    public String getNoticeContent() {
        return noticeContent;
    }
    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getNoticeCategory() {
        return noticeCategory;
    }
    public void setNoticeCategory(String noticeCategory) {
        this.noticeCategory = noticeCategory;
    }
}
