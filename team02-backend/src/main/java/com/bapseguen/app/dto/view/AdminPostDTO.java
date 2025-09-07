package com.bapseguen.app.dto.view;

import java.util.List;
import com.bapseguen.app.dto.AdminImageDTO;

public class AdminPostDTO {
	private int postNumber; // 게시글 번호
	private String postTitle; // 제목
	private int postLikeCount; // 좋아요 수
	private int postViewCount; // 조회수
	private int postReportCount; // 신고 수
	private String postDeleteState; // 삭제 여부 (Y/N)
	private String postCreatedDate; // 작성일 (문자열 처리)
	private String postUpdatedDate; // 수정일 (문자열 처리)
	private String postType; // 게시글 타입 (NOTICE / FAQ / INQUIRY)

	private int adminNumber; // 작성자(관리자 번호)
	private String adminId; // 관리자 아이디
	private String adminTreeGrade; // 관리자 등급 (관리자)

	// 게시판 별 추가 내용
	private String noticeContent; // 공지/이벤트 내용
	private String faqContent; // FAQ 내용

	// 문의(Inquiry) 관련
	private String inquiryContent;
	private String inquiryStatus; // YET / IN_PROGRESS / COMPLETE

	// 첨부 이미지 (1:N 관계)
	private List<AdminImageDTO> images;

	// 이름 추가
	private String memberName; // 회원만 이름 존재함.(없어도 되면 삭제할거)

	// 회원 번호(문의)
	private Integer memberNumber; // 회원 번호 (관리자면 null값이기 때문에 Integer타입으로 바꿈.)
	private String memberId; // 회원 아이디
	
	
	// AdminPostDTO.java
	private String answerContent;   // 관리자 답변 내용
	private String answerDate;      // 답변 작성일

	
	// getter & setter
	public String getAnswerContent() {
	    return answerContent;
	}
	public void setAnswerContent(String answerContent) {
	    this.answerContent = answerContent;
	}

	public String getAnswerDate() {
	    return answerDate;
	}
	public void setAnswerDate(String answerDate) {
	    this.answerDate = answerDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public AdminPostDTO() {
	}

	// getter & setter
	public int getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}

	public Integer getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(Integer memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public int getPostLikeCount() {
		return postLikeCount;
	}

	public void setPostLikeCount(int postLikeCount) {
		this.postLikeCount = postLikeCount;
	}

	public int getPostViewCount() {
		return postViewCount;
	}

	public void setPostViewCount(int postViewCount) {
		this.postViewCount = postViewCount;
	}

	public int getPostReportCount() {
		return postReportCount;
	}

	public void setPostReportCount(int postReportCount) {
		this.postReportCount = postReportCount;
	}

	public String getPostDeleteState() {
		return postDeleteState;
	}

	public void setPostDeleteState(String postDeleteState) {
		this.postDeleteState = postDeleteState;
	}

	public String getPostCreatedDate() {
		return postCreatedDate;
	}

	public void setPostCreatedDate(String postCreatedDate) {
		this.postCreatedDate = postCreatedDate;
	}

	public String getPostUpdatedDate() {
		return postUpdatedDate;
	}

	public void setPostUpdatedDate(String postUpdatedDate) {
		this.postUpdatedDate = postUpdatedDate;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public int getAdminNumber() {
		return adminNumber;
	}

	public void setAdminNumber(int adminNumber) {
		this.adminNumber = adminNumber;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminTreeGrade() {
		return adminTreeGrade;
	}

	public void setAdminTreeGrade(String adminTreeGrade) {
		this.adminTreeGrade = adminTreeGrade;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getFaqContent() {
		return faqContent;
	}

	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}

	public String getInquiryContent() {
		return inquiryContent;
	}

	public void setInquiryContent(String inquiryContent) {
		this.inquiryContent = inquiryContent;
	}

	public String getInquiryStatus() {
		return inquiryStatus;
	}

	public void setInquiryStatus(String inquiryStatus) {
		this.inquiryStatus = inquiryStatus;
	}

	public List<AdminImageDTO> getImages() {
		return images;
	}

	public void setImages(List<AdminImageDTO> images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "AdminPostDTO [postNumber=" + postNumber + ", postTitle=" + postTitle + ", postLikeCount="
				+ postLikeCount + ", postViewCount=" + postViewCount + ", postReportCount=" + postReportCount
				+ ", postDeleteState=" + postDeleteState + ", postCreatedDate=" + postCreatedDate + ", postUpdatedDate="
				+ postUpdatedDate + ", postType=" + postType + ", adminNumber=" + adminNumber + ", adminId=" + adminId
				+ ", adminTreeGrade=" + adminTreeGrade + ", noticeContent=" + noticeContent + ", faqContent="
				+ faqContent + ", inquiryContent=" + inquiryContent + ", inquiryStatus=" + inquiryStatus + ", images="
				+ images + ", memberName=" + memberName + ", memberNumber=" + memberNumber + ", memberId=" + memberId
				+ ", answerContent=" + answerContent + ", answerDate=" + answerDate + "]";
	}


}
