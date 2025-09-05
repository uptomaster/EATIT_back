package com.bapseguen.app.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;

public class NoticeEditController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int postNumber = Integer.parseInt(request.getParameter("postNumber"));
        AdminDAO dao = new AdminDAO();

        // 기존 글 정보 가져오기
        AdminPostDTO notice = dao.selectNoticeDetail(postNumber);
        request.setAttribute("notice", notice);

        // 기존 이미지 삭제
        dao.deleteAdminImagesByPost(postNumber);

        Result result = new Result();
        result.setPath("/app/admin/noticeEdit.jsp");
        result.setRedirect(false);
        return result;
    }
}

