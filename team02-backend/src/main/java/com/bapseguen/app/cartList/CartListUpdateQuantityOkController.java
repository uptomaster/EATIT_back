package com.bapseguen.app.cartList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.cartList.dao.CartListDAO;

public class CartListUpdateQuantityOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer memberNumber = (Integer) req.getSession().getAttribute("memberNumber");
        if (memberNumber == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null; // 로그인 안 된 경우
        }

        int itemNumber = Integer.parseInt(req.getParameter("itemNumber"));
        int quantity   = Integer.parseInt(req.getParameter("quantity"));

        CartListDAO dao = new CartListDAO();
        dao.updateQuantity(memberNumber, itemNumber, quantity);

        // Ajax 응답이므로 JSP forward 대신 JSON 리턴
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write("{\"status\":\"ok\"}");
        return null;
    }
}
