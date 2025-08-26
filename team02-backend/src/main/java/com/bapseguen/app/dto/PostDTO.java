package com.bapseguen.app.dto;

public class PostDTO {

		private int postNumber;
		private int memberNumber;
		private int memberId;
		private String postTitle;

		private int postLikeCount;
		private int postViewCount;
		private int postReportCount;
		private boolean deleteState;
		private String postCreatedDate;
		private String postUpdatedDate;
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
		
		public int getMemberId() {
			return memberId;
		}
		public void setMemberId(int memberId) {
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
