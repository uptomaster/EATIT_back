package com.bapseguen.app.orders.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.OrderItemDTO;
import com.bapseguen.app.dto.OrdersDTO;
import com.bapseguen.app.dto.view.ItemWithImgDTO;
import com.bapseguen.config.MyBatisConfig;

public class OrdersDAO {

    private final SqlSession sqlSession;

    public OrdersDAO() {
        this.sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true); // auto-commit
    }

    // ========================= Orders =========================

    /** 주문 등록 */
    public int insertOrder(OrdersDTO order) {
        sqlSession.insert("orders.insertOrder", order);
        return order.getOrdersNumber(); // selectKey로 주입된 값 반환
    }

    /** 주문 단건 조회 */
    public OrdersDTO selectOrder(int ordersNumber) {
        return sqlSession.selectOne("orders.selectOrder", ordersNumber);
    }

    /** 특정 회원의 주문 목록 */
    public List<OrdersDTO> selectOrdersByMember(int memberNumber) {
        return sqlSession.selectList("orders.selectOrdersByMember", memberNumber);
    }

    /** 주문 상태 변경 (DTO 기반) */
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
        Map<String, Object> param = new HashMap<>();
        param.put("orderNumber", ordersNumber);
        param.put("paymentInfo", paymentInfoJson);
        sqlSession.update("orders.updateOrderPaymentInfo", param);
    }

    /** orderId 로 주문 조회 */
    public OrdersDTO selectOrderByOrderId(String orderId) {
        return sqlSession.selectOne("orders.selectOrderByOrderId", orderId);
    }

    // ========================= Order Items =========================

    /** 주문 상품 등록 */
    public int insertOrderItem(OrderItemDTO item) {
        sqlSession.insert("orders.insertOrderItem", item);
        return item.getOrderItemNumber(); // selectKey로 주입된 값 반환
    }

    /** 특정 주문의 상품 목록 */
    public List<OrderItemDTO> selectOrderItems(int ordersNumber) {
        return sqlSession.selectList("orders.selectOrderItems", ordersNumber);
    }

    // ========================= Etc =========================

    /** 특정 상품 상세 조회 (item 네임스페이스에 의존) */
    public ItemWithImgDTO selectItemDetail(int itemNumber) {
        return sqlSession.selectOne("item.selectItemDetail", itemNumber);
    }

    /** 특정 회원의 가장 최근 READY 주문 */
    public OrdersDTO selectLatestReadyByMember(int memberNumber) {
        return sqlSession.selectOne("orders.selectLatestReadyByMember", memberNumber);
    }

    /** 결제 성공 시 PAID 처리 */
    public int updatePaidByOrderId(String orderId, int amount) {
        Map<String, Object> param = new HashMap<>();
        param.put("orderId", orderId);
        param.put("amount", amount);
        return sqlSession.update("orders.updatePaidByOrderId", param);
    }

    /** orderId 기반으로 상태 업데이트 */
    public void updateOrderStatusByOrderId(String orderId, String ordersPaymentStatus) {
        Map<String, Object> param = new HashMap<>();
        param.put("orderId", orderId);
        param.put("ordersPaymentStatus", ordersPaymentStatus);
        sqlSession.update("orders.updateOrderStatusByOrderId", param);
    }

    /** 세션 종료 */
    public void close() {
        if (sqlSession != null) sqlSession.close();
    }
}
