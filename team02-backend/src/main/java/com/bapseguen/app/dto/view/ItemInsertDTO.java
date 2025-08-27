package com.bapseguen.app.dto.view;

public class ItemInsertDTO {
	 private int itemNumber;           // selectKey로 세팅
	    private String businessNumber;    // 세션에서
	    private String itemType;          // "FOOD"
	    private String itemName;
	    private int itemPrice;            // NUMBER -> int
	    private String itemContent;
	    private int itemQuantity;         // NUMBER -> int
	    private String itemOrigin;
	    private String itemExpireDate;      // DATE -> java.sql.Date
	    private String itemSellState;     // 'Y' or 'N'
	    
	    
		public int getItemNumber() {
			return itemNumber;
		}
		public void setItemNumber(int itemNumber) {
			this.itemNumber = itemNumber;
		}
		public String getBusinessNumber() {
			return businessNumber;
		}
		public void setBusinessNumber(String businessNumber) {
			this.businessNumber = businessNumber;
		}
		public String getItemType() {
			return itemType;
		}
		public void setItemType(String itemType) {
			this.itemType = itemType;
		}
		public String getItemName() {
			return itemName;
		}
		public void setItemName(String itemName) {
			this.itemName = itemName;
		}
		public int getItemPrice() {
			return itemPrice;
		}
		public void setItemPrice(int itemPrice) {
			this.itemPrice = itemPrice;
		}
		public String getItemContent() {
			return itemContent;
		}
		public void setItemContent(String itemContent) {
			this.itemContent = itemContent;
		}
		public int getItemQuantity() {
			return itemQuantity;
		}
		public void setItemQuantity(int itemQuantity) {
			this.itemQuantity = itemQuantity;
		}
		public String getItemOrigin() {
			return itemOrigin;
		}
		public void setItemOrigin(String itemOrigin) {
			this.itemOrigin = itemOrigin;
		}
	
		public String getItemExpireDate() {
			return itemExpireDate;
		}
		public void setItemExpireDate(String itemExpireDate) {
			this.itemExpireDate = itemExpireDate;
		}
		public String getItemSellState() {
			return itemSellState;
		}
		public void setItemSellState(String itemSellState) {
			this.itemSellState = itemSellState;
		}
		
		
		@Override
		public String toString() {
			return "ItemInsertDTO [itemNumber=" + itemNumber + ", businessNumber=" + businessNumber + ", itemType="
					+ itemType + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemContent=" + itemContent
					+ ", itemQuantity=" + itemQuantity + ", itemOrigin=" + itemOrigin + ", itemExpireDate="
					+ itemExpireDate + ", itemSellState=" + itemSellState + "]";
		}
	    
	    
}
