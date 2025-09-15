package com.bapseguen.app.sellerMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class OriginDetailController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        int originNumber = Integer.parseInt(request.getParameter("originNumber"));

        HttpSession session = request.getSession(false);
        String businessNumber = (session != null) ? (String) session.getAttribute("businessNumber") : null;

        SellerMyPageDAO dao = new SellerMyPageDAO();
        request.setAttribute("originList", dao.originList(businessNumber));
        request.setAttribute("editOrigin", dao.selectOriginOne(originNumber));
        request.setAttribute("openEditModal", true);

        Result result = new Result();
        result.setPath("/app/sellerMyPage/originList.jsp"); // forward
        return result;
        
	}
	
}
