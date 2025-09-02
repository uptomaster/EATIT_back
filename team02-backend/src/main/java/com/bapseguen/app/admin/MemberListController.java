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
import com.bapseguen.app.dto.view.MemberListDTO;

public class MemberListController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("==== MemberListController 실행 ====");

        AdminDAO adminDAO = new AdminDAO();
        Result result = new Result();

        // ===== 페이지 파라미터 =====
        String temp = request.getParameter("page");
        int page = (temp == null) ? 1 : Integer.parseInt(temp);

        int rowCount = 10;   // 한 페이지당 회원 수
        int pageCount = 5;   // 하단 페이지 번호 개수

        int startRow = (page - 1) * rowCount + 1;
        int endRow = startRow + rowCount - 1;

        // ===== 검색 파라미터 =====
        String searchType = request.getParameter("searchType");
        String searchWord = request.getParameter("searchWord");

        // ===== PageMap 생성 =====
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("startRow", startRow);
        pageMap.put("endRow", endRow);
        pageMap.put("searchType", searchType);
        pageMap.put("searchWord", searchWord);

        // ===== 회원 목록 조회 =====
//        List<MemberListDTO> memberList = adminDAO.selectMemberList(pageMap);
//        request.setAttribute("memberList", memberList);

        // ===== 전체 회원 수 =====
        int total = adminDAO.memberListCount(pageMap);

        // ===== 페이징 처리 =====
        int realEndPage = (int) Math.ceil(total / (double) rowCount);
        int endPage = (int) (Math.ceil(page / (double) pageCount) * pageCount);
        int startPage = endPage - (pageCount - 1);
        endPage = Math.min(endPage, realEndPage);

        boolean prev = startPage > 1;
        boolean next = endPage < realEndPage;

        // ===== request에 저장 =====
        request.setAttribute("page", page);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("prev", prev);
        request.setAttribute("next", next);

        // 검색 값 유지
        request.setAttribute("searchType", searchType);
        request.setAttribute("searchWord", searchWord);

        System.out.println("==== 페이징/검색 정보 ====");
        System.out.println("pageMap : " + pageMap);
        System.out.println("memberList size : " + memberList.size());
        System.out.println("startPage : " + startPage + ", endPage : " + endPage + ", prev : " + prev + ", next : " + next);
        System.out.println("========================");

        result.setPath("/app/admin/memberList.jsp");
        result.setRedirect(false);

        return result;
    }
}
