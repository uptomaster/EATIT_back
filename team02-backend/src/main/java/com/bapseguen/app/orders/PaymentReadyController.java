package com.bapseguen.app.orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;

public class PaymentReadyController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer memberNumber = (Integer) request.getSession().getAttribute("memberNumber");
        if (memberNumber == null) {
          Result r = new Result();
          r.setRedirect(true);
          r.setPath(request.getContextPath() + "/login/login.lo");
          return r;
        }

        String orderId = (String) request.getAttribute("orderId");
        Integer amount = (Integer) request.getAttribute("amount");
        String customerName = (String) request.getAttribute("customerName");

        // fallback 1: 파라미터에서 시도
        if (orderId == null) orderId = request.getParameter("orderId");
        if (amount == null) {
          try { amount = Integer.valueOf(request.getParameter("amount")); } catch (Exception ignore) {}
        }
        if (customerName == null || customerName.isBlank()) {
          String sessionName = (String) request.getSession().getAttribute("memberName");
          customerName = (sessionName == null || sessionName.isBlank()) ? "고객" : sessionName;
        }

        // fallback 2: DB에서 가장 최근 READY 주문 찾기 (선택)
        if (orderId == null || amount == null) {
          com.bapseguen.app.orders.dao.OrdersDAO dao = new com.bapseguen.app.orders.dao.OrdersDAO();
          // 최근 READY 주문을 하나 가져오는 쿼리를 따로 만들어두면 좋아요. (예: orders.selectLatestReadyByMember)
          com.bapseguen.app.dto.OrdersDTO last = dao.selectLatestReadyByMember(memberNumber); // 직접 만들 함수
          if (last != null) {
            if (orderId == null) orderId = last.getOrderId();
            if (amount == null) amount = last.getOrdersTotalAmount();
          }
          dao.close();
        }

        // 여전히 없으면 장바구니로 안전하게 돌려보내기
        if (orderId == null || amount == null || amount <= 0) {
          request.setAttribute("error", "결제 준비 정보가 없습니다. 다시 시도해 주세요.");
          Result r = new Result();
          r.setPath("/app/cartList/shoppingList.jsp");
          return r;
        }

        // 클라이언트 키
        String clientKey = request.getServletContext().getInitParameter("TOSS_CLIENT_KEY");
        request.setAttribute("tossClientKey", clientKey);

        // 최종 전달
        request.setAttribute("orderId", orderId);
        request.setAttribute("amount", amount);
        request.setAttribute("customerName", customerName);

        Result r = new Result();
        r.setPath("/app/orders/checkout.jsp");
        return r;
      }
}
