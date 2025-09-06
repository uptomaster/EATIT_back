package com.bapseguen.app.dto;

import java.util.Date;

public class NoticeDTO {

    private int postNumber;
    private String postType;       // 'NOTICE' 고정
    private String postTitle;
    private String noticeContent;
    private Date postCreatedDate;
    private Date postUpdatedDate;
    private int postViewCount;
    private boolean deleteState;
    private int postLikeCount;

    

	// 작성자 (관리자)
    private String adminId;
    private int adminNumber;

    public int getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(int postNumber) {
        this.postNumber = postNumber;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Date getPostCreatedDate() {
        return postCreatedDate;
    }

    public void setPostCreatedDate(Date postCreatedDate) {
        this.postCreatedDate = postCreatedDate;
    }

    public Date getPostUpdatedDate() {
        return postUpdatedDate;
    }

    public void setPostUpdatedDate(Date postUpdatedDate) {
        this.postUpdatedDate = postUpdatedDate;
    }

    public int getPostViewCount() {
        return postViewCount;
    }

    public void setPostViewCount(int postViewCount) {
        this.postViewCount = postViewCount;
    }

    public boolean isDeleteState() {
        return deleteState;
    }

    public void setDeleteState(boolean deleteState) {
        this.deleteState = deleteState;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public int getAdminNumber() {
        return adminNumber;
    }

    public void setAdminNumber(int adminNumber) {
        this.adminNumber = adminNumber;
    }
    public int getPostLikeCount() {
		return postLikeCount;
	}

	public void setPostLikeCount(int postLikeCount) {
		this.postLikeCount = postLikeCount;
	}

    @Override
    public String toString() {
        return "NoticeDetailDTO [postNumber=" + postNumber + ", postType=" + postType + ", postTitle=" + postTitle
                + ", noticeContent=" + noticeContent + ", postCreatedDate=" + postCreatedDate + ", postUpdatedDate="
                + postUpdatedDate + ", postViewCount=" + postViewCount + ", deleteState=" + deleteState + ", adminId="
                + adminId + ", adminNumber=" + adminNumber + "]";
    }
}
