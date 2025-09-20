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
		Result result = new Result();
		String path = null;
		HttpSession session = request.getSession(false);
	
		// 세션값이 없을떄 에러 메시지 출력하고 로그인 창으로 이동
		if(session == null) {
			System.out.println("로그아웃 상태에서 마이페이지 접근");
			response.sendRedirect("/login/login.lo");
			return null;
		}
		//세션 값이 있을때 
		//세션에 저장된 memberNumber와 비밀번호를 이용하여 비밀번호 확인
			//세션에서 memberNumber 가져오기
		Integer memberNumber = (int) session.getAttribute("memberNumber");
		System.out.println("세션의 회원번호 : "+memberNumber);
		// 입력한 비밀번호 저장
		String inputPw = request.getParameter("sellerPw");
		System.out.println("pw: "+inputPw);
		//모든 정보를 가져오기 	
		
		String business = sellerDAO.getBusinessNumber(memberNumber);
		 session.setAttribute("businessNumber",business);
		System.out.println("사업자번호: "+business);
		
        boolean ok = false;
        
        if (memberNumber != null && inputPw != null && !inputPw.isEmpty()) {
	        UserMyPageDAO dao = new UserMyPageDAO();
	        ok = dao.checkPassword(memberNumber, inputPw); // Mapper: myPage.checkPassword 사용
	        // ↳ TBL_MEMBER에서 MEMBER_NUMBER+PASSWORD 일치 COUNT(1)로 확인. 
	      }
	
	      if (ok) {
	        // ★ 성공: 반드시 .se 경로로 redirect
	        response.sendRedirect(request.getContextPath() + "/sellerMyPage/storeInfo.se");
	      } else {
	        // ★ 실패: 다시 입력 페이지로 forward + 에러 메시지 전달
	        request.setAttribute("errorMsg", "비밀번호가 일치하지 않습니다.");
	        request.getRequestDispatcher("/app/sellerMyPage/sellerCheckPw.jsp").forward(request, response);
	      }
        
        return null; // JSON 출력했으니 Result는 필요 없음
	}
}
