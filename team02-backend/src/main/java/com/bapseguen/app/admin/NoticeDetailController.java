package com.bapseguen.app.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.AdminImageDTO;
import com.bapseguen.app.dto.view.AdminPostDTO;

public class NoticeDetailController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int postNumber = Integer.parseInt(request.getParameter("postNumber"));
        AdminDAO dao = new AdminDAO();

        AdminPostDTO notice = dao.selectNoticeDetail(postNumber);
        List<AdminImageDTO> images = dao.selectAdminImagesByPost(postNumber);

        request.setAttribute("notice", notice);
        request.setAttribute("images", images);

        Result result = new Result();
        result.setPath("/app/admin/noticeDetail.jsp");
        result.setRedirect(false);
        return result;
    }
}
