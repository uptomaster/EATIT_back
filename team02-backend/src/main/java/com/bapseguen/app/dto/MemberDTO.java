package com.bapseguen.app.dto;

public class MemberDTO {
//	CREATE TABLE TBL_MEMBER (
//			  MEMBER_NUMBER        NUMBER CONSTRAINT pk_member PRIMARY key,
//			  MEMBER_TYPE          VARCHAR2(10) DEFAULT 'GENERAL' NOT NULL
//			                         CONSTRAINT CK_MEMBER_TYPE CHECK (MEMBER_TYPE IN ('GENERAL','SELLER','ADMIN')),
//			  MEMBER_ID            VARCHAR2(20) NOT NULL
//			                         CONSTRAINT UQ_MEMBER_ID UNIQUE,
//			  MEMBER_PASSWORD      VARCHAR2(60) NOT NULL,
//			  MEMBER_NAME          VARCHAR2(90) NOT NULL,
//			  MEMBER_BIRTHDATE     DATE NOT NULL,
//			  MEMBER_PHONE_NUMBER  VARCHAR2(11)
//			                         CONSTRAINT UQ_MEMBER_PHONE UNIQUE,
//			  MEMBER_JOIN_DATE     DATE NOT NULL,
//			  MEMBER_UPDATED_DATE  DATE,
//			  MEMBER_PAYMENT_AMOUNT		NUMBER,
//			  MEMBER_TREE_GRADE			VARCHAR(20) CHECK (MEMBER_TREE_GRADE IN ('씨앗','새싹','잎새','가지','나무','관리자')),
//			);
	private int memberNumber;
	private String memberType;
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String memberBirthdate;
	private String memberPhoneNumber;
	private String memberJoinDate;
	private String memberUpdatedDate;
	private int memberPaymentAmount;
	private int memberPoint;
	private String memberTreeGrade;
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberBirthdate() {
		return memberBirthdate;
	}
	public void setMemberBirthdate(String memberBirthdate) {
		this.memberBirthdate = memberBirthdate;
	}
	public String getMemberPhoneNumber() {
		return memberPhoneNumber;
	}
	public void setMemberPhoneNumber(String memberPhoneNumber) {
		this.memberPhoneNumber = memberPhoneNumber;
	}
	public String getMemberJoinDate() {
		return memberJoinDate;
	}
	public void setMemberJoinDate(String memberJoinDate) {
		this.memberJoinDate = memberJoinDate;
	}
	public String getMemberUpdatedDate() {
		return memberUpdatedDate;
	}
	public void setMemberUpdatedDate(String memberUpdatedDate) {
		this.memberUpdatedDate = memberUpdatedDate;
	}
	public int getMemberPaymentAmount() {
		return memberPaymentAmount;
	}
	public void setMemberPaymentAmount(int memberPaymentAmount) {
		this.memberPaymentAmount = memberPaymentAmount;
	}
	public int getMemberPoint() {
		return memberPoint;
	}
	public void setMemberPoint(int memberPoint) {
		this.memberPoint = memberPoint;
	}
	public String getMemberTreeGrade() {
		return memberTreeGrade;
	}
	public void setMemberTreeGrade(String memberTreeGrade) {
		this.memberTreeGrade = memberTreeGrade;
	}
	@Override
	public String toString() {
		return "MemberDTO [memberNumber=" + memberNumber + ", memberType=" + memberType + ", memberId=" + memberId
				+ ", memberPassword=" + memberPassword + ", memberName=" + memberName + ", memberBirthdate="
				+ memberBirthdate + ", memberPhoneNumber=" + memberPhoneNumber + ", memberJoinDate=" + memberJoinDate
				+ ", memberUpdatedDate=" + memberUpdatedDate + ", memberPaymentAmount=" + memberPaymentAmount
				+ ", memberPoint=" + memberPoint + ", memberTreeGrade=" + memberTreeGrade + "]";
	}

	
	
	

}
