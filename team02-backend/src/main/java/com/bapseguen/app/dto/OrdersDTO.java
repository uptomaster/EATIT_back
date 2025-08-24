package com.bapseguen.app.dto;

import java.util.List;

public class OrdersDTO {

<<<<<<< HEAD
	private int ordersNumber;
	private int ordersMemberNumber;
	private String ordersPaymentInfo;
	private String ordersDate;
	private int ordersTotalAmount;
	private String ordersPaymentStatus;
=======
	private int ordersNumber; // 주문번호
	private Integer ordersMemberNumber; // 주문자 회원번호 => 회원 삭제하면 주문내역만 남게할때 Null값 인식하기 위해서 Integer로 변경함.
	// 사업자번호 필요함(어느 가게에서 주문했는지 확인)
	private String businessNumber; // varchar2(13)
	private String ordersPaymentInfo; // 결제 정보 -> 더미값 : CARD:VISA-APPROVED(결제 API배우고 진행)
	private String ordersDate; // 주문 날짜
	private int ordersTotalAmount; // 주문 총량
	private String ordersPaymentStatus; // 결제 상태(success, cancel, fail, pending)

	private List<OrderItemDTO> orderItems; // 주문 상세 (1:N)

	
>>>>>>> d182d8ad59917b1633fd463b5f23dabcbf9fb439
	
	// getter & setter
	public int getOrdersNumber() {
		return ordersNumber;
	}
	
	public void setOrdersNumber(int ordersNumber) {
		this.ordersNumber = ordersNumber;
	}
	public int getOrdersMemberNumber() {
		return ordersMemberNumber;
	}
	public void setOrdersMemberNumber(int ordersMemberNumber) {
		this.ordersMemberNumber = ordersMemberNumber;
	}
	public String getOrdersPaymentInfo() {
		return ordersPaymentInfo;
	}
	public void setOrdersPaymentInfo(String ordersPaymentInfo) {
		this.ordersPaymentInfo = ordersPaymentInfo;
	}
	public String getOrdersDate() {
		return ordersDate;
	}
	public void setOrdersDate(String ordersDate) {
		this.ordersDate = ordersDate;
	}
	public int getOrdersTotalAmount() {
		return ordersTotalAmount;
	}
	public void setOrdersTotalAmount(int ordersTotalAmount) {
		this.ordersTotalAmount = ordersTotalAmount;
	}
	public String getOrdersPaymentStatus() {
		return ordersPaymentStatus;
	}
	public void setOrdersPaymentStatus(String ordersPaymentStatus) {
		this.ordersPaymentStatus = ordersPaymentStatus;
	}
	
	@Override
	public String toString() {
		return "OrdersDTO [ordersNumber=" + ordersNumber + ", ordersMemberNumber=" + ordersMemberNumber
				+ ", ordersPaymentInfo=" + ordersPaymentInfo + ", ordersDate=" + ordersDate + ", ordersTotalAmount="
				+ ordersTotalAmount + ", ordersPaymentStatus=" + ordersPaymentStatus + "]";
	}
    
}
