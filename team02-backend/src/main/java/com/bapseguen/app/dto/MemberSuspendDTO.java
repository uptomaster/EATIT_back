package com.bapseguen.app.dto;

public class MemberSuspendDTO {
//	CREATE TABLE TBL_MEMBER_SUSPEND (
//			  MEMBER_NUMBER  NUMBER
//			                   CONSTRAINT FK_MSUSP_MEMBER
//			                   REFERENCES TBL_MEMBER(MEMBER_NUMBER) ON DELETE CASCADE,
//			  SUSPEND_START_DATE     DATE,
//			  SUSPEND_END_DATE       DATE,
//			  SUSPEND_REPORT_COUNT   NUMBER NOT NULL,
//			  CONSTRAINT PK_MEMBER_SUSPEND PRIMARY KEY (MEMBER_NUMBER, SUSPEND_START_DATE)
//			);
	
	private int memberNumber;
	private String suspendStartDate;
	private String suspendEndDate;
	private int suspendReportCount;
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getSuspendStartDate() {
		return suspendStartDate;
	}
	public void setSuspendStartDate(String suspendStartDate) {
		this.suspendStartDate = suspendStartDate;
	}
	public String getSuspendEndDate() {
		return suspendEndDate;
	}
	public void setSuspendEndDate(String suspendEndDate) {
		this.suspendEndDate = suspendEndDate;
	}
	public int getSuspendReportCount() {
		return suspendReportCount;
	}
	public void setSuspendReportCount(int suspendReportCount) {
		this.suspendReportCount = suspendReportCount;
	}
	@Override
	public String toString() {
		return "MemberSuspendDTO [memberNumber=" + memberNumber + ", suspendStartDate=" + suspendStartDate
				+ ", suspendEndDate=" + suspendEndDate + ", suspendReportCount=" + suspendReportCount + "]";
	}
	
	
}
