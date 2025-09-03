package com.bapseguen.app.admin;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;

public class NoticeListController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 공지/이벤트 목록 요청");

        AdminDAO dao = new AdminDAO();
        Result result = new Result();

        // 페이징 처리
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        int rowCount = 10;
        int startRow = (page - 1) * rowCount + 1;
        int endRow = page * rowCount;

        Map<String, Object> params = new HashMap<>();
        params.put("startRow", startRow);
        params.put("endRow", endRow);
        params.put("searchWord", request.getParameter("searchWord"));

        List<AdminPostDTO> noticeList = dao.selectNoticeList(params);
        int totalCount = dao.countNotices(params);

        request.setAttribute("noticeList", noticeList);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("page", page);
        request.setAttribute("rowCount", rowCount);

        result.setPath("/app/admin/noticeList.jsp");
        result.setRedirect(false);
        return result;
    }
}
