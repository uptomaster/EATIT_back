package com.bapseguen.app.cartList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.cartList.dao.CartListDAO;
import com.bapseguen.app.dto.CartItemDTO;

public class CartListDeleteItemOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Result result = new Result();
        CartListDAO cartListDAO = new CartListDAO();

        int cartItemNumber = Integer.parseInt(request.getParameter("cartItemNumber"));

        CartItemDTO dto = new CartItemDTO();
        dto.setCartItemNumber(cartItemNumber);

        cartListDAO.deleteCartItem(dto);

        result.setPath("/cartList/viewOk.cl");
        result.setRedirect(true);

        return result;
    }
}
