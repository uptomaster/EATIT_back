package com.bapseguen.app.cartList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.cartList.dao.CartListDAO;
import com.bapseguen.app.dto.CartItemDTO;

public class CartListDeleteItemOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Result result = new Result();
        HttpSession session = request.getSession();
        CartListDAO cartDAO = new CartListDAO();

        // 1. 로그인 체크
        Integer memberNumber = (Integer) session.getAttribute("memberNumber");
        if (memberNumber == null) {
            result.setPath(request.getContextPath() + "/member/login.me");
            result.setRedirect(true);
            return result;
        }

        // 2. cartItemNumber 파라미터 검증
        int cartItemNumber;
        try {
            cartItemNumber = Integer.parseInt(request.getParameter("cartItemNumber"));
        } catch (NumberFormatException e) {
            session.setAttribute("cartError", "잘못된 장바구니 항목 번호입니다.");
            result.setPath(request.getContextPath() + "/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }

        // 3. 내 카트의 항목인지 확인 로직이 있으면 더 안전 (DAO에 selectOwner 추가 가능)
        // 지금은 단순 삭제만 처리
        CartItemDTO dto = new CartItemDTO();
        dto.setCartItemNumber(cartItemNumber);

        cartDAO.deleteCartItem(dto);

        // 4. 완료 메시지 후 장바구니 화면 이동
        session.setAttribute("cartNotice", "상품을 장바구니에서 삭제했습니다.");
        result.setPath(request.getContextPath() + "/cartList/view.cl");
        result.setRedirect(true);

        return result;
    }
}
