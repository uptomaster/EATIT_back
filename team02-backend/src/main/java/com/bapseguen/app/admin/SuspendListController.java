package com.bapseguen.app.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.MemberSuspendDTO;

public class SuspendListController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("→ [ADMIN] SuspendListController 실행");

        AdminDAO adminDAO = new AdminDAO();
        Result result = new Result();

        // 파라미터
        String temp = request.getParameter("page");
        String searchType = request.getParameter("searchType");
        String searchWord = request.getParameter("searchWord");

        int page = temp == null ? 1 : Integer.parseInt(temp);
        int rowCount = 10;
        int pageSize = 10;
        int startRow = (page - 1) * rowCount + 1;
        int endRow = page * rowCount;

        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("startRow", startRow);
        pageMap.put("endRow", endRow);
        pageMap.put("searchType", searchType);
        pageMap.put("searchWord", searchWord);

        // 전체 개수
        int total = adminDAO.countSuspends(pageMap);

        // 페이지 계산
        int realEndPage = (int) Math.ceil(total / (double) rowCount);
        int startPage = ((page - 1) / pageSize) * pageSize + 1;
        int endPage = startPage + pageSize - 1;
        if(endPage > realEndPage) {
            endPage = realEndPage;
        }
        boolean prev = startPage > 1;
        boolean next = endPage < realEndPage;

        // 리스트 조회
        List<MemberSuspendDTO> suspendList = adminDAO.selectSuspendList(pageMap);

        // 데이터 바인딩
        request.setAttribute("suspendList", suspendList);
        request.setAttribute("total", total);
        request.setAttribute("page", page);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("realEndPage", realEndPage);
        request.setAttribute("prev", prev);
        request.setAttribute("next", next);
        request.setAttribute("searchType", searchType);
        request.setAttribute("searchWord", searchWord);

        result.setPath("/app/admin/suspendedMemberList.jsp");
        result.setRedirect(false);
        return result;
    }
}
