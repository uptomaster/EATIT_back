package com.bapseguen.app.orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.cartList.dao.CartListDAO;
import com.bapseguen.app.dto.CartDTO;
import com.bapseguen.app.dto.CartItemDTO;

import java.util.List;

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

        // 장바구니 총 금액 재계산
        CartListDAO cartDao = new CartListDAO();
        List<CartItemDTO> items = cartDao.selectCurrentCartItemsWithPrice(memberNumber);

        int amount = 0;
        if (items != null) {
            for (CartItemDTO it : items) {
                amount += it.getCartItemPrice() * it.getCartItemQuantity();
            }
        }

        if (amount <= 0) {
            Result r = new Result();
            r.setRedirect(true);
            r.setPath(request.getContextPath() + "/cartList/view.cl");
            return r;
        }

        // 주문 ID (결제 API용, 아직 DB INSERT 안함)
        String orderId = "ORD-" + memberNumber + "-" + System.currentTimeMillis();

        String customerName = (String) request.getSession().getAttribute("memberName");
        if (customerName == null || customerName.isBlank()) customerName = "고객";

        // 결제 위젯에 필요한 값 전달
        request.setAttribute("tossClientKey", request.getServletContext().getInitParameter("TOSS_CLIENT_KEY"));
        request.setAttribute("orderId", orderId);
        request.setAttribute("amount", amount);
        request.setAttribute("customerName", customerName);

        Result r = new Result();
        r.setRedirect(false);
        r.setPath("/app/orders/checkout.jsp");
        return r;
    }
}
