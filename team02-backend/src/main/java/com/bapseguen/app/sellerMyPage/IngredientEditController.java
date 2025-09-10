package com.bapseguen.app.sellerMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.ItemImageDTO;
import com.bapseguen.app.dto.ItemListDTO;
import com.bapseguen.app.dto.view.ItemInsertDTO;
import com.bapseguen.app.img.dao.ItemImageDAO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class IngredientEditController implements Execute{
	

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("=== IngredientEditController 진입 성공 ===");
		
		// 데리고 들어와야할 값들 확인하기
		int itemNumber = Integer.valueOf(request.getParameter("itemNumber"));
		Integer memberNumber = (Integer)request.getSession().getAttribute("memberNumber");
		String businessNumber = (String)request.getSession().getAttribute("businessNumber");
		System.out.println("데려온 값> memberNumber : "+memberNumber+" ,businessNumber : "+businessNumber+" ,itemNumber : "+itemNumber);
		
		//DAO DTO 세팅
		Result result = new Result();
		SellerMyPageDAO sellerDAO = new SellerMyPageDAO();   
		ItemImageDAO ItemImageDAO = new ItemImageDAO();   
	
		
        request.setAttribute("item", sellerDAO.detailItem(itemNumber));
        request.setAttribute("itemImage", ItemImageDAO.select(itemNumber));
        

        
        result.setPath("/app/sellerMyPage/editIngreOk.se");
        result.setRedirect(false);
        return result;

	}
	
	
}
