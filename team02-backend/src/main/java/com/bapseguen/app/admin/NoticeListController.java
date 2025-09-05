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

        // 현재 페이지
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        int rowCount = 10; // 한 페이지에 보여줄 게시글 수
        int pageBlock = 5; // 하단에 보여줄 페이지 번호 개수

        // 시작/끝 row 계산
        int startRow = (page - 1) * rowCount + 1;
        int endRow = page * rowCount;

        Map<String, Object> params = new HashMap<>();
        params.put("startRow", startRow);
        params.put("endRow", endRow);
        params.put("searchWord", request.getParameter("searchWord"));

        // 목록 + 전체 개수
        List<AdminPostDTO> noticeList = dao.selectNoticeList(params);
        int totalCount = dao.countNotices(params);

        // 전체 페이지 수
        int totalPage = (int) Math.ceil((double) totalCount / rowCount);

        // 페이지 블럭 계산
        int startPage = ((page - 1) / pageBlock) * pageBlock + 1;
        int endPage = startPage + pageBlock - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }

        boolean prev = startPage > 1;
        boolean next = endPage < totalPage;

        // JSP 전달값
        request.setAttribute("noticeList", noticeList);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("page", page);
        request.setAttribute("rowCount", rowCount);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("prev", prev);
        request.setAttribute("next", next);

        result.setPath("/app/admin/noticeList.jsp");
        result.setRedirect(false);
        return result;
    }
}
