package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;

public class InquiryCommentOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 문의글 답변 처리");

        int postNumber = Integer.parseInt(request.getParameter("postNumber"));
        String inquiryStatus = request.getParameter("inquiryStatus"); 
        // "IN_PROGRESS" 또는 "COMPLETE" 값으로 넘어옴

        AdminPostDTO postDTO = new AdminPostDTO();
        postDTO.setPostNumber(postNumber);
        postDTO.setInquiryStatus(inquiryStatus);

        AdminDAO dao = new AdminDAO();
        dao.updateInquiryStatus(postDTO);

        Result result = new Result();
        result.setPath(request.getContextPath() + "/admin/inquiry/detail.ad?postNumber=" + postNumber);
        result.setRedirect(true);
        return result;
    }
}
