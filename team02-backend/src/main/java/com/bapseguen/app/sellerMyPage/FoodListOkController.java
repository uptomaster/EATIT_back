package com.bapseguen.app.sellerMyPage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.view.ItemWithImgDTO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class FoodListOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	ItemWithImgDTO dto = new ItemWithImgDTO();
    	SellerMyPageDAO dao = new SellerMyPageDAO();
    	Result result = new Result();
    	String path = null;

        String businessNumber = request.getParameter("businessNumber");
        List<Map<String, Object>> list = dao.foodList(businessNumber);

        request.setAttribute("foodList", list);

        result.setRedirect(false);
        result.setPath("/app/Seller/foodList.jsp");
        return result;
    }
}