package com.bapseguen.app.dto;

public class FaqDTO {
//	CREATE TABLE TBL_FAQ (
//			  FAQ_NUMBER     NUMBER PRIMARY KEY,
//			  ADMIN_NUMBER   NUMBER NOT NULL
//			                   CONSTRAINT FK_FAQ_ADMIN
//			                   REFERENCES TBL_ADMIN(MEMBER_NUMBER),
//			  FAQ_QUESTION       VARCHAR2(200) NOT NULL,
//			  FAQ_ANSWER         CLOB NOT NULL,
//			  FAQ_VIEW_COUNT     NUMBER DEFAULT 0 NOT NULL,
//			  FAQ_LIKE_COUNT     NUMBER DEFAULT 0 NOT NULL,
//			  FAQ_CREATED_DATE   DATE DEFAULT SYSDATE NOT NULL,
//			  FAQ_UPDATED_DATE   DATE,
//			  FAQ_DELETE_STATE   CHAR(1) DEFAULT 'N'
//			                   CONSTRAINT CK_FAQ_DEL CHECK (DELETE_STATE IN ('Y','N'))
//			);
	private int faqNumber;
	private int adminNumber;
	private String faqQuestion;
	private String faqAnswer;
	private int faqViewCount;
	private int faqLikeCount;
	private String faqCreatedDate;
	private String faqUpdatedDate;
	private boolean faqDeleteState;
	public int getFaqNumber() {
		return faqNumber;
	}
	public void setFaqNumber(int faqNumber) {
		this.faqNumber = faqNumber;
	}
	public int getAdminNumber() {
		return adminNumber;
	}
	public void setAdminNumber(int adminNumber) {
		this.adminNumber = adminNumber;
	}
	public String getFaqQuestion() {
		return faqQuestion;
	}
	public void setFaqQuestion(String faqQuestion) {
		this.faqQuestion = faqQuestion;
	}
	public String getFaqAnswer() {
		return faqAnswer;
	}
	public void setFaqAnswer(String faqAnswer) {
		this.faqAnswer = faqAnswer;
	}
	public int getFaqViewCount() {
		return faqViewCount;
	}
	public void setFaqViewCount(int faqViewCount) {
		this.faqViewCount = faqViewCount;
	}
	public int getFaqLikeCount() {
		return faqLikeCount;
	}
	public void setFaqLikeCount(int faqLikeCount) {
		this.faqLikeCount = faqLikeCount;
	}
	public String getFaqCreatedDate() {
		return faqCreatedDate;
	}
	public void setFaqCreatedDate(String faqCreatedDate) {
		this.faqCreatedDate = faqCreatedDate;
	}
	public String getFaqUpdatedDate() {
		return faqUpdatedDate;
	}
	public void setFaqUpdatedDate(String faqUpdatedDate) {
		this.faqUpdatedDate = faqUpdatedDate;
	}
	public boolean isFaqDeleteState() {
		return faqDeleteState;
	}
	public void setFaqDeleteState(boolean faqDeleteState) {
		this.faqDeleteState = faqDeleteState;
	}
	@Override
	public String toString() {
		return "FaqDTO [faqNumber=" + faqNumber + ", adminNumber=" + adminNumber + ", faqQuestion=" + faqQuestion
				+ ", faqAnswer=" + faqAnswer + ", faqViewCount=" + faqViewCount + ", faqLikeCount=" + faqLikeCount
				+ ", faqCreatedDate=" + faqCreatedDate + ", faqUpdatedDate=" + faqUpdatedDate + ", faqDeleteState="
				+ faqDeleteState + "]";
	}
	
	
}
