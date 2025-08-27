package com.bapseguen.app.sellerMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class FoodEditController implements Execute{
	

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

        int itemNumber = Integer.valueOf(request.getParameter("itemNumber"));
        SellerMyPageDAO sellerDAO = new SellerMyPageDAO();
        Result result = new Result();
               
        request.setAttribute("itemNumber", sellerDAO.detaileFood(itemNumber));
        

        
        result.setPath("/app/sellerMyPage/foodSalesEdit.jsp");
        result.setRedirect(false);
        return result;
//        String raw = request.getParameter("itemNumber");
        
//        if (raw == null || raw.isBlank()) {
//        	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "missing param: itemNumber");
//        	return null;
//        }
//        
//        int itemNumber;
//        try {
//        	itemNumber = Integer.parseInt(raw.trim());
//        } catch (NumberFormatException e) {
//        	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "invalid int: itemNumber=" + raw);
//        	return null;
//        }
	}
	
	
}
