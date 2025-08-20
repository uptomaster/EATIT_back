package com.bapseguen.app.dto;

public class PromoBoardDTO {
	
	private int postNubmer;
	private String promoContent;
	
	public int getPostNubmer() {
		return postNubmer;
	}
	public void setPostNubmer(int postNubmer) {
		this.postNubmer = postNubmer;
	}
	public String getPromoContent() {
		return promoContent;
	}
	public void setPromoContent(String promoContent) {
		this.promoContent = promoContent;
	}
	@Override
	public String toString() {
		return "PromoBoardDTO [postNubmer=" + postNubmer + ", promoContent=" + promoContent + "]";
	}
	
	
}
