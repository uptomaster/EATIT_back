package com.bapseguen.app.userMyPage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.userMyPage.dao.UserMyPageDAO;
import com.bapseguen.app.dto.view.MyPageDTO;

public class EditUserInfoController implements Execute {
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

        
        if (session.getAttribute("myPagePwVerified") == null) {
            result.setRedirect(true);
            result.setPath(request.getContextPath() + "/UserMyPage/generalCheckPw.my");
            return result;
        }

        UserMyPageDAO dao = new UserMyPageDAO();
        MyPageDTO my = dao.MyPageSelect(memberNumber);
        Object msg = session.getAttribute("phoneFlashMsg");
        if (msg != null) {
            request.setAttribute("phoneMsg", msg);
            session.removeAttribute("phoneFlashMsg");
        }
        Object color = session.getAttribute("phoneFlashColor");
        if (color != null) {
            request.setAttribute("phoneMsgColor", color);
            session.removeAttribute("phoneFlashColor");
        }
        Object dev = session.getAttribute("phoneDevCode");
        if (dev != null) {
            request.setAttribute("phoneDevCode", dev);
            session.removeAttribute("phoneDevCode");
        }
        request.setAttribute("my", my);

        result.setRedirect(false);
        result.setPath("/app/userMyPage/editUserInfo.jsp");
        return result;
    }
}