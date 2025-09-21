package com.bapseguen.app.orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.cartList.dao.CartListDAO;
import com.bapseguen.app.orders.dao.OrdersDAO;

public class PaymentApproveOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String paymentKey = req.getParameter("paymentKey");
        String orderId    = req.getParameter("orderId");
        String amountStr  = req.getParameter("amount");

        int amount = Integer.parseInt(amountStr);

        // Toss confirm (서버 → Toss API)
        TossService toss = new TossService();
        boolean approved = toss.confirm(paymentKey, orderId, amount, TossService.DOCS_SECRET_KEY);
        if (!approved) {
            Result r = new Result();
            r.setRedirect(true);
            r.setPath(req.getContextPath() + "/orders/paymentFail.or?reason=confirm");
            return r;
        }

        // 로그인한 사용자 번호
        Integer memberNumber = (Integer) req.getSession().getAttribute("memberNumber");

        // 주문 상태 업데이트
        OrdersDAO odao = new OrdersDAO();
        odao.updatePaidByOrderId(orderId, amount);

        // 장바구니 마감
        CartListDAO cdao = new CartListDAO();
        if (memberNumber != null) {
            cdao.closeCurrentCart(memberNumber);
        }

        // 성공 페이지 이동
        Result r = new Result();
        r.setRedirect(false);
        r.setPath("/app/cartList/paymentSuccess.jsp");
        return r;
    }
}
