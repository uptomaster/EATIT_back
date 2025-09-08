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

public class ReportListController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("→ [ADMIN] ReportListController 실행");

		AdminDAO adminDAO = new AdminDAO();
		Result result = new Result();

		// ================= 페이징 처리 =================
		String temp = request.getParameter("page");
		int page = temp == null ? 1 : Integer.parseInt(temp);

		int rowCount = 10; // 한 페이지당 게시글 수
		int pageCount = 10; // 한 화면에 보여줄 페이지 버튼 수

		int startRow = (page - 1) * rowCount + 1;
		int endRow = page * rowCount;

		// 검색 처리
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");

		Map<String, Object> pageMap = new HashMap<>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		pageMap.put("searchType", searchType);
		pageMap.put("searchWord", searchWord);

		int total = adminDAO.countReportList(pageMap);

		int realEndPage = (int) Math.ceil(total / (double) rowCount);
		int startPage = ((page - 1) / pageCount) * pageCount + 1;
		int endPage = startPage + pageCount - 1;

		if (endPage > realEndPage) {
			endPage = realEndPage == 0 ? 1 : realEndPage;
		}

		boolean prev = startPage > 1;
		boolean next = endPage < realEndPage;

		// 데이터 조회
		List<PostReportDTO> reportList = adminDAO.selectReportListPaged(pageMap);

		// request에 데이터 담기
		request.setAttribute("reportList", reportList);
		request.setAttribute("total", total);
		request.setAttribute("page", page);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchWord", searchWord);

		result.setPath("/app/admin/reportList.jsp");
		result.setRedirect(false);

		return result;
	}
}
