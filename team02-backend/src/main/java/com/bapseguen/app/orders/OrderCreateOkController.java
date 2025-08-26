package com.bapseguen.app.orders;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.cartList.dao.CartListDAO;
import com.bapseguen.app.dto.CartItemDTO;
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

        // 2) 장바구니 합계 계산
        CartListDAO cartDAO = new CartListDAO();
        List<CartItemDTO> items = cartDAO.selectCurrentCartItemsWithPrice(memberNumber);

        int amount = 0;
        if (items != null) {
            for (CartItemDTO it : items) {
                amount += it.getCartItemPrice() * it.getCartItemQuantity();
            }
        }

        if (amount <= 0) {
            request.setAttribute("error", "장바구니가 비어 있거나 금액이 0원입니다.");
            result.setPath("/cartList/view.cl"); // 컨트롤러로 보내야 모델 채워짐
            result.setRedirect(true);
            return result;
        }

        // (필요하다면 businessNumber는 items.get(0).getBusinessNumber() 식으로 얻을 수 있음)
        String businessNumber = request.getParameter("businessNumber");

        // 3) 주문자 이름 (결제창 표시용)
        String customerName = (String) request.getSession().getAttribute("memberName");
        if (customerName == null || customerName.isBlank()) {
            customerName = "고객";
        }

        // 4) orderId 생성
        String orderId = makeOrderId(memberNumber);

        // 5) DTO 구성
        OrdersDTO order = new OrdersDTO();
        order.setOrderId(orderId);
        order.setOrdersMemberNumber(memberNumber);
        order.setBusinessNumber(businessNumber);
        order.setOrdersPaymentInfo(null);
        order.setOrdersTotalAmount(amount);
        order.setOrdersPaymentStatus("READY");

        // 6) INSERT
        OrdersDAO ordersDAO = new OrdersDAO();
        try {
            ordersDAO.insertOrder(order);

            // 7) 결제 준비 컨트롤러로 전달
            request.setAttribute("orderId", orderId);
            request.setAttribute("amount", amount);
            request.setAttribute("customerName", customerName);

            result.setRedirect(false); // forward
            result.setPath("/orders/paymentReady.or");
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "주문 생성 중 오류가 발생했습니다: " + e.getMessage());
            result.setPath("/cartList/view.cl");
            result.setRedirect(true);
            return result;
        } finally {
            ordersDAO.close();
        }
    }

    private static String makeOrderId(long memberNumber) {
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String rand = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        String id = "ORD-" + ts + "-" + memberNumber + "-" + rand;
        if (id.length() > 64) {
            id = id.substring(0, 64);
        }
        return id;
    }
}
