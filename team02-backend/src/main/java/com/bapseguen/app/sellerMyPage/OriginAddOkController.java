package com.bapseguen.app.sellerMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.OriginDTO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class OriginAddOkController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		OriginDTO originDTO = new OriginDTO();
		Result result = new Result();		
		
		originDTO.setOriginItem(request.getParameter("originItem"));
		originDTO.setOriginMenu(request.getParameter("originMenu"));
		originDTO.setOriginLocation(request.getParameter("originLocation"));
		originDTO.setBusinessNumber(request.getParameter("businessNumber"));

		new SellerMyPageDAO().addOrigin(originDTO);

		result.setRedirect(true);
		result.setPath(request.getContextPath() + "/seller/origin/listOk.se?businessNumber=" + originDTO.getBusinessNumber());
		return result;
	}
}