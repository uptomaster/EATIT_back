package com.bapseguen.app.orders.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.OrderItemDTO;
import com.bapseguen.app.dto.OrdersDTO;
import com.bapseguen.config.MyBatisConfig;

/**
 * Mapper namespace: "orders"
 * - orders.insertOrder
 * - orders.selectOrder
 * - orders.selectOrdersByMember
 * - orders.updateOrderStatus
 * - orders.deleteOrder
 * - orders.insertOrderItem
 * - orders.selectOrderItems
 */
public class OrdersDAO {

    private final SqlSession sqlSession;

    public OrdersDAO() {
        this.sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }

    // 주문(Orders)

    /** 주문 등록: selectKey로 DTO.orderNumber가 채워지며, 반환값으로 PK를 돌려준다. */
    public int insertOrder(OrdersDTO order) {
        sqlSession.insert("orders.insertOrder", order);
        return order.getOrdersNumber(); // selectKey로 주입된 값
    }

    /** 주문 단건 조회 */
    public OrdersDTO selectOrder(int orderNumber) {
        return sqlSession.selectOne("orders.selectOrder", orderNumber);
    }

    /** 특정 회원의 주문 목록 */
    public List<OrdersDTO> selectOrdersByMember(int memberNumber) {
        return sqlSession.selectList("orders.selectOrdersByMember", memberNumber);
    }

    /** 주문 상태 변경 (DTO 전달) */
    public void updateOrderStatus(OrdersDTO order) {
        sqlSession.update("orders.updateOrderStatus", order);
    }

    /** 주문 상태 변경 (편의 메서드) */
    public void updateOrderStatus(int orderNumber, String ordersPaymentStatus) {
        OrdersDTO dto = new OrdersDTO();
        dto.setOrdersNumber(orderNumber);
        dto.setOrdersPaymentStatus(ordersPaymentStatus);
        sqlSession.update("orders.updateOrderStatus", dto);
    }

    /** 주문 삭제 */
    public void deleteOrder(int orderNumber) {
        sqlSession.delete("orders.deleteOrder", orderNumber);
    }

    // 주문상품(OrderItem) 

    /** 주문 상품 등록: selectKey로 DTO.orderItemNumber가 채워지며, 반환값으로 PK를 돌려준다. */
    public int insertOrderItem(OrderItemDTO item) {
        sqlSession.insert("orders.insertOrderItem", item);
        return item.getOrderItemNumber(); // selectKey로 주입된 값
    }

    /** 특정 주문의 상품 목록 */
    public List<OrderItemDTO> selectOrderItems(int orderNumber) {
        return sqlSession.selectList("orders.selectOrderItems", orderNumber);
    }

    /** 수동으로 닫아야 할 때 */
    public void close() {
        if (sqlSession != null) sqlSession.close();
    }
}
