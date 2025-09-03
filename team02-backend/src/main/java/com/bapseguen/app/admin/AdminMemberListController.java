package com.bapseguen.app.admin;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;

public class AdminMemberListController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("==== AdminMemberListController 실행 ====");

        Result result = new Result();
        AdminDAO adminDAO = new AdminDAO();

        // -------------------------------
        // 1) 페이징 파라미터 처리
        // -------------------------------
        int page = 1;
        String temp = request.getParameter("page");
        if (temp != null && !temp.isBlank()) {
            try {
                page = Integer.parseInt(temp);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        int rowCount = 10;   // 한 페이지당 출력할 데이터 수
        int pageCount = 5;   // 페이지네이션 블럭 단위
        int startRow = (page - 1) * rowCount + 1;
        int endRow = page * rowCount;

        // -------------------------------
        // 2) 검색 조건 처리
        // -------------------------------
        String searchType = request.getParameter("searchType"); // id / name / type
        String searchWord = request.getParameter("searchWord");
        String memberType = request.getParameter("memberType"); // GENERAL / SELLER

        // AdminMapper.xml 에서 searchWord, memberType 만 처리하므로
        // searchType == "name"이면 그냥 searchWord에 이름 넣어서 전달
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("startRow", startRow);
        paramMap.put("endRow", endRow);
        paramMap.put("memberType", memberType);

        if (searchWord != null && !searchWord.isBlank()) {
            paramMap.put("searchWord", searchWord);
        }

        // -------------------------------
        // 3) DAO 호출
        // -------------------------------
        List<Map<String, Object>> memberList = adminDAO.selectMemberList(paramMap);
        int totalCount = adminDAO.memberListCount(paramMap);

        // -------------------------------
        // 4) 페이지네이션 계산
        // -------------------------------
        int startPage = ((page - 1) / pageCount) * pageCount + 1;
        int endPage = startPage + pageCount - 1;
        int realEndPage = (int) Math.ceil((double) totalCount / rowCount);

        if (endPage > realEndPage) {
            endPage = realEndPage;
        }

        boolean prev = startPage > 1;
        boolean next = endPage < realEndPage;

        // -------------------------------
        // 5) JSP 전달
        // -------------------------------
        request.setAttribute("memberList", memberList);
        request.setAttribute("page", page);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("prev", prev);
        request.setAttribute("next", next);
        request.setAttribute("searchType", searchType);
        request.setAttribute("searchWord", searchWord);
        request.setAttribute("memberType", memberType);

        result.setPath("/app/admin/memberList.jsp");
        result.setRedirect(false);

        System.out.println("==== AdminMemberListController 완료 ====");
        return result;
    }
}
