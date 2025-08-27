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
        UserMyPageDAO dao = new UserMyPageDAO();
        if (password == null || password.isEmpty() || !dao.checkPassword(memberNumber, password)) {
            request.setAttribute("pwError", "비밀번호가 일치하지 않습니다.");
            result.setRedirect(false);
            result.setPath("/app/userMyPage/withdrawalAgreement.jsp");
            return result;
        }

        // 3) 탈퇴 처리
        
        dao.delete(memberNumber); // withDraw.withDrawDelete 호출됨(이미 DAO에 존재)

        // 4) 세션 종료 & 리다이렉트
        if (session != null) session.invalidate();

        result.setRedirect(true);
        result.setPath(request.getContextPath() + "/?withdraw=1");
        return result;
    }
}