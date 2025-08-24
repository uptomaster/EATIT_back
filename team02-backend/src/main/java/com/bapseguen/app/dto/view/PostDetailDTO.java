package com.bapseguen.app.dto.view;

import java.util.List;
import com.bapseguen.app.dto.PostImageDTO;

public class PostDetailDTO {
   
   //게시글정보
   private String postType; // ‘NOTICE’,’FREE’,’PROMOTION’,’RECIPE’,’INQUIRY’,’FAQ’
   private String postTitle;
   private String postCreatedDate;
   private String postUpdatedDate;
   private int postViewCount;
   private int postLikeCount;
   private boolean deleteState;

   //게시글 신고
   private int postReportNumber;
   private String postReportReason; //CHECK (POST_REPORT_REASON IN ('ADV','BADWORDS','PORN','PERSONAL','ETC'))
   private int postReportCount;
   private int commentNumber;
   private String commentContent;
   private int commentCount;
   
   //게시글 이미지
   private List<PostImageDTO> files;
   
   //자유게시판 글 작성
   private String freeContent;
   //홍보게시판 글 작성
   private String promoContent;
   //레시피게시판 글 작성
   private String recipeContent;
   
   //사용자
   private String treeGrade; //CHECK (MEMBER_TREE_GRADE IN ('씨앗','새싹','잎새','가지','나무')
   private int memberNumber;
   private String memberId;
   
   public String getPostType() {
      return postType;
   }
   public void setPostType(String postType) {
      this.postType = postType;
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
   public boolean isDeleteState() {
      return deleteState;
   }
   public void setDeleteState(boolean deleteState) {
      this.deleteState = deleteState;
   }
   public int getPostReportNumber() {
      return postReportNumber;
   }
   public void setPostReportNumber(int postReportNumber) {
      this.postReportNumber = postReportNumber;
   }
   public String getPostReportReason() {
      return postReportReason;
   }
   public void setPostReportReason(String postReportReason) {
      this.postReportReason = postReportReason;
   }
   public int getPostReportCount() {
      return postReportCount;
   }
   public void setPostReportCount(int postReportCount) {
      this.postReportCount = postReportCount;
   }
   public int getCommentNumber() {
      return commentNumber;
   }
   public void setCommentNumber(int commentNumber) {
      this.commentNumber = commentNumber;
   }
   public String getCommentContent() {
      return commentContent;
   }
   public void setCommentContent(String commentContent) {
      this.commentContent = commentContent;
   }
   public List<PostImageDTO> getFiles() {
      return files;
   }
   public void setFiles(List<PostImageDTO> files) {
      this.files = files;
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
   public String getTreeGrade() {
      return treeGrade;
   }
   public void setTreeGrade(String treeGrade) {
      this.treeGrade = treeGrade;
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
      return "PostDetailDTO [postType=" + postType + ", postTitle=" + postTitle + ", postCreatedDate="
            + postCreatedDate + ", postUpdatedDate=" + postUpdatedDate + ", postViewCount=" + postViewCount
            + ", postLikeCount=" + postLikeCount + ", deleteState=" + deleteState + ", postReportNumber="
            + postReportNumber + ", postReportReason=" + postReportReason + ", postReportCount=" + postReportCount
            + ", commentNumber=" + commentNumber + ", commentContent=" + commentContent + ", files=" + files
            + ", freeContent=" + freeContent + ", promoContent=" + promoContent + ", recipeContent=" + recipeContent
            + ", treeGrade=" + treeGrade + ", memberNumber=" + memberNumber + ", memberId=" + memberId + "]";
   }
   public int getCommentCount() {
      return commentCount;
   }
   public void setCommentCount(int commentCount) {
      this.commentCount = commentCount;
   }
   

}
