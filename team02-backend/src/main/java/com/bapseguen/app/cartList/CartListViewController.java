package com.bapseguen.app.cartList;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.cartList.dao.CartListDAO;
import com.bapseguen.app.dto.CartDTO;
import com.bapseguen.app.dto.CartItemDTO;

public class CartListViewController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Result result = new Result();

		// 로그인 체크
		Integer memberNumber = (Integer) request.getSession().getAttribute("memberNumber");
		if (memberNumber == null) {
			result.setPath(request.getContextPath() + "/member/login.me");
			return result;
		}

		CartListDAO dao = new CartListDAO();
		CartDTO cart = new CartDTO();
		// 세션에서 가져온 회원번호
		cart.setMemberNumber(memberNumber);

		// 회원이 장바구니가 없으면 장바구니 번호 확보하기
		// public Integer ensureOpenCart(CartDTO dto)
		Integer cartNumber = dao.ensureOpenCart(cart);

		// 전체 금액 계산(클릭할때 증가시키기)
		// totalAmount는 금액이 커질 수 있으니 long 사용
		long totalAmount = 0L;
		List<CartItemDTO> items = null;

		if (cartNumber != null) {
			// 목록 조회
			items = dao.selectCartItems(cart);
			if (items != null) {
				for (CartItemDTO it : items) {
					long price = 0L;
					try {
						price = (long) it.getCartItemPrice();
					} catch (Throwable ignore) {
					}
					totalAmount += price * it.getCartItemQuantity();
				}
			}
		}

		request.setAttribute("cartNumber", cartNumber);
		request.setAttribute("items", items);
		request.setAttribute("totalAmount", totalAmount);

		// 장바구니 포워드하기
		result.setPath(request.getContextPath() + "/app/cartList/shoppingList.jsp");
		result.setRedirect(false);

		return result;
	}
}
