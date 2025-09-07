package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;

public class InquiryAnswerOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("[ADMIN] 문의 답변 등록 처리");

        int postNumber = Integer.parseInt(request.getParameter("postNumber"));
        String answerContent = request.getParameter("answerContent");

        AdminPostDTO dto = new AdminPostDTO();
        dto.setPostNumber(postNumber);
        dto.setAnswerContent(answerContent);

        AdminDAO dao = new AdminDAO();
        dao.updateInquiryAnswer(dto);

        Result result = new Result();
        result.setPath(request.getContextPath() + "/admin/inquiry/detail.ad?postNumber=" + postNumber);
        result.setRedirect(true);
        return result;
    }
}
