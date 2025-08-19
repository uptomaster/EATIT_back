package com.bapseguen.app.dto;

public class NoticeDTO {
//	CREATE TABLE TBL_NOTICE (
//			  NOTICE_NUMBER  NUMBER PRIMARY KEY,
//			  ADMIN_NUMBER   NUMBER NOT NULL
//			                   CONSTRAINT FK_NOTICE_ADMIN
//			                   REFERENCES TBL_ADMIN(MEMBER_NUMBER),
//			  NOTICE_TITLE          VARCHAR2(200) NOT NULL,
//			  NOTICE_CONTENT        CLOB NOT NULL,
//			  NOTCIE_VIEW_COUNT     NUMBER DEFAULT 0 NOT NULL,
//			  NOTCIE_LIKE_COUNT     NUMBER DEFAULT 0 NOT NULL,
//			  NOTCIE_CREATED_DATE   DATE DEFAULT SYSDATE NOT NULL,
//			  NOTCIE_UPDATED_DATE   DATE,
//			  NOTCIE_DELETE_STATE   CHAR(1) DEFAULT 'N'
//			                   CONSTRAINT CK_NOTICE_DEL CHECK (NOTCIE_DELETE_STATE IN ('Y','N'))
//			);
	
	private int noticeNumber;
	private int adminNumber;
	private String noticeTitle;
	private String noticeContent;
	private int noticeViewCount;
	private int noticeLikeCount;
	private String noticeCreatedDate;
	private String noticeUpdatedDate;
	private boolean noticeDeleteState;
	public int getNoticeNumber() {
		return noticeNumber;
	}
	public void setNoticeNumber(int noticeNumber) {
		this.noticeNumber = noticeNumber;
	}
	public int getAdminNumber() {
		return adminNumber;
	}
	public void setAdminNumber(int adminNumber) {
		this.adminNumber = adminNumber;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public int getNoticeViewCount() {
		return noticeViewCount;
	}
	public void setNoticeViewCount(int noticeViewCount) {
		this.noticeViewCount = noticeViewCount;
	}
	public int getNoticeLikeCount() {
		return noticeLikeCount;
	}
	public void setNoticeLikeCount(int noticeLikeCount) {
		this.noticeLikeCount = noticeLikeCount;
	}
	public String getNoticeCreatedDate() {
		return noticeCreatedDate;
	}
	public void setNoticeCreatedDate(String noticeCreatedDate) {
		this.noticeCreatedDate = noticeCreatedDate;
	}
	public String getNoticeUpdatedDate() {
		return noticeUpdatedDate;
	}
	public void setNoticeUpdatedDate(String noticeUpdatedDate) {
		this.noticeUpdatedDate = noticeUpdatedDate;
	}
	public boolean isNoticeDeleteState() {
		return noticeDeleteState;
	}
	public void setNoticeDeleteState(boolean noticeDeleteState) {
		this.noticeDeleteState = noticeDeleteState;
	}
	@Override
	public String toString() {
		return "NoticeDTO [noticeNumber=" + noticeNumber + ", adminNumber=" + adminNumber + ", noticeTitle="
				+ noticeTitle + ", noticeContent=" + noticeContent + ", noticeViewCount=" + noticeViewCount
				+ ", noticeLikeCount=" + noticeLikeCount + ", noticeCreatedDate=" + noticeCreatedDate
				+ ", noticeUpdatedDate=" + noticeUpdatedDate + ", noticeDeleteState=" + noticeDeleteState + "]";
	}
	
	
}
