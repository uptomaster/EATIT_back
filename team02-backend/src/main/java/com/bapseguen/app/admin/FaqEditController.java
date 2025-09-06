package com.bapseguen.app.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;

public class FaqEditController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("==== [ADMIN] FaqEditController 실행 ====");

        Result result = new Result();
        HttpSession session = request.getSession(false);

        if (session == null || !"ADMIN".equals(String.valueOf(session.getAttribute("memberType")))) {
            System.out.println("[ADMIN] 권한 없음 → 로그인 페이지로 이동");
            result.setPath(request.getContextPath() + "/admin/login.ad");
            result.setRedirect(true);
            return result;
        }

        int postNumber = Integer.parseInt(request.getParameter("postNumber"));
        AdminDAO adminDAO = new AdminDAO();
        AdminPostDTO faqDetail = adminDAO.selectFaqDetail(postNumber);

        request.setAttribute("faqDetail", faqDetail);

        result.setPath("/app/admin/adminFaqEdit.jsp");
        result.setRedirect(false);

        System.out.println("==== [ADMIN] FaqEditController 완료 (postNumber=" + postNumber + ") ====");
        return result;
    }
}
