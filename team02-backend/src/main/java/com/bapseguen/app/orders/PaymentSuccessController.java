package com.bapseguen.app.orders;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;

public class PaymentSuccessController implements Execute {

    @Override
    public Result execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String paymentKey = req.getParameter("paymentKey");
        String orderId    = req.getParameter("orderId");
        String amountStr  = req.getParameter("amount");

        if (paymentKey == null || orderId == null || amountStr == null) {
            Result r = new Result();
            r.setRedirect(true);
            r.setPath(req.getContextPath() + "/cartList/view.cl");
            return r;
        }

        // PaymentApproveOkController로 위임
        String q = "orderId=" + URLEncoder.encode(orderId, StandardCharsets.UTF_8.name())
                 + "&amount=" + amountStr
                 + "&paymentKey=" + URLEncoder.encode(paymentKey, StandardCharsets.UTF_8.name());

        Result r = new Result();
        r.setRedirect(true);
        r.setPath(req.getContextPath() + "/orders/paymentApproveOk.or?" + q);
        return r;
    }
}
