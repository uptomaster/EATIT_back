package com.bapseguen.app.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;

public class NoticeListController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 공지 목록 요청");

        AdminDAO dao = new AdminDAO();

        // 페이징 처리
        int page = 1;
        String temp = request.getParameter("page");
        if (temp != null) { page = Integer.parseInt(temp); }

        int rowCount = 10;   // 한 페이지당 게시글 수
        int startRow = (page - 1) * rowCount + 1;
        int endRow = page * rowCount;

        String searchWord = request.getParameter("searchWord");

        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("startRow", startRow);
        pageMap.put("endRow", endRow);
        pageMap.put("searchWord", searchWord);

        List<AdminPostDTO> noticeList = dao.selectNoticeList(pageMap);
        int totalCount = dao.countNotices(pageMap);

        request.setAttribute("noticeList", noticeList);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("page", page);
        request.setAttribute("rowCount", rowCount);

        Result result = new Result();
        result.setPath("/app/admin/noticeList.jsp");
        result.setRedirect(false);
        return result;
    }
}

