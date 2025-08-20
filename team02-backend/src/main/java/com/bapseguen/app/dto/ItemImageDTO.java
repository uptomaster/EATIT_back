package com.bapseguen.app.dto;

public class ItemImageDTO {
	
	private int itemImageNumber;
	private String itemImageSystemName;
	private String itemImageOriginalName;
	private int itemNumber;
	
	public int getItemImageNumber() {
		return itemImageNumber;
	}
	public void setItemImageNumber(int itemImageNumber) {
		this.itemImageNumber = itemImageNumber;
	}
	public String getItemImageSystemName() {
		return itemImageSystemName;
	}
	public void setItemImageSystemName(String itemImageSystemName) {
		this.itemImageSystemName = itemImageSystemName;
	}
	public String getItemImageOriginalName() {
		return itemImageOriginalName;
	}
	public void setItemImageOriginalName(String itemImageOriginalName) {
		this.itemImageOriginalName = itemImageOriginalName;
	}
	public int getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}
	
	@Override
	public String toString() {
		return "ItemImageDTO [itemImageNumber=" + itemImageNumber + ", itemImageSystemName=" + itemImageSystemName
				+ ", itemImageOriginalName=" + itemImageOriginalName + ", itemNumber=" + itemNumber
				+ ", getItemImageNumber()=" + getItemImageNumber() + ", getItemImageSystemName()="
				+ getItemImageSystemName() + ", getItemImageOriginalName()=" + getItemImageOriginalName()
				+ ", getItemNumber()=" + getItemNumber() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}
