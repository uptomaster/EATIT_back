package com.bapseguen.app.orders;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;

public class PaymentSuccessController implements Execute {

    @Override
    public Result execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1) 로그인 체크
        Integer memberNumber = (Integer) req.getSession().getAttribute("memberNumber");
        if (memberNumber == null) {
            Result r = new Result();
            r.setPath(req.getContextPath() + "/login/login.lo");
            r.setRedirect(true);
            return r;
        }

        // 2) 토스 successUrl 파라미터
        String paymentKey = req.getParameter("paymentKey");
        String orderId    = req.getParameter("orderId");
        String amountStr  = req.getParameter("amount");

        if (paymentKey == null || orderId == null || amountStr == null) {
            Result r = new Result();
            r.setPath(req.getContextPath() + "/cartList/view.cl");
            r.setRedirect(true);
            return r;
        }

        int amount;
        try {
            amount = Integer.parseInt(amountStr);
        } catch (NumberFormatException e) {
            Result r = new Result();
            r.setPath(req.getContextPath() + "/cartList/view.cl");
            r.setRedirect(true);
            return r;
        }

        // 3) 승인(Confirm) 호출용 시크릿 키
        //  - web.xml의 <context-param>TOSS_SECRET_KEY</context-param> 사용
        //  - v2 문서용 키를 web.xml 에 넣었다면 그 값이 그대로 내려옵니다.
        String secretKey = req.getServletContext().getInitParameter("TOSS_SECRET_KEY");

        TossService toss = new TossService();
        boolean approved = toss.confirm(paymentKey, orderId, amount, secretKey);

        if (!approved) {
            Result r = new Result();
            r.setPath(req.getContextPath() + "/orders/paymentFail.or?reason=confirm");
            r.setRedirect(true);
            return r;
        }

        // 4) 승인 성공 → 기존 승인 처리 컨트롤러로 위임 (DB 업데이트/카트 마감 등)
        //    이미 프로젝트에 있는 PaymentApproveOkController가 처리하게 함
        String q = "orderId=" + URLEncoder.encode(orderId, StandardCharsets.UTF_8.name())
                 + "&amount=" + amount
                 + "&paymentKey=" + URLEncoder.encode(paymentKey, StandardCharsets.UTF_8.name());

        Result r = new Result();
        r.setPath(req.getContextPath() + "/orders/paymentApproveOk.or?" + q);
        r.setRedirect(true);
        return r;
    }
}
