package com.bapseguen.app.sellerMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.view.SellerInfoDTO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;
import com.bapseguen.app.userMyPage.dao.UserMyPageDAO;

public class CheckSellerPwOkController implements Execute{
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		SellerInfoDTO sellerDTO = new SellerInfoDTO();
		SellerMyPageDAO sellerDAO = new SellerMyPageDAO();
		int memberNumber = 0;
		Result result = new Result();
		String path = null;
		HttpSession session = request.getSession(false);
	
		// 세션값이 없을떄 에러 메시지 출력하고 로그인 창으로 이동
		if(session == null) {
			System.out.println("로그아웃 상태에서 마이페이지 접근");
			response.sendRedirect("/app/sellerMyPage/sellerCheckPw.jsp");
			return null;
		}
		//세션 값이 있을때 
		//세션에 저장된 memberNumber와 비밀번호를 이용하여 비밀번호 확인
			//세션에서 memberNumber 가져오기
		memberNumber = (int) session.getAttribute("memberNumber");
		System.out.println("세션의 회원번호 : "+memberNumber);
		// 입력한 비밀번호 저장
		String pw = request.getParameter("sellerPw");
		System.out.println("pw: "+pw);
		//모든 정보를 가져오기 	
		
		String business = sellerDAO.getBusinessNumber(memberNumber);
		 session.setAttribute("businessNumber",business);
		System.out.println("사업자번호: "+business);
		
	
		 result.setPath("/sellerMyPage/storeInfo.se"); 
		 result.setRedirect(true);
		 return result;
//		 
//		 Integer memberNumber = (session != null) ? (Integer) session.getAttribute("memberNumber") : null;
//        String inputPw = request.getParameter("currentPassword");
//
//        boolean ok = false;
//        if(memberNumber != null && inputPw != null){
//            UserMyPageDAO userMyPageDAO = new UserMyPageDAO();
//            ok = userMyPageDAO.checkPassword(memberNumber, inputPw);
//        }
//
//        // JSON 직접 출력
//        response.setContentType("application/json; charset=UTF-8");
//        response.getWriter().write("{\"result\":\"" + (ok ? "success" : "fail") + "\"}");
//        return null; // JSON 출력했으니 Result는 필요 없음
	}
}
