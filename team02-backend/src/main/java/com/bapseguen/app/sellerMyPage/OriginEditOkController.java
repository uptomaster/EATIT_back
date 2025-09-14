package com.bapseguen.app.sellerMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.OriginDTO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class OriginEditOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

        OriginDTO dto = new OriginDTO();
        dto.setOriginNumber(Integer.parseInt(request.getParameter("originNumber")));
        dto.setOriginItem(request.getParameter("originItem"));
        dto.setOriginMenu(request.getParameter("originMenu"));
        dto.setOriginLocation(request.getParameter("originLocation"));

        new SellerMyPageDAO().updateOriginByNumber(dto);

        Result result = new Result();
        result.setPath(request.getContextPath() + "/sellerMyPage/originList.se"); // redirect 목록
        result.setRedirect(true);
        return result;
	}
	
}
