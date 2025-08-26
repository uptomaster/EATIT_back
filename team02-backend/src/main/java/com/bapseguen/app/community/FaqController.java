package com.bapseguen.app.community;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.community.dao.CommunityDAO;
import com.bapseguen.app.dto.FaqDTO;

public class FaqController implements Execute {

    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("====FaqController 실행====");
        CommunityDAO communityDAO = new CommunityDAO();
        Result result = new Result();

        // 페이지 번호 처리
        String temp = request.getParameter("page");
        int page = (temp == null) ? 1 : Integer.valueOf(temp);
        int rowCount = 10;  // 한 페이지에 표시될 게시글 수
        int pageCount = 5;  // 페이징에서 한 번에 표시될 페이지 수

        // 페이지 계산
        int startRow = (page - 1) * rowCount + 1;
        int endRow = startRow + rowCount - 1;

        // 페이지 맵 설정
        Map<String, Integer> pageMap = new HashMap<>();
        pageMap.put("startRow", startRow);
        pageMap.put("endRow", endRow);

        // 게시글 목록 조회
        List<FaqDTO> faqList = communityDAO.faqSelectAll(pageMap);
        request.setAttribute("faq", faqList);

        // 전체 게시글 수 가져오기
        int total = communityDAO.getTotal1();
        int realEndPage = (int) Math.ceil(total / (double) rowCount);
        int endPage = (int) (Math.ceil(page / (double) pageCount) * pageCount);
        int startPage = endPage - (pageCount - 1);
        endPage = Math.min(endPage, realEndPage);

        // 페이징 여부
        boolean prev = startPage > 1;
        boolean next = endPage < realEndPage;

        // 페이징 정보 request에 저장
        request.setAttribute("page", page);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("prev", prev);
        request.setAttribute("next", next);

        System.out.println("====페이징정보 확인====");
        System.out.println("pageMap : " + pageMap);
        System.out.println("faqList : " + faqList);
        System.out.println("startPage : " + startPage + ", endPage : " + endPage + ", prev : " + prev + ", next : " + next);
        System.out.println("====================");

        // 결과 경로 설정 (고객센터 목록 JSP로 수정)
        result.setPath("/app/community/faqList.jsp");  // 경로를 고객센터 목록 페이지로 변경
        result.setRedirect(false);

        return result;
    }
}
