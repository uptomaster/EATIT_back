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
import com.bapseguen.app.dto.MemberSuspendDTO;

public class SuspendListController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("→ [ADMIN] SuspendListController 실행");

		AdminDAO adminDAO = new AdminDAO();

		// 페이지 번호 처리
		String temp = request.getParameter("page");
		int page = temp == null ? 1 : Integer.parseInt(temp);

		int rowCount = 10; // 한 페이지에 보여줄 게시글 수
		int pageCount = 5; // 한 번에 보여줄 페이지 버튼 수
		int startRow = (page - 1) * rowCount + 1;
		int endRow = page * rowCount;

		// 검색 조건
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");

		Map<String, Object> pageMap = new HashMap<>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		pageMap.put("searchType", searchType);
		pageMap.put("searchWord", searchWord);

		// 전체 정지회원 수
		int total = adminDAO.countSuspendList(pageMap);

		// 페이지네이션 계산
		int realEndPage = (int) Math.ceil(total / (double) rowCount);
		int startPage = ((page - 1) / pageCount) * pageCount + 1;
		int endPage = startPage + pageCount - 1;
		endPage = endPage > realEndPage ? realEndPage : endPage;

		boolean prev = startPage > 1;
		boolean next = endPage < realEndPage;

		// 정지회원 목록 조회
		List<MemberSuspendDTO> suspendList = adminDAO.selectSuspendList(pageMap);

		// JSP 전달
		request.setAttribute("suspendList", suspendList);
		request.setAttribute("total", total);
		request.setAttribute("page", page);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchWord", searchWord);

		// 이동
		Result result = new Result();
		result.setPath("/app/admin/suspendedMemberList.jsp");
		result.setRedirect(false);

		return result;
	}
}
