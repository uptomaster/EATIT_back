package com.bapseguen.app.dto;

public class FaqDTO {

	private int postNumber;
	private String faqContent;
	
	public int getPostNumber() {
		return postNumber;
	}
	
	// getter & setter
	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}
	public String getFaqContent() {
		return faqContent;
	}
	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}
	
	@Override
	public String toString() {
		return "FaqDTO [postNumber=" + postNumber + ", faqContent=" + faqContent + "]";
	}
	
	
	
	
}
