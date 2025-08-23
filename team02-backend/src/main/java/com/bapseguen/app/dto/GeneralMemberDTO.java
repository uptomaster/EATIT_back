package com.bapseguen.app.dto;

public class GeneralMemberDTO {

	private int memberNumber;
	private int generalReportCount;
	private int generalWarningCount;
	private boolean generalBlackState;
	private String generalName;
	private String generalBirthdate;
	private String generalPhoneNumber;
	private String generalJoinDate;
	private String generalUpdatedDate;
	private String generalTreeGrade;
	private String generalPaymentAmount;
	
	// getter & setter
	public int getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}

	public int getGeneralReportCount() {
		return generalReportCount;
	}

	public void setGeneralReportCount(int generalReportCount) {
		this.generalReportCount = generalReportCount;
	}

	public int getGeneralWarningCount() {
		return generalWarningCount;
	}

	public void setGeneralWarningCount(int generalWarningCount) {
		this.generalWarningCount = generalWarningCount;
	}

	public boolean isGeneralBlackState() {
		return generalBlackState;
	}

	public void setGeneralBlackState(boolean generalBlackState) {
		this.generalBlackState = generalBlackState;
	}

	public String getGeneralName() {
		return generalName;
	}

	public void setGeneralName(String generalName) {
		this.generalName = generalName;
	}

	public String getGeneralBirthdate() {
		return generalBirthdate;
	}

	public void setGeneralBirthdate(String generalBirthdate) {
		this.generalBirthdate = generalBirthdate;
	}

	public String getGeneralPhoneNumber() {
		return generalPhoneNumber;
	}

	public void setGeneralPhoneNumber(String generalPhoneNumber) {
		this.generalPhoneNumber = generalPhoneNumber;
	}

	public String getGeneralJoinDate() {
		return generalJoinDate;
	}

	public void setGeneralJoinDate(String generalJoinDate) {
		this.generalJoinDate = generalJoinDate;
	}

	public String getGeneralUpdatedDate() {
		return generalUpdatedDate;
	}

	public void setGeneralUpdatedDate(String generalUpdatedDate) {
		this.generalUpdatedDate = generalUpdatedDate;
	}

	public String getGeneralTreeGrade() {
		return generalTreeGrade;
	}

	public void setGeneralTreeGrade(String generalTreeGrade) {
		this.generalTreeGrade = generalTreeGrade;
	}

	public String getGeneralPaymentAmount() {
		return generalPaymentAmount;
	}

	public void setGeneralPaymentAmount(String generalPaymentAmount) {
		this.generalPaymentAmount = generalPaymentAmount;
	}

	@Override
	public String toString() {
		return "GeneralMemberDTO [memberNumber=" + memberNumber + ", generalReportCount=" + generalReportCount
				+ ", generalWarningCount=" + generalWarningCount + ", generalBlackState=" + generalBlackState
				+ ", generalName=" + generalName + ", generalBirthdate=" + generalBirthdate + ", generalPhoneNumber="
				+ generalPhoneNumber + ", generalJoinDate=" + generalJoinDate + ", generalUpdatedDate="
				+ generalUpdatedDate + ", generalTreeGrade=" + generalTreeGrade + ", generalPaymentAmount="
				+ generalPaymentAmount + "]";
	}
	
	
}
