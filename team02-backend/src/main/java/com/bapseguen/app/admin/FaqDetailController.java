package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;

public class FaqDetailController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] FAQ 상세 요청");

        int postNumber = Integer.parseInt(request.getParameter("postNumber"));

        AdminDAO dao = new AdminDAO();
        AdminPostDTO faq = dao.selectFaqDetail(postNumber);

        request.setAttribute("faq", faq);

        Result result = new Result();
        result.setPath("/app/admin/faqDetail.jsp");
        result.setRedirect(false);
        return result;
    }
}
