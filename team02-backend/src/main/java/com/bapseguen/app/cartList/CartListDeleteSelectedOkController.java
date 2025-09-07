package com.bapseguen.app.cartList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.cartList.dao.CartListDAO;
import com.bapseguen.app.dto.CartItemDTO;

public class CartListDeleteSelectedOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Result result = new Result();
        HttpSession session = request.getSession();

        // 로그인 체크
        Integer memberNumber = (Integer) session.getAttribute("memberNumber");
        if (memberNumber == null) {
            result.setPath(request.getContextPath() + "/login/login.lo");
            result.setRedirect(true);
            return result;
        }

        // 선택된 항목 번호 가져오기
        String cartItemNumbers = request.getParameter("cartItemNumbers"); // "3,5,7"
        if (cartItemNumbers == null || cartItemNumbers.isBlank()) {
            session.setAttribute("cartError", "선택된 상품이 없습니다.");
            result.setPath(request.getContextPath() + "/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }

        CartListDAO cartDAO = new CartListDAO();
        String[] ids = cartItemNumbers.split(",");
        for (String id : ids) {
            try {
                int cartItemNumber = Integer.parseInt(id.trim());
                CartItemDTO dto = new CartItemDTO();
                dto.setCartItemNumber(cartItemNumber);
                cartDAO.deleteCartItem(dto);
            } catch (NumberFormatException e) {
                System.out.println("[WARN] 잘못된 cartItemNumber: " + id);
            }
        }

        session.setAttribute("cartNotice", "선택한 상품을 장바구니에서 삭제했습니다.");
        result.setPath(request.getContextPath() + "/cartList/view.cl");
        result.setRedirect(true);

        return result;
    }
}
