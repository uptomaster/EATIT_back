package com.bapseguen.app.dto;

public class PostImageDTO {

	private int postImageNumber;
	private String postImageSystemName;
	private String postImageOriginalName;
	private int postNumber;
	
	public int getPostImageNumber() {
		return postImageNumber;
	}
	
	// getter & setter
	public void setPostImageNumber(int postImageNumber) {
		this.postImageNumber = postImageNumber;
	}
	public String getPostImageSystemName() {
		return postImageSystemName;
	}
	public void setPostImageSystemName(String postImageSystemName) {
		this.postImageSystemName = postImageSystemName;
	}
	public String getPostImageOriginalName() {
		return postImageOriginalName;
	}
	public void setPostImageOriginalName(String postImageOriginalName) {
		this.postImageOriginalName = postImageOriginalName;
	}
	public int getPostNumber() {
		return postNumber;
	}
	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}
	
	@Override
	public String toString() {
		return "PostImageDTO [postImageNumber=" + postImageNumber + ", postImageSystemName=" + postImageSystemName
				+ ", postImageOriginalName=" + postImageOriginalName + ", postNumber=" + postNumber + "]";
	}
	
	
	
}
