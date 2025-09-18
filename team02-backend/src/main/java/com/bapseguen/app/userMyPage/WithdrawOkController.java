package com.bapseguen.app.userMyPage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.userMyPage.dao.UserMyPageDAO;

public class WithdrawOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

    	System.out.println("====WithdrawOkController 실행====");
    	
    	 Result result = new Result();
         HttpSession session = request.getSession(false);
         Integer memberNumber = (session != null) ? (Integer) session.getAttribute("memberNumber") : null;

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
             result.setPath("/app/userMyPage/withdrawalAgreement.jsp");
             return result;
         }

         // 2) 비밀번호 확인
         UserMyPageDAO userMyPagedao = new UserMyPageDAO();
         if (password == null || password.isEmpty() || !userMyPagedao.checkPassword(memberNumber, password)) {
             request.setAttribute("pwError", "비밀번호가 일치하지 않습니다.");
             result.setRedirect(false);
             result.setPath("/app/userMyPage/withdrawalAgreement.jsp");
             return result;
         }

         // 3) 탈퇴 처리 (게시글, 댓글, 리뷰 삭제 후 회원 삭제)
         userMyPagedao.withdrawMember(memberNumber);

         // 4) 세션 종료 & 완료 페이지 리다이렉트
         if (session != null) session.invalidate();

         result.setRedirect(true);
         result.setPath(request.getContextPath() + "/app/userMyPage/userGoodBye.jsp?withdraw=1");
         return result;
    }
}