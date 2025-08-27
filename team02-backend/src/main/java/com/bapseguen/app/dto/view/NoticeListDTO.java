package com.bapseguen.app.dto.view;

public class NoticeListDTO {
    private int noticeNumber;
    private String noticeTitle;
    private String noticeDate;
    private int noticeViews;
    private int noticeLikes;

    // getter/setter
    public int getNoticeNumber() { return noticeNumber; }
    public void setNoticeNumber(int noticeNumber) { this.noticeNumber = noticeNumber; }

    public String getNoticeTitle() { return noticeTitle; }
    public void setNoticeTitle(String noticeTitle) { this.noticeTitle = noticeTitle; }

    public String getNoticeDate() { return noticeDate; }
    public void setNoticeDate(String noticeDate) { this.noticeDate = noticeDate; }

    public int getNoticeViews() { return noticeViews; }
    public void setNoticeViews(int noticeViews) { this.noticeViews = noticeViews; }

    public int getNoticeLikes() { return noticeLikes; }
    public void setNoticeLikes(int noticeLikes) { this.noticeLikes = noticeLikes; }
    
	@Override
	public String toString() {
		return "NoticeListDTO [noticeNumber=" + noticeNumber + ", noticeTitle=" + noticeTitle + ", noticeDate="
				+ noticeDate + ", noticeViews=" + noticeViews + ", noticeLikes=" + noticeLikes + "]";
	}

    
}
