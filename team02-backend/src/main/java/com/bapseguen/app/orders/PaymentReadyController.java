package com.bapseguen.app.orders;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.cartList.dao.CartListDAO;
import com.bapseguen.app.dto.CartDTO;
import com.bapseguen.app.dto.CartItemDTO;
import com.bapseguen.app.orders.dao.OrdersDAO;
import com.bapseguen.app.dto.OrdersDTO;

public class PaymentReadyController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer memberNumber = (Integer) request.getSession().getAttribute("memberNumber");
        if (memberNumber == null) {
            Result r = new Result();
            r.setRedirect(true);
            r.setPath(request.getContextPath() + "/login/login.lo");
            return r;
        }

        // 1) 우선 요청/세션에서 orderId/amount/name 수집
        String orderId = (String) request.getAttribute("orderId");
        Integer amount = (Integer) request.getAttribute("amount");
        String customerName = (String) request.getAttribute("customerName");

        if (orderId == null) orderId = request.getParameter("orderId");
        if (amount == null) {
            try { amount = Integer.valueOf(request.getParameter("amount")); } catch (Exception ignore) {}
        }
        if (customerName == null || customerName.isBlank()) {
            String sessionName = (String) request.getSession().getAttribute("memberName");
            customerName = (sessionName == null || sessionName.isBlank()) ? "고객" : sessionName;
        }

        // 2) 없으면 Cart에서 금액 재계산 (가장 신뢰도 높음)
        if (amount == null || amount <= 0) {
            CartListDAO cartDao = new CartListDAO();
            CartDTO cart = new CartDTO();
            cart.setMemberNumber(memberNumber);

            Integer cartNumber = cartDao.selectOpenCartNumberByMember(cart);
            if (cartNumber != null) {
                List<CartItemDTO> items = cartDao.selectCartItemsByCartNo(cartNumber);
                long total = 0L;
                if (items != null) {
                    for (CartItemDTO it : items) {
                        total += (long) it.getCartItemPrice() * it.getCartItemQuantity();
                    }
                }
                if (total > 0) amount = (int) Math.min(total, Integer.MAX_VALUE);
            }
        }

        // 3) orderId가 없으면 생성 (규칙: [A-Za-z0-9-_], 6~64자)
        if (orderId == null || orderId.length() < 6) {
            orderId = ("ORD-" + memberNumber + "-" + System.currentTimeMillis()).replaceAll("[^A-Za-z0-9-_]", "_");
            if (orderId.length() < 6) orderId = (orderId + "______").substring(0, 6);
            if (orderId.length() > 64) orderId = orderId.substring(0, 64);
        }

        // 4) 마지막 안전장치: 준비정보 부족 → 컨트롤러 경유로 장바구니 이동
        if (amount == null || amount <= 0) {
            Result r = new Result();
            r.setPath(request.getContextPath() + "/cartList/view.cl"); // 컨트롤러로!
            r.setRedirect(true);
            return r;
        }

        // 5) 프론트 결제 위젯용 clientKey 전달
        String clientKey = request.getServletContext().getInitParameter("TOSS_CLIENT_KEY");
        request.setAttribute("tossClientKey", clientKey);

        // 6) checkout.jsp로 forward (위젯 로딩 → 결제 요청)
        request.setAttribute("orderId", orderId);
        request.setAttribute("amount", amount);
        request.setAttribute("customerName", customerName);

        
        
        Result r = new Result();
        r.setPath("/app/orders/checkout.jsp"); // forward: contextPath 붙이지 않음
        r.setRedirect(false);
        return r;
    }
}
