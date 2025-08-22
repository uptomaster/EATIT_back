package com.bapseguen.app.sellerMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.ItemDTO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class FoodAddOkController implements Execute{
	@Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Result result = new Result();
        ItemDTO itemDTO = new ItemDTO();
        
        itemDTO.setBusinessNumber(request.getParameter("businessNumber"));
        itemDTO.setItemName(request.getParameter("itemName"));
        itemDTO.setItemPrice(Integer.parseInt(request.getParameter("itemPrice")));
        itemDTO.setItemContent(request.getParameter("itemContent"));
        itemDTO.setItemQuantity(Integer.parseInt(request.getParameter("itemQuantity")));
        itemDTO.setItemOrigin(request.getParameter("itemOrigin"));
        itemDTO.setItemExpireDate(request.getParameter("itemExpireDate"));

//        new StoreManageDAO().addFood(itemDTO);
        new SellerMyPageDAO().addFood(itemDTO);
       result.setRedirect(true);
        result.setPath(request.getContextPath() + "/seller/food/listOk.se?businessNumber=" + itemDTO.getBusinessNumber());
        return result;
    }

}
