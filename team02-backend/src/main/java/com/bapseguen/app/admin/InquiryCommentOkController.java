package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;
import com.bapseguen.app.dto.view.InquiryCommentDTO;

public class InquiryCommentOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int postNumber = Integer.parseInt(request.getParameter("postNumber"));
        String replyContent = request.getParameter("replyContent");

        AdminDAO dao = new AdminDAO();
        AdminPostDTO dto = new AdminPostDTO();
        dto.setPostNumber(postNumber);
        dto.setAnswerContent(replyContent);

        dao.updateInquiryAnswer(dto); // 신규 등록이든 수정이든 동일 처리

        Result result = new Result();
        result.setPath(request.getContextPath() + "/admin/inquiry/detail.ad?postNumber=" + postNumber);
        result.setRedirect(true);
        return result;
    }
}

