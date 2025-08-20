package com.bapseguen.app.dto.view;

public class ReviewWriteDTO {
	//리뷰내용
	   private int reviewRating;
	   private String reviewContent;
	   private String reviewCreateDate;
	//구매내역
	   private String ordersDate;
	   private int ordersTotalAmount;
	// 구매품목
	   private int orderItemNumber;
	   private int orderItemUnitPrice;
	   private int orderItemQuantity;
	// 메뉴 정보 
	   private String itemType;
	   private String itemName;
}
