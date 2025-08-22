package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;

public class AdminLoginController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 관리자 로그인 페이지 요청");

        Result result = new Result();
        // 로그인 폼 JSP로 forward
        result.setPath("/app/admin/adminLogin.jsp");
        result.setRedirect(false);
        return result;
    }
}
