package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;

public class NoticeDetailController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 공지/이벤트 상세보기 요청");

        int postNumber = Integer.parseInt(request.getParameter("postNumber"));
        AdminDAO dao = new AdminDAO();
        AdminPostDTO notice = dao.selectNoticeDetail(postNumber);

        request.setAttribute("notice", notice);

        Result result = new Result();
        result.setPath("/app/admin/noticeDetail.jsp");
        result.setRedirect(false);
        return result;
    }
}