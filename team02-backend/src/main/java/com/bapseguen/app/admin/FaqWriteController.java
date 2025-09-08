package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;

public class FaqWriteController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("==== [ADMIN] FaqWriteController 실행 ====");

        Result result = new Result();
        HttpSession session = request.getSession(false);

        // 관리자 권한 체크
        if (session == null || !"ADMIN".equals(String.valueOf(session.getAttribute("memberType")))) {
            System.out.println("[ADMIN] 권한 없음 → 로그인 페이지로 이동");
            result.setPath(request.getContextPath() + "/admin/login.ad");
            result.setRedirect(true);
            return result;
        }

        // 작성 폼 페이지로 이동
        result.setPath("/app/admin/adminFaqWrite.jsp");
        result.setRedirect(false);

        System.out.println("==== [ADMIN] FaqWriteController 완료 ====");
        return result;
    }
}
