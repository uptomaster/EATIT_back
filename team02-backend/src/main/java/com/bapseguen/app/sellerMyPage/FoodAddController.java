package com.bapseguen.app.sellerMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class FoodAddController implements Execute{
	
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("FoodAddController 진입 성공");
		
		SellerMyPageDAO dao = new SellerMyPageDAO();
		Result result = new Result();
		String path = null;
		HttpSession session = request.getSession();
		
	    // 1. 세션 속성 null 체크
	    Integer memberNumber = (Integer)session.getAttribute("memberNumber");
	    if (memberNumber == null) {
	        System.out.println("경고: 세션에 memberNumber 속성이 없음");
	        result.setPath("/app/login/login.jsp");
	        result.setRedirect(true);
	        return result;
	    }
	    
	    // 2. path 변수 초기화
	    path = "/sellerMyPage/addFoodOk.se";  // 기본값 설정

		
		result.setPath(path);
		result.setRedirect(false);
		
		System.out.println(result);
		return result;	
		}
	

}
