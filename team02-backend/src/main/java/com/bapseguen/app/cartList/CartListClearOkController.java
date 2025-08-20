package com.bapseguen.app.cartList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.cartList.dao.CartListDAO;
import com.bapseguen.app.dto.CartDTO;

// 장바구니 전체 삭제 시 OK컨트롤러
public class CartListClearOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Result result = new Result();
        CartListDAO cartListDAO = new CartListDAO();

        int cartNumber = Integer.parseInt(request.getParameter("cartNumber"));

        CartDTO dto = new CartDTO();
        dto.setCartNumber(cartNumber);

        cartListDAO.deleteCartAllItems(dto);

        result.setPath("/cartList/viewOk.cl");
        result.setRedirect(true);

        return result;
    }
}
