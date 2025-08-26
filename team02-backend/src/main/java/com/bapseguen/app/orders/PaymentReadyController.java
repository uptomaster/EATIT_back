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
        // 로그인 체크
        Integer memberNumber = (Integer) request.getSession().getAttribute("memberNumber");
        if (memberNumber == null) {
            Result r = new Result();
            r.setRedirect(true);
            r.setPath(request.getContextPath() + "/login/login.lo");
            return r;
        }

        // OrderCreateOkController에서 세팅한 값 가져오기
        String orderId = (String) request.getAttribute("orderId");
        Integer amount = (Integer) request.getAttribute("amount");
        String customerName = (String) request.getAttribute("customerName");

        if (orderId == null || amount == null) {
            request.setAttribute("error", "결제 준비 정보가 없습니다.");
            Result r = new Result();
            r.setPath("/app/cartList/shoppingList.jsp");
            return r;
        }

        if (customerName == null) {
            String sessionName = (String) request.getSession().getAttribute("memberName");
            customerName = (sessionName == null || sessionName.isBlank()) ? "고객" : sessionName;
            request.setAttribute("customerName", customerName);
        }

        // 클라이언트 키 가져오기
        String clientKey = request.getServletContext().getInitParameter("TOSS_CLIENT_KEY");
        request.setAttribute("tossClientKey", clientKey);

        Result r = new Result();
        r.setPath("/app/orders/checkout.jsp");
        return r;
    }
}
