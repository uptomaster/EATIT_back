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

        // 세션이 없거나, ADMIN 회원유형이 아닌 경우(관리자 페이지는 이거 공통으로 들어갑니다.)
        HttpSession session = request.getSession(false);
        if (session == null || !"ADMIN".equals(String.valueOf(session.getAttribute("memberType")))) {
            result.setPath(request.getContextPath() + "/admin/login.ad");
            result.setRedirect(true);
            return result;
        }

        // 여기서 대시보드에 필요한 데이터 셋업(미구현)
        
        
        
        
        // 대시보드로 이동
        result.setPath("/app/admin/dashboard.jsp");
        result.setRedirect(false);
        return result;
    }
}
