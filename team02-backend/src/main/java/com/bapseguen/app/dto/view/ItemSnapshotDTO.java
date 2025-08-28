package com.bapseguen.app.dto.view;

public class ItemSnapshotDTO {
	private Integer itemNumber; // 상품 PK → 어떤 상품인지
	private String businessNumber; // 가게번호 → 장바구니 '한 가게' 규칙 검증용
	private String itemName; // 메뉴명 → 스냅샷 보존, 주문 내역용
	private Integer itemPrice; // 가격 → 결제 금액 검증 핵심
	private Integer itemQuantity; // 재고 (nullable 허용) → 품절/재고 부족 검증
	private String itemSellState; // 'Y'/'N' → 현재 판매중 여부 확인

	// getter & setter
	public Integer getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Integer getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getItemSellState() {
		return itemSellState;
	}

	public void setItemSellState(String itemSellState) {
		this.itemSellState = itemSellState;
	}
}
