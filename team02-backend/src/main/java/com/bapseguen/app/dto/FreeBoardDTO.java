package com.bapseguen.app.dto;

public class FreeBoardDTO {

	private int postNumber;
	private String freeContent;
	
	public int getPostNumber() {
		return postNumber;
	}
	
	// getter & setter
	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}
	public String getFreeContent() {
		return freeContent;
	}
	public void setFreeContent(String freeContent) {
		this.freeContent = freeContent;
	}
	
	@Override
	public String toString() {
		return "FreeBoardDTO [postNumber=" + postNumber + ", freeContent=" + freeContent + "]";
	}
	
	
}
