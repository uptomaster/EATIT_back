package com.bapseguen.app.orders;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.cartList.dao.CartListDAO;
import com.bapseguen.app.dto.CartItemDTO;
import com.bapseguen.app.dto.OrderItemDTO;
import com.bapseguen.app.dto.OrdersDTO;
import com.bapseguen.app.orders.dao.OrdersDAO;

public class PaymentApproveOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer memberNumber = (Integer) req.getSession().getAttribute("memberNumber");
		if (memberNumber == null) {
			Result r = new Result();
			r.setRedirect(true);
			r.setPath(req.getContextPath() + "/login/login.lo");
			return r;
		}

		String paymentKey = req.getParameter("paymentKey");
		String orderId = req.getParameter("orderId");
		String amountStr = req.getParameter("amount");

		int amount = Integer.parseInt(amountStr);

		// Toss 문서용 시크릿키로 결제 승인 확인
		TossService toss = new TossService();
		boolean approved = toss.confirm(paymentKey, orderId, amount, TossService.DOCS_SECRET_KEY);
		if (!approved) {
			Result r = new Result();
			r.setRedirect(true);
			r.setPath(req.getContextPath() + "/orders/paymentFail.or?reason=confirm");
			return r;
		}

		// 장바구니 상품 가져오기
		CartListDAO cdao = new CartListDAO();
		List<CartItemDTO> items = cdao.selectCurrentCartItemsWithPrice(memberNumber);

		if (items == null || items.isEmpty()) {
			Result r = new Result();
			r.setRedirect(true);
			r.setPath(req.getContextPath() + "/cartList/view.cl");
			return r;
		}

		// 주문 INSERT
		OrdersDAO odao = new OrdersDAO();
		OrdersDTO order = new OrdersDTO();
		order.setOrdersMemberNumber(memberNumber);
		order.setOrderId(orderId);
		order.setOrdersTotalAmount(amount);
		order.setOrdersPaymentStatus("PAID");
		order.setOrdersPaymentInfo("CARD:TOSS-APPROVED");
		order.setBusinessNumber(cdao.selectCartBusinessNumberByCartNumber(items.get(0).getCartNumber()));

		int ordersNumber = odao.insertOrder(order);

		// 주문상품 INSERT
		for (CartItemDTO it : items) {
			OrderItemDTO oi = new OrderItemDTO();
			oi.setOrdersNumber(ordersNumber);
			oi.setItemNumber(it.getItemNumber());
			oi.setOrderItemUnitPrice(it.getCartItemPrice());
			oi.setOrderItemQuantity(it.getCartItemQuantity());
			odao.insertOrderItem(oi);
		}

		// 장바구니 마감
		cdao.closeCurrentCart(memberNumber);

		// 성공 페이지로 이동
		Result r = new Result();
		r.setRedirect(false);
		req.setAttribute("orderId", orderId); // 주문번호 전달
		req.setAttribute("amount", amount); // 결제금액 전달
		r.setPath("/app/cartList/paymentSuccess.jsp");
		return r;

	}
}
