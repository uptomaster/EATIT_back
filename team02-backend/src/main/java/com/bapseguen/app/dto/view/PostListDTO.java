package com.bapseguen.app.dto.view;

public class PostListDTO {

	//게시글정보
	private String postType; // ‘NOTICE’,’FREE’,’PROMOTION’,’RECIPE’,’INQUIRY’,’FAQ’
	private String postTitle;
	private String postCreatedDate;
	private String postUpdatedDate;
	private int postViewCount;
	private int postLikeCount;
	private String freeContent;
	private String promoContent;
	private String recipeContent;
	//사용자
	private int memberNumber;
	private String memberId;
	private String treeGrade; //CHECK (MEMBER_TREE_GRADE IN ('씨앗','새싹','잎새','가지','나무'))
	
	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	public String getTreeGrade() {
		return treeGrade;
	}
	public void setTreeGrade(String treeGrade) {
		this.treeGrade = treeGrade;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
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
	public int getPostViewCount() {
		return postViewCount;
	}
	public void setPostViewCount(int postViewCount) {
		this.postViewCount = postViewCount;
	}
	public int getPostLikeCount() {
		return postLikeCount;
	}
	public void setPostLikeCount(int postLikeCount) {
		this.postLikeCount = postLikeCount;
	}
	public String getFreeContent() {
		return freeContent;
	}
	public void setFreeContent(String freeContent) {
		this.freeContent = freeContent;
	}
	public String getPromoContent() {
		return promoContent;
	}
	public void setPromoContent(String promoContent) {
		this.promoContent = promoContent;
	}
	public String getRecipeContent() {
		return recipeContent;
	}
	public void setRecipeContent(String recipeContent) {
		this.recipeContent = recipeContent;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	@Override
	public String toString() {
		return "PostListDTO [postType=" + postType + ", treeGrade=" + treeGrade + ", postTitle=" + postTitle
				+ ", postCreatedDate=" + postCreatedDate + ", postUpdatedDate=" + postUpdatedDate + ", postViewCount="
				+ postViewCount + ", postLikeCount=" + postLikeCount + ", freeContent=" + freeContent
				+ ", promoContent=" + promoContent + ", recipeContent=" + recipeContent + ", memberNumber="
				+ memberNumber + ", memberId=" + memberId + "]";
	}	
	
}
