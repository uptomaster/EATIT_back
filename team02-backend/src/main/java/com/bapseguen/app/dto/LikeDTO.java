package com.bapseguen.app.dto;

public class LikeDTO {

	private int postNumber;
	private int memberNumber;
	private int likeNumber;
	
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
	public int getLikeNumber() {
		return likeNumber;
	}
	public void setLikeNumber(int likeNumber) {
		this.likeNumber = likeNumber;
	}
	
	@Override
	public String toString() {
		return "LikeDTO [postNumber=" + postNumber + ", memberNumber=" + memberNumber + ", likeNumber=" + likeNumber
				+ ", getPostNumber()=" + getPostNumber() + ", getMemberNumber()=" + getMemberNumber()
				+ ", getLikeNumber()=" + getLikeNumber() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}	
}
