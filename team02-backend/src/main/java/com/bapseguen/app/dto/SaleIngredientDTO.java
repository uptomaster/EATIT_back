package com.bapseguen.app.dto;

public class SaleIngredientDTO {
//	CREATE TABLE TBL_SALE_INGREDIENT (
//			  SALE_POST_NUMBER  NUMBER PRIMARY KEY
//			                      CONSTRAINT FK_SING_SPOST
//			                      REFERENCES TBL_SALE_POST(SALE_POST_NUMBER) ON DELETE CASCADE,
//			  INGREDIENT_UNIT              VARCHAR2(20), -- KG, G, 개 등
//			  INGREDIENT_EXPIRATION_DATE   DATE NOT NULL
//			);
	
	private int salePostNumber;
	private String ingredientUnit;
	private String ingredientExpirationDate;
	public int getSalePostNumber() {
		return salePostNumber;
	}
	public void setSalePostNumber(int salePostNumber) {
		this.salePostNumber = salePostNumber;
	}
	public String getIngredientUnit() {
		return ingredientUnit;
	}
	public void setIngredientUnit(String ingredientUnit) {
		this.ingredientUnit = ingredientUnit;
	}
	public String getIngredientExpirationDate() {
		return ingredientExpirationDate;
	}
	public void setIngredientExpirationDate(String ingredientExpirationDate) {
		this.ingredientExpirationDate = ingredientExpirationDate;
	}
	@Override
	public String toString() {
		return "SaleIngredientDTO [salePostNumber=" + salePostNumber + ", ingredientUnit=" + ingredientUnit
				+ ", ingredientExpirationDate=" + ingredientExpirationDate + "]";
	}
	
	
	
}
