package com.bapseguen.app.cartList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.cartList.dao.CartListDAO;
import com.bapseguen.app.dto.CartDTO;
import com.bapseguen.app.dto.CartItemDTO;

public class CartListAddItemOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Result result = new Result();
        CartListDAO cartListDAO = new CartListDAO();

        int memberNumber = (int) request.getSession().getAttribute("memberNumber");
        String businessNumber = request.getParameter("businessNumber");
        int itemNumber = Integer.parseInt(request.getParameter("itemNumber"));
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // 1. 카트 없으면 생성
        CartDTO cartDTO = new CartDTO();
        cartDTO.setMemberNumber(memberNumber);
        cartDTO.setBusinessNumber(businessNumber);
        cartListDAO.insertCartIfNotExists(cartDTO);

        // 2. 회원의 OPEN 카트 번호 가져오기
        Integer cartNumber = cartListDAO.selectOpenCartNumberByMember(cartDTO);

        // 3. 카트 아이템 추가
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setCartNumber(cartNumber);
        cartItemDTO.setItemNumber(itemNumber);
        cartItemDTO.setCartItemPrice(price);
        cartItemDTO.setCartItemQuantity(quantity);

        cartListDAO.insertCartItem(cartItemDTO);

        // 완료 후 장바구니 페이지로 이동
        result.setPath("/cartList/viewOk.cl");
        result.setRedirect(true);

        return result;
    }
}
