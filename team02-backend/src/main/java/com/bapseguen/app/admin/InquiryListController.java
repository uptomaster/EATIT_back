package com.bapseguen.app.admin;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;

public class InquiryListController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("[ADMIN] 문의 목록 요청");

		AdminDAO dao = new AdminDAO();
		Result result = new Result();

		// 현재 페이지
		int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		int rowCount = 10; // 한 페이지 게시글 수
		int pageSize = 5; // 페이지 버튼 개수

		int startRow = (page - 1) * rowCount + 1;
		int endRow = page * rowCount;

		// 검색 파라미터
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		// Mapper에 전달할 Map
		Map<String, Object> params = new HashMap<>();
		params.put("startRow", startRow);
		params.put("endRow", endRow);
		params.put("searchType", searchType);
		params.put("searchWord", searchWord);
		params.put("startDate", startDate);
		params.put("endDate", endDate);

		// DAO 호출
		List<AdminPostDTO> inquiryList = dao.selectInquiryList(params);
		int totalCount = dao.countInquiries(params);

		// ✅ 페이지네이션 계산
		int realEndPage = (int) Math.ceil((double) totalCount / rowCount);
		int startPage = ((page - 1) / pageSize) * pageSize + 1;
		int endPage = startPage + pageSize - 1;
		if (endPage > realEndPage) {
			endPage = realEndPage;
		}

		boolean prev = startPage > 1;
		boolean next = endPage < realEndPage;

		// JSP에 데이터 전달
		request.setAttribute("inquiryList", inquiryList);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("page", page);
		request.setAttribute("rowCount", rowCount);

		// 페이지네이션 관련 속성
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);

		// 검색 조건 유지
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchWord", searchWord);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);

		result.setPath("/app/admin/adminInquiry.jsp");
		result.setRedirect(false);
		return result;
	}
}
