package com.bapseguen.app.cartList;

import java.io.IOException;
import java.util.Collections;
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

        // 1) 로그인 체크
        Integer memberNumber = (Integer) request.getSession().getAttribute("memberNumber");
        if (memberNumber == null) {
            result.setPath(request.getContextPath() + "/login/login.lo");
            result.setRedirect(true);
            return result;
        }

        CartListDAO dao = new CartListDAO();

        // 2) OPEN 카트번호 조회 (새로 만들지 말고 "있으면" 가져오기)
        CartDTO cart = new CartDTO();
        cart.setMemberNumber(memberNumber);
        Integer cartNumber = dao.selectOpenCartNumberByMember(cart);

        // 3) 모델 기본값
        List<CartItemDTO> items = Collections.emptyList();
        long totalAmount = 0L;

        if (cartNumber != null) {
            // ★ cartNumber 세팅 필수
            cart.setCartNumber(cartNumber);

            // 방법 A) DTO 기반 조회 (매퍼 WHERE가 CART_NUMBER 사용해야 함)
            items = dao.selectCartItems(cart);

            // 방법 B) cartNumber로 직접 조회 (실수를 줄이고 싶으면 이걸 사용)
            // items = dao.selectCartItemsByCartNo(cartNumber);

            if (items != null) {
                for (CartItemDTO it : items) {
                    long price = (long) it.getCartItemPrice();
                    totalAmount += price * it.getCartItemQuantity();
                }
            }
        }

        // 4) 모델 주입
        request.setAttribute("cartNumber", cartNumber);
        request.setAttribute("items", items);
        request.setAttribute("totalAmount", totalAmount);

        // 5) forward: contextPath 붙이지 않음
        result.setPath("/app/cartList/shoppingList.jsp");
        result.setRedirect(false);
        return result;
    }
}
