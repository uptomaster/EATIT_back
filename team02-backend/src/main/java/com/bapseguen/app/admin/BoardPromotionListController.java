package com.bapseguen.app.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;

public class BoardPromotionListController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("==== BoardPromotionListController 실행 ====");

        Result result = new Result();

        // 1) 로그인/권한 체크
        HttpSession session = request.getSession(false);
        if (session == null || !"ADMIN".equals(String.valueOf(session.getAttribute("memberType")))) {
            System.out.println("[ADMIN] 로그인 세션 없음 → 로그인 페이지로 이동");
            result.setPath(request.getContextPath() + "/admin/login.ad");
            result.setRedirect(true);
            return result;
        }

        AdminDAO adminDAO = new AdminDAO();

        // 2) 페이징
        int page = 1;
        String temp = request.getParameter("page");
        if (temp != null && !temp.isBlank()) {
            page = Integer.parseInt(temp);
        }

        int rowCount = 10;
        int pageCount = 5;
        int startRow = (page - 1) * rowCount + 1;
        int endRow = page * rowCount;

        // 3) 검색 조건
        String searchType = request.getParameter("searchType");
        String searchWord = request.getParameter("searchWord");

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("startRow", startRow);
        paramMap.put("endRow", endRow);
        paramMap.put("searchType", searchType);
        paramMap.put("searchWord", searchWord);

        // 4) 데이터 조회
        List<Map<String, Object>> promotionList = adminDAO.selectBoardPromotionList(paramMap);
        int totalCount = adminDAO.countBoardPromotion(paramMap);

        // 5) 페이지네이션 계산
        int startPage = ((page - 1) / pageCount) * pageCount + 1;
        int endPage = startPage + pageCount - 1;
        int realEndPage = (int) Math.ceil((double) totalCount / rowCount);

        if (endPage > realEndPage) {
            endPage = realEndPage;
        }

        boolean prev = startPage > 1;
        boolean next = endPage < realEndPage;

        // 6) JSP 전달
        request.setAttribute("promotionList", promotionList);
        request.setAttribute("page", page);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("prev", prev);
        request.setAttribute("next", next);
        request.setAttribute("searchType", searchType);
        request.setAttribute("searchWord", searchWord);

        // 7) 이동
        result.setPath("/app/admin/boardPromotionList.jsp");
        result.setRedirect(false);

        System.out.println("==== BoardPromotionListController 완료 (page=" + page + ", total=" + totalCount + ") ====");
        return result;
    }
}
