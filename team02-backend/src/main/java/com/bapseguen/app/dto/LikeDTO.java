package com.bapseguen.app.dto;

public class LikeDTO {
//	CREATE TABLE TBL_POST_LIKE (
//			  POST_NUMBER    NUMBER
//			                    CONSTRAINT FK_PLIKE_POST
//			                    REFERENCES TBL_POST(POST_NUMBER) ON DELETE CASCADE,
//			  MEMBER_NUMBER  NUMBER
//			                    CONSTRAINT FK_PLIKE_MEMBER
//			                    REFERENCES TBL_MEMBER(MEMBER_NUMBER) ON DELETE CASCADE,
//			  POST_LIKE_CREATED_DATE   DATE DEFAULT SYSDATE NOT NULL,
//			  					CONSTRAINT PK_POST_LIKE PRIMARY KEY (POST_NUMBER, MEMBER_NUMBER)
//			);
	private int postNumber;
	private int MemberNumber;
	private String postLikeCreatedDate;
	public int getPostNumber() {
		return postNumber;
	}
	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}
	public int getMemberNumber() {
		return MemberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		MemberNumber = memberNumber;
	}
	public String getPostLikeCreatedDate() {
		return postLikeCreatedDate;
	}
	public void setPostLikeCreatedDate(String postLikeCreatedDate) {
		this.postLikeCreatedDate = postLikeCreatedDate;
	}
	@Override
	public String toString() {
		return "PostLikeDTO [postNumber=" + postNumber + ", MemberNumber=" + MemberNumber + ", postLikeCreatedDate="
				+ postLikeCreatedDate + "]";
	}
	
	
}
