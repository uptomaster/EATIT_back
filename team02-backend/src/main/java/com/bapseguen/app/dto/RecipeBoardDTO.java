package com.bapseguen.app.dto;

public class RecipeBoardDTO {

	private int postNumber;
	private String recipeContent;
	
	public int getPostNumber() {
		return postNumber;
	}
	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}
	public String getRecipeContent() {
		return recipeContent;
	}
	public void setRecipeContent(String recipeContent) {
		this.recipeContent = recipeContent;
	}
	@Override
	public String toString() {
		return "RecipeBoardDTO [postNumber=" + postNumber + ", recipeContent=" + recipeContent + "]";
	}
	
	
	
}
