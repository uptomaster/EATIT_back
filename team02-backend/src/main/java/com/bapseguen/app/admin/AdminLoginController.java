package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;

public class AdminLoginController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 관리자 로그인 페이지 요청");

        // 이미 로그인된 관리자라면 → 대시보드로 이동
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("adminNumber") != null) {
            Result redirect = new Result();
            redirect.setPath(request.getContextPath() + "/admin/dashboard.ad");
            redirect.setRedirect(true);
            return redirect;
        }

        // 로그인 페이지로 forward
        Result result = new Result();
        result.setPath("/app/admin/adminLogin.jsp");
        result.setRedirect(false);
        return result;
    }
}
