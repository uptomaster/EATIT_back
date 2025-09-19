package com.bapseguen.app.userMyPage;

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
import com.bapseguen.app.dto.view.CommentListDTO;
import com.bapseguen.app.userMyPage.dao.UserMyPageDAO;

public class MyCommentsListOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("====MyCommentsListOkController 실행====");
		
		UserMyPageDAO userMyPageDAO = new UserMyPageDAO();
        HttpSession session = request.getSession(false);
        int memberNumber = (int) session.getAttribute("memberNumber");
        System.out.println("세션의 회원번호 : " + memberNumber);

        Result result = new Result();

        // 페이지 번호 처리
        String temp = request.getParameter("page");
        int page = (temp == null) ? 1 : Integer.valueOf(temp);
        int rowCount = 10;  // 한 페이지에 보여줄 댓글 개수
        int pageCount = 5;   // 페이지네이션 한 묶음 개수

        // 시작/끝 행 번호 계산
        int startRow = (page - 1) * rowCount + 1;
        int endRow = startRow + rowCount - 1;

        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("startRow", startRow);
        pageMap.put("endRow", endRow);
        pageMap.put("memberNumber", memberNumber);

        // 댓글 목록 조회
        List<CommentListDTO> myComments = userMyPageDAO.myCommentSelect(pageMap);
        request.setAttribute("myComments", myComments);

        // 전체 댓글 수
        int total = userMyPageDAO.myCommentCount(memberNumber);

        // 페이지네이션 계산
        int realEndPage = (int) Math.ceil(total / (double) rowCount);
        int endPage = (int) (Math.ceil(page / (double) pageCount) * pageCount);
        int startPage = endPage - (pageCount - 1);

        // endPage 보정
        endPage = Math.min(endPage, realEndPage);

        // prev / next 버튼 여부
        boolean prev = startPage > 1;
        boolean next = endPage < realEndPage;

        // JSP 전달값
        request.setAttribute("page", page);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("prev", prev);
        request.setAttribute("next", next);

        System.out.println("==== 페이징정보 확인 ====");
        System.out.println("pageMap : " + pageMap);
        System.out.println("myComments : " + myComments);
        System.out.println("startPage : " + startPage + ", endPage : " + endPage + ", prev : " + prev + ", next : " + next);
        System.out.println("====================");

        result.setPath("/app/userMyPage/manageMyCommentsList.jsp");
        result.setRedirect(false);

        return result;
		
	}
	
}
