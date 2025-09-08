package com.bapseguen.app.dto;

import java.util.Date;

public class PostDTO {

		private int postNumber;
		private int memberNumber;
		private String memberId;
		private String postTitle;

		private int postLikeCount;
		private int postViewCount;
		private int postReportCount;
		private boolean deleteState;
		private Date postCreatedDate;
		private Date postUpdatedDate;
		private String postType; // ‘NOTICE’,’FREE’,’PROMOTION’,’RECIPE’,’INQUIRY’,’FAQ’
		
		
		// getter & setter
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
		public boolean isDeleteState() {
			return deleteState;
		}
		public void setDeleteState(boolean deleteState) {
			this.deleteState = deleteState;
		}
		
		public Date getPostCreatedDate() {
			return postCreatedDate;
		}
		public void setPostCreatedDate(Date postCreatedDate) {
			this.postCreatedDate = postCreatedDate;
		}
		public Date getPostUpdatedDate() {
			return postUpdatedDate;
		}
		public void setPostUpdatedDate(Date postUpdatedDate) {
			this.postUpdatedDate = postUpdatedDate;
		}
		public String getPostType() {
			return postType;
		}
		public void setPostType(String postType) {
			this.postType = postType;
		}
		
		
		
		public String getMemberId() {
			return memberId;
		}
		public void setMemberId(String memberId) {
			this.memberId = memberId;
		}
		@Override
		public String toString() {
			return "PostDTO [postNumber=" + postNumber + ", memberNumber=" + memberNumber + ", memberId=" + memberId
					+ ", postTitle=" + postTitle + ", postLikeCount=" + postLikeCount + ", postViewCount="
					+ postViewCount + ", postReportCount=" + postReportCount + ", deleteState=" + deleteState
					+ ", postCreatedDate=" + postCreatedDate + ", postUpdatedDate=" + postUpdatedDate + ", postType="
					+ postType + "]";
		}
		
		
		
	
	
		
}
