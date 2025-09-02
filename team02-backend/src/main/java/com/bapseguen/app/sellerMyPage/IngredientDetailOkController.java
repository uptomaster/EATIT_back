package com.bapseguen.app.sellerMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.ItemDTO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class IngredientDetailOkController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ItemDTO itemDTO = new ItemDTO();
		SellerMyPageDAO dao = new SellerMyPageDAO();
		Result result = new Result();

		return result;
	}
}