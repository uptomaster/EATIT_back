package com.bapseguen.app.dto.view;

public class PostListDTO {

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
}
