package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;

public class NoticeUpdateOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 공지 수정 처리");

        int postNumber = Integer.parseInt(request.getParameter("postNumber"));
        String postTitle = request.getParameter("postTitle");
        String noticeContent = request.getParameter("noticeContent");

        AdminPostDTO postDTO = new AdminPostDTO();
        postDTO.setPostNumber(postNumber);
        postDTO.setPostTitle(postTitle);
        postDTO.setNoticeContent(noticeContent);

        AdminDAO dao = new AdminDAO();
        dao.updateNoticeTitle(postDTO);
        dao.updateNoticeContent(postDTO);

        Result result = new Result();
        result.setPath(request.getContextPath() + "/admin/notice/detail.ad?postNumber=" + postNumber);
        result.setRedirect(true);
        return result;
    }
}
