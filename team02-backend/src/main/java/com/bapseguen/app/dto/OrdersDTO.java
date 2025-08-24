package com.bapseguen.app.dto;

import java.util.List;

public class OrdersDTO {

   private int ordersNumber; // 주문번호
   private Integer ordersMemberNumber; // 주문자 회원번호 => 회원 삭제하면 주문내역만 남게할때 Null값 인식하기 위해서 Integer로 변경함.
   // 사업자번호 필요함(어느 가게에서 주문했는지 확인)
   private String businessNumber; // varchar2(13)
   private String ordersPaymentInfo; // 결제 정보 -> 더미값 : CARD:VISA-APPROVED(결제 API배우고 진행)
   private String ordersDate; // 주문 날짜
   private int ordersTotalAmount; // 주문 총량
   private String ordersPaymentStatus; // 결제 상태(success, cancel, fail, pending)

   private List<OrderItemDTO> orderItems; // 주문 상세 (1:N)

   
   
   // getter & setter
   public int getOrdersNumber() {
      return ordersNumber;
   }

   public void setOrdersNumber(int ordersNumber) {
      this.ordersNumber = ordersNumber;
   }

   public Integer getOrdersMemberNumber() {
      return ordersMemberNumber;
   }

   public void setOrdersMemberNumber(Integer ordersMemberNumber) {
      this.ordersMemberNumber = ordersMemberNumber;
   }

   public String getBusinessNumber() {
      return businessNumber;
   }

   public void setBusinessNumber(String businessNumber) {
      this.businessNumber = businessNumber;
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

   public List<OrderItemDTO> getOrderItems() {
      return orderItems;
   }

   public void setOrderItems(List<OrderItemDTO> orderItems) {
      this.orderItems = orderItems;
   }

   @Override
   public String toString() {
      return "OrdersDTO [ordersNumber=" + ordersNumber + ", ordersMemberNumber=" + ordersMemberNumber
            + ", businessNumber=" + businessNumber + ", ordersPaymentInfo=" + ordersPaymentInfo + ", ordersDate="
            + ordersDate + ", ordersTotalAmount=" + ordersTotalAmount + ", ordersPaymentStatus="
            + ordersPaymentStatus + "]";
   }

}
