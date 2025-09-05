package com.bapseguen.app.userMyPage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.userMyPage.dao.UserMyPageDAO;

public class GeneralCheckPwOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        Result result = new Result();
        HttpSession session = request.getSession(false);

        // 1) 로그인 세션 체크
        Integer memberNumber = (session != null) ? (Integer) session.getAttribute("memberNumber") : null;
        if (memberNumber == null) {
            // 로그인 안 되어 있으면 로그인 페이지로
            result.setRedirect(true);
            result.setPath(request.getContextPath() + "/login/login.lo?login=required");
            return result;
        }

        // 2) 입력 비밀번호
        String inputPw = request.getParameter("generalPw");
        if (inputPw == null || inputPw.isEmpty()) {
            request.setAttribute("pwError", "비밀번호를 입력해주세요.");
            result.setRedirect(false);
            result.setPath("/app/userMyPage/generalCheckPw.jsp");
            return result;
        }

        // 3) 검증
        UserMyPageDAO dao = new UserMyPageDAO();
        boolean ok = dao.checkPassword(memberNumber, inputPw);

        if (ok) {
        	session.setAttribute("myPagePwVerified", Boolean.TRUE);
            result.setRedirect(true);
            result.setPath(request.getContextPath() + "/userMyPage/editUserInfo.my");
        } else {
            request.setAttribute("pwError", "비밀번호가 일치하지 않습니다.");
            result.setRedirect(false);
            result.setPath("/app/userMyPage/generalCheckPw.jsp");
        }

        return result;
    }
}
