package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;

public class FaqUpdateOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] FAQ 수정 처리");

        int postNumber = Integer.parseInt(request.getParameter("postNumber"));
        String postTitle = request.getParameter("postTitle");
        String faqContent = request.getParameter("faqContent");

        AdminPostDTO postDTO = new AdminPostDTO();
        postDTO.setPostNumber(postNumber);
        postDTO.setPostTitle(postTitle);
        postDTO.setFaqContent(faqContent);

        AdminDAO dao = new AdminDAO();
        dao.updateFaqTitle(postDTO);
        dao.updateFaqContent(postDTO);

        Result result = new Result();
        result.setPath(request.getContextPath() + "/admin/faq/detail.ad?postNumber=" + postNumber);
        result.setRedirect(true);
        return result;
    }
}
