package com.bapseguen.app.sellerMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.ItemDTO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class AlreadyFoodOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ItemDTO dto = new ItemDTO();
        SellerMyPageDAO dao = new SellerMyPageDAO();
        Result result = new Result();
        String path = null;
        
        
        System.out.println("AlreadyFoodOkController 진입 성공");
        dto.setItemName(request.getParameter("itemName"));
        dto.setBusinessNumber(request.getParameter("businessNumber"));

        int count = dao.alreadyFood(dto);
//        request.setAttribute("alreadyFoodCount", count);

        result.setRedirect(false);
        result.setPath("/app/sellerMyPage/storeInfo.jsp");
        return result;
    }
}