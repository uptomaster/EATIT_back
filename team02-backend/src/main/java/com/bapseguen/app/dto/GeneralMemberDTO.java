package com.bapseguen.app.dto;

public class GeneralMemberDTO {
//	CREATE TABLE TBL_GENERAL_MEMBER (
//			  GENERAL_MEMBER_NUMBER  NUMBER PRIMARY KEY
//			                   CONSTRAINT FK_GENERAL_MEMBER
//			                   REFERENCES TBL_MEMBER(MEMBER_NUMBER) ON DELETE CASCADE,
//			  GENERAL_REPORT_COUNT   NUMBER,
//			  GENERAL_WARNING_COUNT  NUMBER,
//			  GENERAL_BLACK_STATE    CHAR(1) DEFAULT 'N' 
//			                   CONSTRAINT CK_GMEM_BLACK CHECK (BLACK_STATE IN ('Y','N')),
//			);
	private int generalMemberNumber;
	private int generalReportCount;
	private int generalWarningCount;
	private boolean generalBlackState;
	public int getGeneralMemberNumber() {
		return generalMemberNumber;
	}
	public void setGeneralMemberNumber(int generalMemberNumber) {
		this.generalMemberNumber = generalMemberNumber;
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
	@Override
	public String toString() {
		return "GeneralMemberDTO [generalMemberNumber=" + generalMemberNumber + ", generalReportCount="
				+ generalReportCount + ", generalWarningCount=" + generalWarningCount + ", generalBlackState="
				+ generalBlackState + "]";
	}

	
	
	
}
