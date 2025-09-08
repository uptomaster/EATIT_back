package com.bapseguen.app.admin;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;

public class BoardFreeListController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("==== [ADMIN] 자유게시판 목록 컨트롤러 실행 ====");

		Result result = new Result();
		AdminDAO adminDAO = new AdminDAO();

		// 1) 페이지 번호
		int page = 1;
		String temp = request.getParameter("page");
		if (temp != null && !temp.isBlank()) {
			page = Integer.parseInt(temp);
		}

		// 2) 검색 파라미터
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");

		// 3) 페이징 기본값
		int rowCount = 10; // 한 페이지당 게시글 수
		int pageCount = 5; // 페이지 버튼 개수

		int startRow = (page - 1) * rowCount + 1;
		int endRow = page * rowCount;

		// 4) 파라미터 맵 구성
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("startRow", startRow);
		paramMap.put("endRow", endRow);
		paramMap.put("searchType", searchType);
		paramMap.put("searchWord", searchWord);

		// 5) 전체 게시글 수
		int totalCount = adminDAO.countBoardFree(paramMap);

		// 6) 페이지네이션 계산
		int startPage = ((page - 1) / pageCount) * pageCount + 1;
		int endPage = startPage + pageCount - 1;
		int realEndPage = (int) Math.ceil(totalCount / (double) rowCount);
		if (endPage > realEndPage) {
			endPage = realEndPage;
		}

		boolean prev = startPage > 1;
		boolean next = endPage < realEndPage;

		// 7) 목록 조회
		List<Map<String, Object>> freeList = adminDAO.selectBoardFreeList(paramMap);

		// 8) JSP에 데이터 전달
		request.setAttribute("freeList", freeList);
		request.setAttribute("page", page);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchWord", searchWord);

		result.setPath("/app/admin/boardFreeList.jsp");
		result.setRedirect(false);

		System.out.println("==== [ADMIN] 자유게시판 목록 컨트롤러 완료 ====");
		return result;
	}
}
