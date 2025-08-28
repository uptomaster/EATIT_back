package com.bapseguen.app.orders;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.cartList.dao.CartListDAO;
import com.bapseguen.app.dto.CartItemDTO;
import com.bapseguen.app.dto.view.ItemSnapshotDTO;
import com.bapseguen.app.item.dao.ItemDAO;
import com.bapseguen.app.orders.dao.OrdersDAO;

public class PaymentApproveOkController implements Execute {

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

		// 2) 파라미터 (※ 반드시 getParameter 로 읽어야 함: redirect로 넘어옴)
		String paymentKey = pick(req, "paymentKey", "payment_key");
		String orderId = pick(req, "orderId", "ordersId", "order_id");
		String amountStr = pick(req, "amount", "totalAmount");
		if (paymentKey == null || orderId == null || amountStr == null) {
			return redirect(req, "/cartList/view.cl");
		}

		int amount;
		try {
			amount = Integer.parseInt(amountStr);
		} catch (NumberFormatException e) {
			return redirect(req, "/cartList/view.cl");
		}

		// 3) 서버에서 금액 재계산 (장바구니 기준)
		CartListDAO cdao = new CartListDAO();
		List<CartItemDTO> items = cdao.selectCurrentCartItemsWithPrice(memberNumber);
		long serverTotal = 0L;
		if (items != null) {
			for (CartItemDTO it : items) {
				serverTotal += (long) it.getCartItemPrice() * it.getCartItemQuantity();
			}
		}
		if (serverTotal <= 0) {
			// 장바구니가 비어있으면 실패 처리
			return redirect(req, "/cartList/view.cl");
		}
		if (serverTotal != amount) {
			// 금액 불일치 → 보안상 결제 취소(혹은 실패 페이지로)
			String q = "reason=" + URLEncoder.encode("amount_mismatch", StandardCharsets.UTF_8.name());
			return redirect(req, "/orders/paymentCancelOk.or?" + q);
		}

		// --- 스냅샷 검증 ---
		ItemDAO idao = new ItemDAO();
		boolean valid = true;

		for (CartItemDTO cartItem : items) {
			ItemSnapshotDTO snapshot = idao.selectSnapshot(cartItem.getItemNumber());

			if (snapshot == null || snapshot.getItemPrice() != cartItem.getCartItemPrice()
					|| snapshot.getItemQuantity() < cartItem.getCartItemQuantity()
					|| !"Y".equals(snapshot.getItemSellState())) {

				valid = false;
				break;
			}
		}

		// 4) 결제 승인(Confirm)
		String secretKey = req.getServletContext().getInitParameter("TOSS_SECRET_KEY"); // web.xml의 gsk
		TossService toss = new TossService();
		boolean approved = toss.confirm(paymentKey, orderId, amount, secretKey);
		if (!approved) {
			return redirect(req, "/orders/paymentCancelOk.or?reason=confirm");
		}

		// 5) 주문 상태/금액 업데이트 (READY → PAID), 카트 마감
		OrdersDAO odao = new OrdersDAO();
		try {
			// orderId 기준으로 PAID 업데이트 (아래 2) 매퍼 추가)
			odao.updatePaidByOrderId(orderId, amount);

			// 현재 OPEN 카트 CLOSE
			cdao.closeCurrentCart(memberNumber);

			// 성공 페이지로 forward
			Result r = new Result();
			r.setRedirect(false);
			r.setPath("/app/orders/success.jsp");
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			return redirect(req, "/orders/paymentCancelOk.or?reason=finalize");
		} finally {
			try {
				odao.close();
			} catch (Exception ignore) {
			}
		}
	}

	private static String pick(HttpServletRequest req, String... names) {
		for (String n : names) {
			String v = req.getParameter(n);
			if (v != null && !v.isBlank())
				return v;
		}
		return null;
	}

	private Result redirect(HttpServletRequest req, String path) {
		Result r = new Result();
		r.setRedirect(true);
		r.setPath(req.getContextPath() + path);
		return r;
	}
}
