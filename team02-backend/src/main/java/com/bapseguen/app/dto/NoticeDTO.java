package com.bapseguen.app.dto;

public class NoticeDTO {

	private int postNumber;
	private String noticeContent;
	
	public int getPostNumber() {
		return postNumber;
	}
	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	@Override
	public String toString() {
		return "NoticeDTO [postNumber=" + postNumber + ", noticeContent=" + noticeContent + "]";
	}

}
