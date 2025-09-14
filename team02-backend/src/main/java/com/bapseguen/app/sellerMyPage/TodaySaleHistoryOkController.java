package com.bapseguen.app.sellerMyPage;

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
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class TodaySaleHistoryOkController implements Execute {
	
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("[판페] SellerMyReviewController 진입 성공 ===");
		
		HttpSession session = request.getSession(false);
	    Integer memberNumber = (session != null) ? (Integer) session.getAttribute("memberNumber") : null;

	    // businessNumber 조회 (이미 가지고 있다면 생략)
	    String businessNumber = new SellerMyPageDAO().getBusinessNumber(memberNumber);

	    // params
	    String q = request.getParameter("q");
	    String status = request.getParameter("status"); // 선택: today 페이지에 상태 필터를 쓰려면 폼에 select 추가

	    // pagination
	    int page = 1;
	    int rowCount = 10;   // 페이지 당 행 수
	    int pageCount = 10;  // 페이지 블록 크기
	    try { page = Integer.parseInt(request.getParameter("page")); } catch(Exception ignore){}

	    int startRow = (page - 1) * rowCount + 1;
	    int endRow   = startRow + rowCount - 1;

	    SellerMyPageDAO dao = new SellerMyPageDAO();
	    int total = dao.todaySaleCount(businessNumber, status, q);
	    List<SaleHistoryDTO> saleList = dao.todaySaleList(businessNumber, startRow, endRow, status, q);

	    // 페이지네이션 계산 (네가 쓰던 로직과 동일하게 맞춰둠)
	    int realEndPage = (int)Math.ceil(total / (double)rowCount);
	    int startPage = ((page - 1) / pageCount) * pageCount;
	    int endPage = Math.min(startPage + pageCount, realEndPage);
	    boolean prev = startPage > 0;
	    boolean next = endPage < realEndPage;

	    request.setAttribute("saleList", saleList);
	    request.setAttribute("page", page);
	    request.setAttribute("startPage", startPage);
	    request.setAttribute("endPage", endPage);
	    request.setAttribute("prev", prev);
	    request.setAttribute("next", next);
	    request.setAttribute("totalCount", total);
	    request.setAttribute("tab", "today");

	    // 요약 카드 금액 계산(원하면 DAO로 합계 쿼리 제공)
	    request.setAttribute("summary", new SummaryService().buildSummary(businessNumber));

	    Result result = new Result();
	    result.setPath("/app/sellerMyPage/todaySales.jsp");
	    return result;
	}
	

}