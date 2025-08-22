package com.bapseguen.app.sellerMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.ItemDTO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class FoodEditOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        ItemDTO dto = new ItemDTO();
        SellerMyPageDAO dao = new SellerMyPageDAO();
        Result result = new Result();
        String path = null;
        
        dto.setItemNumber(Integer.parseInt(request.getParameter("itemNumber")));
        dto.setItemName(request.getParameter("itemName"));
        dto.setItemPrice(request.getParameter("itemPrice"));
        dto.setItemContent(request.getParameter("itemContent"));
        dto.setItemQuantity(Integer.parseInt(request.getParameter("itemQuantity")));
        dto.setItemOrigin(request.getParameter("itemOrigin"));

        new SellerMyPageDAO().editFood(dto);

        result.setRedirect(true);
        result.setPath(request.getContextPath() + "/seller/food/detailOk.se?itemNumber=" + dto.getItemNumber());
        return result;
    }
}