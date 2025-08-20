package com.bapseguen.app.dto.view;

public class PostDetailDTO {
	 
	private String postType; // ‘NOTICE’,’FREE’,’PROMOTION’,’RECIPE’,’INQUIRY’,’FAQ’
	private String treeGrade; //CHECK (MEMBER_TREE_GRADE IN ('씨앗','새싹','잎새','가지','나무'))
	private int memberNumber;
	private String memberId;
	private String postTitle;
	private String postCreatedDate;
	private String postUpdatedDate;
	private int postViewCount;
	private int postLikeCount;
	private String freeContent;
	private String promoContent;
	private String recipeContent;
	private int postReportNumber;
	private String postReportReason; //CHECK (POST_REPORT_REASON IN ('ADV','BADWORDS','PORN','PERSONAL','ETC'))
	private int postReportCount;
	private int commentNumber;
	private String commentContent;
	private boolean deleteState;
	private List<PostImageDTO> files;
	
	
}
