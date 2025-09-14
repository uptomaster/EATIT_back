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
import com.bapseguen.app.dto.view.SaleHistoryDTO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class TotalSaleHistoryOkController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		System.out.println("[판페] SellerMyPostController 진입 성공 ===");
		
		HttpSession session = request.getSession(false);
	    Integer memberNumber = (session != null) ? (Integer) session.getAttribute("memberNumber") : null;
	    String businessNumber = new SellerMyPageDAO().getBusinessNumber(memberNumber);

	    String from = request.getParameter("from"); // YYYY-MM-DD
	    String to   = request.getParameter("to");   // YYYY-MM-DD
	    String status = request.getParameter("status");
	    String q = request.getParameter("q");

	    int page = 1, rowCount = 10, pageCount = 10;
	    try { page = Integer.parseInt(request.getParameter("page")); } catch(Exception ignore){}
	    int startRow = (page - 1) * rowCount + 1;
	    int endRow   = startRow + rowCount - 1;

		SellerMyPageDAO dao = new SellerMyPageDAO();
	    int total = dao.totalSaleCount(businessNumber, from, to, status, q);
	    List<SaleHistoryDTO> saleList = dao.totalSaleList(businessNumber, from, to, startRow, endRow, status, q);

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
	    request.setAttribute("tab", "total");
	    request.setAttribute("summary", new SummaryService().buildSummary(businessNumber));

	    Result result = new Result();
	    result.setPath("/app/sellerMyPage/totalSales.jsp");
	    return result;
	}
}
