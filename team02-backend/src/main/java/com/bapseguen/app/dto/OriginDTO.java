package com.bapseguen.app.dto;

public class OriginDTO {

	private int originNumber;
	private String originItem;
	private String originMenu;
	private String originLocation;
	private String businessNumber;
	
	public int getOriginNumber() {
		return originNumber;
	}
	
	// getter & setter
	public void setOriginNumber(int originNumber) {
		this.originNumber = originNumber;
	}
	public String getOriginItem() {
		return originItem;
	}
	public void setOriginItem(String originItem) {
		this.originItem = originItem;
	}
	public String getOriginMenu() {
		return originMenu;
	}
	public void setOriginMenu(String originMenu) {
		this.originMenu = originMenu;
	}
	public String getOriginLocation() {
		return originLocation;
	}
	public void setOriginLocation(String originLocation) {
		this.originLocation = originLocation;
	}
	public String getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	@Override
	public String toString() {
		return "OriginDTO [originNumber=" + originNumber + ", originItem=" + originItem + ", originMenu=" + originMenu
				+ ", originLocation=" + originLocation + ", businessNumber=" + businessNumber + "]";
	}
	
	
}
