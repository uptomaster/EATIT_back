package com.bapseguen.app.sellerMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.view.SellerInfoDTO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class EditSellerInfoController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
    	System.out.println("====EditSellerInfoController 실행====");

		Result result = new Result();
		SellerMyPageDAO sellerDAO = new SellerMyPageDAO();

		// 로그인된 사용자 번호 가져오기 (세션)
		Integer memberNumber = (Integer) request.getSession().getAttribute("memberNumber");

		if (memberNumber == null) {
			result.setPath("/login/login.lo"); // 로그인 안된 경우 로그인 페이지로
			result.setRedirect(true);
			return result;
		}

		// 내 정보 조회
		SellerInfoDTO sellerInfo = sellerDAO.selectSellerInfo(memberNumber);
		request.setAttribute("sellerInfo", sellerInfo);

		// JSP로 포워딩
		result.setPath("/app/sellerMyPage/editSellerInfo.jsp");
		result.setRedirect(false);
		return result;
	}

}
