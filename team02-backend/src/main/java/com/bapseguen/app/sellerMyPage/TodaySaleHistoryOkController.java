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

public class TodaySaleHistoryOkController implements Execute {
	
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("[판페] TodaySaleHistoryOkController 진입 성공 ===");

		// 1) 세션 → 판매자 식별 → 사업자번호 조회(프로젝트 기존 메서드 사용)
        HttpSession session = request.getSession(false);
        Integer memberNumber = (session != null) ? (Integer) session.getAttribute("memberNumber") : null;

        SellerMyPageDAO sellerMyPageDAO = new SellerMyPageDAO();
        String businessNumber = sellerMyPageDAO.getBusinessNumber(memberNumber);

        // 2) 페이징 파라미터 (기존 페이지네이션 규칙)
        int page = 1;
        int rowCount = 10;   // 페이지 당 행 수
        int pageCount = 10;  // 페이지 블록 크기
        try { page = Integer.parseInt(request.getParameter("page")); } catch (Exception ignore) {}

        int startRow = (page - 1) * rowCount + 1;
        int endRow   = startRow + rowCount - 1;

        // 3) Mapper로 넘길 파라미터 Map (여기서 세팅 끝)
        Map<String, Object> p = new HashMap<>();
        p.put("businessNumber", businessNumber);
        p.put("startRow", startRow);
        p.put("endRow", endRow);

        // 4) 오늘 판매내역(결제완료, 당일) 조회
        int total = sellerMyPageDAO.todaySaleCount(p);                  // storeManage.todaySaleCount
        List<SaleHistoryDTO> saleList = sellerMyPageDAO.todaySaleList(p); // storeManage.todaySaleList

        // 5) 페이지네이션 계산 (네 기존 계산식 유지)
        int realEndPage = (int) Math.ceil(total / (double) rowCount);
        int startPage = ((page - 1) / pageCount) * pageCount;
        int endPageForView = Math.min(startPage + pageCount, realEndPage);
        boolean prev = startPage > 0;
        boolean next = endPageForView < realEndPage;

        // 6) 요약 카드(오늘/이번달/누적) — PAID 기준 합계
        Map<String, Object> summary = sellerMyPageDAO.saleSummary(businessNumber);

        // 7) 뷰에 전달
        request.setAttribute("saleList", saleList);
        request.setAttribute("summary", summary);

        request.setAttribute("page", page);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPageForView);
        request.setAttribute("prev", prev);
        request.setAttribute("next", next);
        request.setAttribute("totalCount", total);
        request.setAttribute("tab", "today");

        // 8) 포워딩 (반드시 .se 경로 체계 준수)
        Result result = new Result();
        result.setPath("/app/sellerMyPage/todaySaleList.jsp");
        return result;
	}
	

}