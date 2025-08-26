package com.bapseguen.app.orders.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.OrderItemDTO;
import com.bapseguen.app.dto.OrdersDTO;
import com.bapseguen.app.dto.view.ItemWithImgDTO;
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
 * - orders.updateOrderPaymentInfo
 * - orders.selectOrderByOrderId (편의)
 */
public class OrdersDAO {

    private final SqlSession sqlSession;

    public OrdersDAO() {
        this.sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }

    // =========================
    // Orders
    // =========================

    /** 주문 등록: selectKey로 DTO.ordersNumber가 채워지며, 반환값으로 PK를 돌려준다. */
    public int insertOrder(OrdersDTO order) {
        sqlSession.insert("orders.insertOrder", order);
        return order.getOrdersNumber(); // selectKey로 주입된 값
    }

    /** 주문 단건 조회 (PK) */
    public OrdersDTO selectOrder(int ordersNumber) {
        return sqlSession.selectOne("orders.selectOrder", ordersNumber);
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
    public void updateOrderStatus(int ordersNumber, String ordersPaymentStatus) {
        OrdersDTO dto = new OrdersDTO();
        dto.setOrdersNumber(ordersNumber);
        dto.setOrdersPaymentStatus(ordersPaymentStatus);
        sqlSession.update("orders.updateOrderStatus", dto);
    }

    /** 주문 삭제 */
    public void deleteOrder(int ordersNumber) {
        sqlSession.delete("orders.deleteOrder", ordersNumber);
    }

    /** 결제정보(JSON 등) 업데이트 */
    public void updateOrderPaymentInfo(int ordersNumber, String paymentInfoJson) {
        sqlSession.update("orders.updateOrderPaymentInfo",
            Map.of("orderNumber", ordersNumber, "paymentInfo", paymentInfoJson));
    }

    /** (편의) orderId로 주문 조회: 결제 승인 콜백에서 사용 */
    public OrdersDTO selectOrderByOrderId(String orderId) {
        return sqlSession.selectOne("orders.selectOrderByOrderId", orderId);
    }

    // =========================
    // Order Items
    // =========================

    /** 주문 상품 등록: selectKey로 DTO.orderItemNumber가 채워지며, 반환값으로 PK를 돌려준다. */
    public int insertOrderItem(OrderItemDTO item) {
        sqlSession.insert("orders.insertOrderItem", item);
        return item.getOrderItemNumber(); // selectKey로 주입된 값
    }

    /** 특정 주문의 상품 목록 */
    public List<OrderItemDTO> selectOrderItems(int orderNumber) {
        return sqlSession.selectList("orders.selectOrderItems", orderNumber);
    }

    // =========================
    // Etc.
    // =========================

    /** 상품 상세 조회 (item 네임스페이스에 의존) */
    public ItemWithImgDTO selectItemDetail(int itemNumber) {
        return sqlSession.selectOne("item.selectItemDetail", itemNumber);
    }

    /** 수동 종료 */
    public void close() {
        if (sqlSession != null) sqlSession.close();
    }
}
