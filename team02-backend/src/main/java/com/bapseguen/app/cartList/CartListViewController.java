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
        CartListDAO cartListDAO = new CartListDAO();
        CartDTO cartDTO = new CartDTO();

        // 세션에서 회원 번호 가져오기
        int memberNumber = (int) request.getSession().getAttribute("memberNumber");
        cartDTO.setMemberNumber(memberNumber);

        // 장바구니 아이템 불러오기
        List<CartItemDTO> cartItems = cartListDAO.selectCartItems(cartDTO);

        // JSP에 전달
        request.setAttribute("cartItems", cartItems);

        result.setPath("/app/cartList/shoppingList.jsp"); // forward
        result.setRedirect(false);

        return result;
    }
}
