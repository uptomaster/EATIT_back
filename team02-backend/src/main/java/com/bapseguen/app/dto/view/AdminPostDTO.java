package com.bapseguen.app.dto.view;

import java.util.List;
import com.bapseguen.app.dto.AdminImageDTO;

public class AdminPostDTO {
	private int postNumber;           // 게시글 번호
	private String postTitle;         // 제목
	private int postLikeCount;        // 좋아요 수
	private int postViewCount;        // 조회수
	private int postReportCount;      // 신고 수
	private String postDeleteState;   // 삭제 여부 (Y/N)
	private String postCreatedDate;   // 작성일
	private String postUpdatedDate;   // 수정일
	private String postType;          // 게시글 타입 (NOTICE / FAQ / INQUIRY)

	// 관리자 정보
	private Integer adminNumber;
	private String adminId;
	private String adminTreeGrade;

	// 문의 작성자 정보
	private Integer memberNumber;
	private String memberId;
	private String memberName;
	private String inquiryContent;
	private String inquiryStatus;     // YET / IN_PROGRESS / COMPLETE

	// 문의 답변 (관리자 전용)
	private String answerContent;     // 답변 내용
	private String answerDate;        // 답변 작성일

	// 게시판 별 추가 내용
	private String noticeContent;
	private String faqContent;

	// 첨부 이미지 (1:N 관계)
	private List<AdminImageDTO> images;

	// (예전 댓글 DTO — 지금 구조에서는 필요 없지만 일단 유지)
	private List<InquiryCommentDTO> replies;

	public AdminPostDTO() {}

	// ===== Getter & Setter =====
	public int getPostNumber() {
		return postNumber;
	}
	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
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

	public Integer getAdminNumber() {
		return adminNumber;
	}
	public void setAdminNumber(Integer adminNumber) {
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

	public Integer getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(Integer memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
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

	public List<AdminImageDTO> getImages() {
		return images;
	}
	public void setImages(List<AdminImageDTO> images) {
		this.images = images;
	}

	public List<InquiryCommentDTO> getReplies() {
		return replies;
	}
	public void setReplies(List<InquiryCommentDTO> replies) {
		this.replies = replies;
	}

	@Override
	public String toString() {
		return "AdminPostDTO [postNumber=" + postNumber + ", postTitle=" + postTitle
				+ ", postLikeCount=" + postLikeCount + ", postViewCount=" + postViewCount
				+ ", postReportCount=" + postReportCount + ", postDeleteState=" + postDeleteState
				+ ", postCreatedDate=" + postCreatedDate + ", postUpdatedDate=" + postUpdatedDate
				+ ", postType=" + postType + ", adminNumber=" + adminNumber + ", adminId=" + adminId
				+ ", adminTreeGrade=" + adminTreeGrade + ", memberNumber=" + memberNumber
				+ ", memberId=" + memberId + ", memberName=" + memberName
				+ ", inquiryContent=" + inquiryContent + ", inquiryStatus=" + inquiryStatus
				+ ", answerContent=" + answerContent + ", answerDate=" + answerDate
				+ ", noticeContent=" + noticeContent + ", faqContent=" + faqContent
				+ ", images=" + images + ", replies=" + replies + "]";
	}
}
