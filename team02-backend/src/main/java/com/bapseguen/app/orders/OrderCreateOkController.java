package com.bapseguen.app.orders;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.OrdersDTO;
import com.bapseguen.app.orders.dao.OrdersDAO;

/**
 * 주문 생성 → DB INSERT → 결제 준비로 전송
 * - INSERT 전에 반드시 orderId를 생성/세팅한다.
 * - PaymentReadyController에서 필요로 하는 request attribute: orderId, amount, customerName
 */
public class OrderCreateOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Result result = new Result();

        // 1) 로그인 체크
        Integer memberNumber = (Integer) request.getSession().getAttribute("memberNumber");
        if (memberNumber == null) {
            result.setRedirect(true);
            result.setPath(request.getContextPath() + "/login/login.lo");
            return result;
        }

        // 2) 파라미터 받기 (amount, businessNumber 등)
        //    - amount는 장바구니 합계 계산으로 대체 가능. 지금은 파라미터로 처리.
        String amountParam = request.getParameter("amount");
        String businessNumber = request.getParameter("businessNumber"); // 가게 번호를 쓰는 경우
        int amount = 0;
        try {
            amount = Integer.parseInt(amountParam);
            if (amount <= 0) throw new NumberFormatException("amount must be positive");
        } catch (Exception e) {
            request.setAttribute("error", "결제 금액이 올바르지 않습니다.");
            result.setPath("/app/cartList/shoppingList.jsp");
            return result;
        }

        // 3) 주문자 이름 (결제창 표시용)
        String customerName = (String) request.getSession().getAttribute("memberName");
        if (customerName == null || customerName.isBlank()) customerName = "고객";

        // 4) orderId 생성 (토스 규칙: 영문대소문자/숫자/-/_ , 6~64자)
        String orderId = makeOrderId(memberNumber);

        // 5) DTO 구성
        OrdersDTO order = new OrdersDTO();
        order.setOrderId(orderId);                  // ★ 반드시 세팅
        order.setOrdersMemberNumber(memberNumber);  // 회원 번호 (컬럼명이 MEMBER_NUMBER면 매퍼에서 그에 맞게 매핑)
        order.setBusinessNumber(businessNumber);    // 사용하는 경우만
        order.setOrdersPaymentInfo(null);           // 결제 전이므로 null 또는 빈 문자열
        order.setOrdersTotalAmount(amount);
        order.setOrdersPaymentStatus("READY");      // 결제 전 상태

        // 디버그 로그
        System.out.println("[OrderCreateOk] orderId=" + orderId + ", amount=" + amount + ", member=" + memberNumber);

        // 6) INSERT
        OrdersDAO ordersDAO = new OrdersDAO();
        try {
            int ordersNumber = ordersDAO.insertOrder(order);
            // (선택) 주문상품(OrderItem)도 여기서 장바구니 기반으로 INSERT 하려면 추가

            // 7) 결제 준비 컨트롤러로 전달 (PaymentReadyController에서 필요)
            request.setAttribute("orderId", orderId);
            request.setAttribute("amount", amount);
            request.setAttribute("customerName", customerName);

            // 8) 포워드 (리다이렉트 X: request attribute 유지)
            result.setRedirect(false);
            result.setPath("/orders/paymentReady.or");
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "주문 생성 중 오류가 발생했습니다: " + e.getMessage());
            result.setPath("/app/cartList/shoppingList.jsp");
            return result;
        } finally {
            ordersDAO.close();
        }
    }

    /**
     * 토스 규칙(6~64자, [A-Za-z0-9_-])을 만족하는 안전한 orderId 생성
     * 예) ORD-20250826-12345-a1b2c3
     */
    private static String makeOrderId(long memberNumber) {
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String rand = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        String id = "ORD-" + ts + "-" + memberNumber + "-" + rand; // 영문/숫자/하이픈만 포함
        if (id.length() > 64) {
            id = id.substring(0, 64);
        }
        return id;
    }
}
