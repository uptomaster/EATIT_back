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
import com.bapseguen.app.dto.OrdersDTO;
import com.bapseguen.app.orders.dao.OrdersDAO;

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

        CartListDAO cartDao = new CartListDAO();
        CartDTO cart = new CartDTO();
        cart.setMemberNumber(memberNumber);

        Integer cartNumber = cartDao.selectOpenCartNumberByMember(cart);
        if (cartNumber == null) {
            Result r = new Result();
            r.setRedirect(true);
            r.setPath(request.getContextPath() + "/cartList/view.cl");
            return r;
        }

        List<CartItemDTO> items = cartDao.selectCartItemsByCartNo(cartNumber);
        if (items == null || items.isEmpty()) {
            Result r = new Result();
            r.setRedirect(true);
            r.setPath(request.getContextPath() + "/cartList/view.cl");
            return r;
        }

        // 총액 계산
        long total = 0L;
        for (CartItemDTO it : items) {
            total += (long) it.getCartItemPrice() * it.getCartItemQuantity();
        }
        int amount = (int) Math.min(total, Integer.MAX_VALUE);

        // orderId 생성
        String orderId = ("ORD-" + memberNumber + "-" + System.currentTimeMillis())
                .replaceAll("[^A-Za-z0-9-_]", "_");
        if (orderId.length() < 6) orderId = orderId + "______";
        if (orderId.length() > 64) orderId = orderId.substring(0, 64);

        // 주문 INSERT (READY 상태)
        OrdersDTO order = new OrdersDTO();
        order.setOrdersMemberNumber(memberNumber);
        order.setOrderId(orderId);
        order.setOrdersPaymentStatus("READY");
        order.setOrdersTotalAmount(amount);
        order.setBusinessNumber(cartDao.selectCartBusinessNumberByCartNumber(cartNumber));

        OrdersDAO odao = new OrdersDAO();
        odao.insertOrder(order);
        odao.close();

        request.setAttribute("orderId", orderId);
        request.setAttribute("amount", amount);
        request.setAttribute("customerName",
                (String) request.getSession().getAttribute("memberName"));

        Result r = new Result();
        r.setPath("/app/orders/checkout.jsp");
        r.setRedirect(false);
        return r;
    }
}
