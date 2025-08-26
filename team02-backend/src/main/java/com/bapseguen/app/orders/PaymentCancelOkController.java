package com.bapseguen.app.orders;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.orders.dao.OrdersDAO;

public class PaymentCancelOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code    = request.getParameter("code");
        String message = request.getParameter("message");
        String orderId = request.getParameter("orderId");

        if (orderId != null && orderId.startsWith("ORD-")) {
            try {
                int ordersNumber = Integer.parseInt(orderId.replace("ORD-", "").trim());
                OrdersDAO dao = new OrdersDAO();
                dao.updateOrderStatus(ordersNumber, "FAILED");
            } catch (Exception ignore) {}
        }

        request.setAttribute("error", "결제가 취소되었거나 실패했습니다.");
        request.setAttribute("detail", (message == null || message.isBlank()) ? "사용자 취소 또는 오류" : message);

        Result r = new Result();
        r.setPath("/app/cartList/paymentFail.jsp");
        return r;
    }
}
