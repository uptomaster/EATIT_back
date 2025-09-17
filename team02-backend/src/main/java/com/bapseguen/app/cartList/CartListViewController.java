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
import com.bapseguen.app.dto.view.ItemWithImgDTO;

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

        // 2) 현재 회원의 OPEN 카트번호(있으면) 조회
        CartDTO cart = new CartDTO();
        cart.setMemberNumber(memberNumber);
        Integer cartNumber = dao.selectOpenCartNumberByMember(cart); // null일 수 있음

        // 3) 상세 목록 조회 (이름/이미지/가격/수량 포함)
        List<CartItemDTO> items = dao.selectCurrentCartItemsWithPrice(memberNumber);
        if (items == null) {
            items = Collections.emptyList();
        }

        // 4) 합계 계산
        long totalAmount = 0L;
        for (CartItemDTO it : items) {
            long price = it.getCartItemPrice();
            totalAmount += price * it.getCartItemQuantity();
        }

        // 5) 추천 상품 (같은 가게 내 다른 메뉴)
        List<ItemWithImgDTO> recommendedItems = Collections.emptyList();
        if (!items.isEmpty()) {
            int firstItemNumber = items.get(0).getItemNumber();
            String businessNumber = dao.findBusinessNumberByItem(firstItemNumber);

            if (businessNumber != null) {
                recommendedItems = dao.selectRecommendedItems(memberNumber, businessNumber);
            }
        }

        // 6) 모델 주입
        request.setAttribute("cartNumber", cartNumber);
        request.setAttribute("items", items);
        request.setAttribute("totalAmount", totalAmount);
        request.setAttribute("recommendedItems", recommendedItems);

        // 7) forward
        result.setPath("/app/cartList/shoppingList.jsp");
        result.setRedirect(false);
        return result;
    }
}
