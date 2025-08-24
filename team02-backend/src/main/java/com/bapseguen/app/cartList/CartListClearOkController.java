package com.bapseguen.app.cartList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.cartList.dao.CartListDAO;
import com.bapseguen.app.dto.CartDTO;

public class CartListClearOkController implements Execute {

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

        // 2. cartNumber 파라미터 파싱
        int cartNumber;
        try {
            cartNumber = Integer.parseInt(request.getParameter("cartNumber"));
        } catch (NumberFormatException e) {
            session.setAttribute("cartError", "잘못된 장바구니 번호입니다.");
            result.setPath(request.getContextPath() + "/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }

        // 3. 해당 카트가 내 카트인지 검증
        String cartBN = cartDAO.selectCartBusinessNumberByCartNumber(cartNumber);
        Integer myCartNumber = cartDAO.selectOpenCartNumberByMember(new CartDTO() {{
            setMemberNumber(memberNumber);
        }});

        if (myCartNumber == null || !myCartNumber.equals(cartNumber)) {
            session.setAttribute("cartError", "본인 장바구니만 비울 수 있습니다.");
            result.setPath(request.getContextPath() + "/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }

        // 4. 전체 아이템 삭제
        CartDTO dto = new CartDTO();
        dto.setCartNumber(cartNumber);
        cartDAO.deleteCartAllItems(dto);

        // 5. 완료 처리
        session.setAttribute("cartNotice", "장바구니를 비웠습니다.");
        result.setPath(request.getContextPath() + "/cartList/view.cl");
        result.setRedirect(true);

        return result;
    }
}
