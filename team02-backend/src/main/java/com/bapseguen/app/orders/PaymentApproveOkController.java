package com.bapseguen.app.orders;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.OrdersDTO;
import com.bapseguen.app.orders.dao.OrdersDAO;

import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PaymentApproveOkController implements Execute {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String paymentKey = request.getParameter("paymentKey");
        String orderId    = request.getParameter("orderId");
        String amountStr  = request.getParameter("amount");

        if (paymentKey == null || orderId == null || amountStr == null) {
            request.setAttribute("error", "필수 파라미터 누락.");
            Result r = new Result(); r.setPath("/app/orders/paymentFail.jsp"); return r;
        }

        int amount;
        int ordersNumber;
        try {
            amount = Integer.parseInt(amountStr);
            ordersNumber = Integer.parseInt(orderId.replace("ORD-", "").trim());
        } catch (Exception e) {
            request.setAttribute("error", "orderId/amount 형식 오류.");
            Result r = new Result(); r.setPath("/app/orders/paymentFail.jsp"); return r;
        }

        OrdersDAO ordersDAO = new OrdersDAO();
        OrdersDTO order = ordersDAO.selectOrder(ordersNumber);
        if (order == null) {
            request.setAttribute("error", "주문을 찾을 수 없습니다.");
            Result r = new Result(); r.setPath("/app/orders/paymentFail.jsp"); return r;
        }

        if (order.getOrdersTotalAmount() != amount) {
            request.setAttribute("error", "결제 금액이 주문 금액과 일치하지 않습니다.");
            Result r = new Result(); r.setPath("/app/orders/paymentFail.jsp"); return r;
        }

        if ("PAID".equalsIgnoreCase(order.getOrdersPaymentStatus())) {
            request.setAttribute("ordersNumber", ordersNumber);
            request.setAttribute("amount", amount);
            Result r = new Result(); r.setPath("/app/cartList/paymentSuccess.jsp"); return r;
        }

        String secretKey = request.getServletContext().getInitParameter("TOSS_SECRET_KEY");
        OkHttpClient client = new OkHttpClient();
        String auth = Credentials.basic(secretKey, "");
        String json = "{\"paymentKey\":\"" + escape(paymentKey) + "\",\"orderId\":\"" + escape(orderId) + "\",\"amount\":" + amount + "}";

        Request httpRequest = new Request.Builder()
                .url("https://api.tosspayments.com/v1/payments/confirm")
                .post(RequestBody.create(json, JSON))
                .addHeader("Authorization", auth)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response httpResponse = client.newCall(httpRequest).execute()) {
            String body = httpResponse.body() != null ? httpResponse.body().string() : "";

            if (!httpResponse.isSuccessful()) {
                ordersDAO.updateOrderStatus(ordersNumber, "FAILED");
                request.setAttribute("error", "결제 승인 실패");
                request.setAttribute("detail", body);
                Result r = new Result(); r.setPath("/app/orders/paymentFail.jsp"); return r;
            }

            ordersDAO.updateOrderStatus(ordersNumber, "PAID");

            try {
                Integer memberNumber = (Integer) request.getSession().getAttribute("memberNumber");
                if (memberNumber != null) {
                    com.bapseguen.app.cartList.dao.CartListDAO cartDao = new com.bapseguen.app.cartList.dao.CartListDAO();
                    cartDao.closeCurrentCart(memberNumber);
                }
            } catch (Exception ignore) {}

            request.setAttribute("ordersNumber", ordersNumber);
            request.setAttribute("amount", amount);
            Result r = new Result(); r.setPath("/app/orders/paymentSuccess.jsp"); return r;
        }
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
