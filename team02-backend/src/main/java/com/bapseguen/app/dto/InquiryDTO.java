package com.bapseguen.app.dto;

public class InquiryDTO {

	private int postNumber;
	private String inquiryContent;
	private String inquiryStatus;
	
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
	
	@Override
	public String toString() {
		return "InquiryDTO [postNumber=" + postNumber + ", inquiryContent=" + inquiryContent + ", inquiryStatus="
				+ inquiryStatus + "]";
	}
	
}