package com.bapseguen.app.dto;

public class SellerMemberDTO {
//	CREATE TABLE TBL_SELLER_MEMBER (
//			  SELLER_MEMBER_NUMBER  NUMBER PRIMARY KEY
//			                   CONSTRAINT FK_SELLER_MEMBER
//			                   REFERENCES TBL_MEMBER(MEMBER_NUMBER) ON DELETE CASCADE,
//			  SELLER_REPORT_COUNT   NUMBER,
//			  SELLER_WARNING_COUNT  NUMBER,
//			  SELLER_BLACK_STATE    CHAR(1) DEFAULT 'N'
//			                   CONSTRAINT CK_SMEM_BLACK CHECK (BLACK_STATE IN ('Y','N')),
//			);

	private int sellerMemberNumber;
	private int sellerReportCount;
	private int sellerWarningCount;
	private boolean sellerBlackState;
	public int getSellerMemberNumber() {
		return sellerMemberNumber;
	}
	public void setSellerMemberNumber(int sellerMemberNumber) {
		this.sellerMemberNumber = sellerMemberNumber;
	}
	public int getSellerReportCount() {
		return sellerReportCount;
	}
	public void setSellerReportCount(int sellerReportCount) {
		this.sellerReportCount = sellerReportCount;
	}
	public int getSellerWarningCount() {
		return sellerWarningCount;
	}
	public void setSellerWarningCount(int sellerWarningCount) {
		this.sellerWarningCount = sellerWarningCount;
	}
	public boolean isSellerBlackState() {
		return sellerBlackState;
	}
	public void setSellerBlackState(boolean sellerBlackState) {
		this.sellerBlackState = sellerBlackState;
	}
	@Override
	public String toString() {
		return "SellerMemberDTO [sellerMemberNumber=" + sellerMemberNumber + ", sellerReportCount=" + sellerReportCount
				+ ", sellerWarningCount=" + sellerWarningCount + ", sellerBlackState=" + sellerBlackState + "]";
	}

	
	
}
