package com.bapseguen.app.dto;

public class FaqDTO {

	private int postNumber;
	private String faqContent;
	
	//+추가	
	private String postType; // ‘NOTICE’,’FREE’,’PROMOTION’,’RECIPE’,’INQUIRY’,’FAQ’private int adminMemberNumber;
	private String adminTreeGrade;
	private String postTitle;
	private String postCreatedDate;
	private String postUpdatedDate;
	private int postViewCount;
	private int postLikeCount;
	
	
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
