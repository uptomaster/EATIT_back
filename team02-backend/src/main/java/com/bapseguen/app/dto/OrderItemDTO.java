package com.bapseguen.app.dto;

public class OrderItemDTO {
	
    private int orderItemNumber;        // PK
    private int ordersNumber;           // FK -> TBL_ORDERS(ORDERS_NUMBER)
    private Integer itemNumber;         // FK -> TBL_ITEM(ITEM_NUMBER)
    private int orderItemUnitPrice;     // 단가(주문 시점 가격)
    private int orderItemQuantity;      // 수량

    
    // getter & setter
    public int getOrderItemNumber() { return orderItemNumber; }
    public void setOrderItemNumber(int orderItemNumber) { this.orderItemNumber = orderItemNumber; }

    public int getOrdersNumber() { return ordersNumber; }
    public void setOrdersNumber(int ordersNumber) { this.ordersNumber = ordersNumber; }

    public Integer getItemNumber() { return itemNumber; }
    public void setItemNumber(Integer itemNumber) { this.itemNumber = itemNumber; }

    public int getOrderItemUnitPrice() { return orderItemUnitPrice; }
    public void setOrderItemUnitPrice(int orderItemUnitPrice) { this.orderItemUnitPrice = orderItemUnitPrice; }

    public int getOrderItemQuantity() { return orderItemQuantity; }
    public void setOrderItemQuantity(int orderItemQuantity) { this.orderItemQuantity = orderItemQuantity; }

    @Override
    public String toString() {
        return "OrderItemDTO [orderItemNumber=" + orderItemNumber
                + ", ordersNumber=" + ordersNumber
                + ", itemNumber=" + itemNumber
                + ", orderItemUnitPrice=" + orderItemUnitPrice
                + ", orderItemQuantity=" + orderItemQuantity + "]";
    }
}
