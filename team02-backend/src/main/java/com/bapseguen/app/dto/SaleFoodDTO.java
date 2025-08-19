package com.bapseguen.app.dto;

public class SaleFoodDTO {
//	CREATE TABLE TBL_SALE_FOOD (
//			  SALE_POST_NUMBER  NUMBER PRIMARY KEY
//			                      CONSTRAINT FK_SFOOD_SPOST
//			                      REFERENCES TBL_SALE_POST(SALE_POST_NUMBER) ON DELETE CASCADE,
//			  FOOD_EXPIRATION_DATE   DATE NOT NULL,
//			  FOOD_ORIGIN            VARCHAR2(100)
//			);
	
	 private int salePostNumber;
	 private String foodExpirationDate;
	 private String foodOrigin;
	public int getSalePostNumber() {
		return salePostNumber;
	}
	public void setSalePostNumber(int salePostNumber) {
		this.salePostNumber = salePostNumber;
	}
	public String getFoodExpirationDate() {
		return foodExpirationDate;
	}
	public void setFoodExpirationDate(String foodExpirationDate) {
		this.foodExpirationDate = foodExpirationDate;
	}
	public String getFoodOrigin() {
		return foodOrigin;
	}
	public void setFoodOrigin(String foodOrigin) {
		this.foodOrigin = foodOrigin;
	}
	@Override
	public String toString() {
		return "SaleFoodDTO [salePostNumber=" + salePostNumber + ", foodExpirationDate=" + foodExpirationDate
				+ ", foodOrigin=" + foodOrigin + "]";
	}
	 
	 
}
