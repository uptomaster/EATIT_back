package com.bapseguen.app.sellerMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class SellerWithdrawOkController implements Execute{
	 @Override
	    public Result execute(HttpServletRequest request, HttpServletResponse response)
	            throws IOException, ServletException {

	    	System.out.println("==== SellerWithdrawOkController 실행====");
	    	
	    	 Result result = new Result();
	         HttpSession session = request.getSession(false);
	         Integer memberNumber = (session != null) ? (Integer) session.getAttribute("memberNumber") : null;
	         String businessNumber = (session != null) ? (String) session.getAttribute("businessNumber") : null;

	         if (memberNumber == null) {
	             result.setRedirect(true);
	             result.setPath(request.getContextPath() + "/login/login.lo?login=required");
	             return result;
	         }

	         String password = request.getParameter("password");
	         String agree    = request.getParameter("agree");

	         // 1) 라디오 동의 체크
	         if (agree == null || !"yes".equals(agree)) {
	             request.setAttribute("agreeError", "회원탈퇴에 동의해 주세요.");
	             result.setRedirect(false);
	             result.setPath("/app/sellerMyPage/sellerwithdrawalAgreement.jsp");
	             return result;
	         }

	         // 2) 비밀번호 확인
	         SellerMyPageDAO sellerdao = new SellerMyPageDAO();
	         if (password == null || password.isEmpty() || !sellerdao.checkPassword(memberNumber, password)) {
	             request.setAttribute("pwError", "비밀번호가 일치하지 않습니다.");
	             result.setRedirect(false);
	             result.setPath("/app/sellerMyPage/sellerwithdrawalAgreement.jsp");
	             return result;
	         }

	         // 3) 탈퇴 처리 (게시글, 댓글, 리뷰 삭제 후 회원 삭제)
	         sellerdao.withdraw(memberNumber,businessNumber);

	         // 4) 세션 종료 & 완료 페이지 리다이렉트
	         if (session != null) session.invalidate();

	         result.setRedirect(true);
	         result.setPath(request.getContextPath() + "/app/sellerMyPage/sellerGoodBye.jsp?withdraw=1");
	         return result;
	    }
}
