package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;

public class AdminDashboardController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 대시보드 컨트롤러 시작");

        Result result = new Result();

        // 로그인/권한 체크(선택)
        HttpSession session = request.getSession(false);
        if (session == null || !"ADMIN".equals(String.valueOf(session.getAttribute("memberType")))) {
            result.setPath(request.getContextPath() + "/admin/login.ad");
            result.setRedirect(true);
            return result;
        }

        // 여기서 대시보드에 필요한 데이터 셋업 가능 (필요 없으면 바로 JSP로)
        result.setPath("/app/admin/dashboard.jsp"); // ★ 실제 존재하는 JSP
        result.setRedirect(false);
        return result;
    }
}
