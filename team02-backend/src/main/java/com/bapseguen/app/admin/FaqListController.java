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
import com.bapseguen.app.dto.view.AdminPostDTO;

public class FaqListController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("[ADMIN] FAQ 목록 요청");

		AdminDAO dao = new AdminDAO();
		Result result = new Result();

		// ===== 페이지 번호 처리 (안전하게) =====
		int page = 1;
		try {
			String pageParam = request.getParameter("page");
			if (pageParam != null && !pageParam.trim().isEmpty()) {
				page = Math.max(1, Integer.parseInt(pageParam.trim()));
			}
		} catch (NumberFormatException e) {
			System.out.println("[WARN] 잘못된 페이지 파라미터: " + request.getParameter("page"));
			page = 1;
		}

		int rowCount = 10; // 한 페이지당 게시글 개수
		int pageSize = 10; // 페이지네이션 버튼 개수
		int startRow = (page - 1) * rowCount + 1;
		int endRow = page * rowCount;

		// ===== 검색 파라미터 =====
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");

		Map<String, Object> params = new HashMap<>();
		params.put("startRow", startRow);
		params.put("endRow", endRow);
		params.put("searchType", searchType);
		params.put("searchWord", searchWord);

		// ===== DAO 호출 =====
		List<AdminPostDTO> faqList = dao.selectFaqList(params);
		int totalCount = dao.countFaqs(params);

		// ===== 페이지네이션 계산 =====
		int realEndPage = (int) Math.ceil(totalCount / (double) rowCount);
		int startPage = ((page - 1) / pageSize) * pageSize + 1;
		int endPage = startPage + pageSize - 1;
		if (endPage > realEndPage) {
			endPage = realEndPage;
		}
		boolean prev = startPage > 1;
		boolean next = endPage < realEndPage;

		// ===== JSP에 전달 =====
		request.setAttribute("faqList", faqList);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("page", page);
		request.setAttribute("rowCount", rowCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);

		// 검색 조건 유지
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchWord", searchWord);

		result.setPath("/app/admin/adminFaqList.jsp");
		result.setRedirect(false);

		return result;
	}
}
