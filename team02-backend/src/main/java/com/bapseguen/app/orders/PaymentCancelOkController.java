package com.bapseguen.app.orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.orders.dao.OrdersDAO;

public class PaymentCancelOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String orderId = req.getParameter("orderId");
        String reason  = req.getParameter("reason");

        // DB에 실패 상태 기록
        if (orderId != null && !orderId.isBlank()) {
            OrdersDAO odao = new OrdersDAO();
            odao.updateOrderStatusByOrderId(orderId, "FAILED");
            odao.close();
        }

        // 에러 메시지 JSP로 전달
        String msg = "결제가 취소되었거나 실패했습니다.";
        if (reason != null) {
            msg += " (" + reason + ")";
        }
        req.setAttribute("error", msg);

        // cartList 폴더의 JSP로 forward
        Result r = new Result();
        r.setRedirect(false);
        r.setPath("/app/cartList/paymentFail.jsp");
        return r;
    }
}
