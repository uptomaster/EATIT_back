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
import com.bapseguen.app.dto.PostReportDTO;
import com.bapseguen.app.util.PageDTO;

public class ReportListController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("→ [ADMIN] ReportListController 실행");

        AdminDAO adminDAO = new AdminDAO();

        // 파라미터 수집
        String temp = request.getParameter("page");
        String searchType = request.getParameter("searchType");
        String searchWord = request.getParameter("searchWord");

        int page = (temp == null) ? 1 : Integer.parseInt(temp);
        int rowCount = 10;
        int pageSize = 10;
        int startRow = (page - 1) * rowCount + 1;
        int endRow = page * rowCount;

        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("startRow", startRow);
        pageMap.put("endRow", endRow);
        pageMap.put("searchType", searchType);
        pageMap.put("searchWord", searchWord);

        // 전체 데이터 개수 (검색 조건 포함)
        int total = adminDAO.countReportList(pageMap);

        // 페이징 처리
        PageDTO pageDTO = new PageDTO(page, total, rowCount, pageSize);

        // 신고 목록 조회
        List<PostReportDTO> reportList = adminDAO.selectReportListPaged(pageMap);

        request.setAttribute("reportList", reportList);
        request.setAttribute("total", total);
        request.setAttribute("page", page);
        request.setAttribute("pageDTO", pageDTO);
        request.setAttribute("searchType", searchType);
        request.setAttribute("searchWord", searchWord);

        Result result = new Result();
        result.setPath("/app/admin/reportList.jsp");
        result.setRedirect(false);

        return result;
    }
}
