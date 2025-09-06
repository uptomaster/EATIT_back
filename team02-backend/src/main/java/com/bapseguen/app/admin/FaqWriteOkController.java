package com.bapseguen.app.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;

public class FaqWriteOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("==== [ADMIN] FaqWriteOkController 실행 ====");

        Result result = new Result();
        HttpSession session = request.getSession(false);

        if (session == null || !"ADMIN".equals(String.valueOf(session.getAttribute("memberType")))) {
            System.out.println("[ADMIN] 권한 없음 → 로그인 페이지로 이동");
            result.setPath(request.getContextPath() + "/admin/login.ad");
            result.setRedirect(true);
            return result;
        }

        int adminNumber = (Integer) session.getAttribute("memberNumber");
        String postTitle = request.getParameter("postTitle");
        String faqContent = request.getParameter("faqContent");

        AdminPostDTO postDTO = new AdminPostDTO();
        postDTO.setAdminNumber(adminNumber);
        postDTO.setPostTitle(postTitle);
        postDTO.setFaqContent(faqContent);

        AdminDAO adminDAO = new AdminDAO();
        adminDAO.insertFaqPost(postDTO); // TBL_POST
        adminDAO.insertFaq(postDTO);     // TBL_FAQ

        result.setPath(request.getContextPath() + "/admin/faq/list.ad");
        result.setRedirect(true);

        System.out.println("==== [ADMIN] FaqWriteOkController 완료 (postNumber=" + postDTO.getPostNumber() + ") ====");
        return result;
    }
}
