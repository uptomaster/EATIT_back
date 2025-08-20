package com.bapseguen.app.dto;

import java.util.List;

public class OrdersDTO {

	private int ordersNumber;
	private int ordersMemberNumber;
	private String ordersPaymentInfo;
	private String ordersDate;
	private int ordersTotalAmount;
	private String ordersPaymentStatus;
	
	public int getOrdersNumber() {
		return ordersNumber;
	}
	
	// getter & setter
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
