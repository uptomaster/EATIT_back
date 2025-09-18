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

        // 1) 로그인 체크
        Integer memberNumber = (Integer) session.getAttribute("memberNumber");
        if (memberNumber == null) {
            result.setPath(request.getContextPath() + "/login/login.lo");
            result.setRedirect(true);
            return result;
        }

        // 2) 현재 회원의 OPEN 장바구니 조회
        CartDTO cartDTO = new CartDTO();
        cartDTO.setMemberNumber(memberNumber);
        Integer myCartNumber = cartDAO.selectOpenCartNumberByMember(cartDTO);

        if (myCartNumber == null) {
            // 장바구니가 없음
            session.setAttribute("cartError", "비울 장바구니가 없습니다.");
            result.setPath(request.getContextPath() + "/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }

        // 3) 장바구니 아이템 전체 삭제
        CartDTO dto = new CartDTO();
        dto.setCartNumber(myCartNumber);
        cartDAO.deleteCartAllItems(dto);

        // 4) 완료 처리
        session.setAttribute("cartNotice", "장바구니를 비웠습니다.");
        result.setPath(request.getContextPath() + "/cartList/view.cl");
        result.setRedirect(true);

        return result;
    }
}
