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
	
		System.out.println("[판페] TotalSaleHistoryOkController 진입 성공 ===");
		
        // 1) 로그인 세션 → 판매자 식별 → 사업자번호 조회(프로젝트 기존 방식 재사용)
        HttpSession session = request.getSession(false);
        Integer memberNumber = (session != null) ? (Integer) session.getAttribute("memberNumber") : null;

        SellerMyPageDAO sellerMyPageDAO = new SellerMyPageDAO();
        String businessNumber = sellerMyPageDAO.getBusinessNumber(memberNumber);
        System.out.println("businessNumber : "+ businessNumber);
        
        // 2) 페이징 파라미터
        int page = 1;
        int rowCount = 10;   // 페이지 당 행 수
        int pageCount = 10;  // 페이지 블록(네가 쓰던 값)
        try { page = Integer.parseInt(request.getParameter("page")); } catch (Exception ignore) {}

        int startRow = (page - 1) * rowCount + 1;
        int endRow   = startRow + rowCount - 1;

        // 3) Mapper로 넘길 파라미터(Map) — 컨트롤러에서 세팅 끝
        Map<String, Object> p = new HashMap<>();
        p.put("businessNumber", businessNumber);
        p.put("startRow", startRow);
        p.put("endRow", endRow);

        // 4) 전체 판매내역(결제완료 고정) 조회
        int total = sellerMyPageDAO.salesHistoryCount(p);              // storeManage.salesHistoryCount
        List<SaleHistoryDTO> saleList = sellerMyPageDAO.salesHistoryList(p); // storeManage.salesHistoryList

        // 5) 페이지네이션 계산 (기존 구조 유지)
        int realEndPage = (int) Math.ceil(total / (double) rowCount);
        int endPage = (int) (Math.ceil(page / (double) pageCount) * pageCount);
        int startPage = endPage - (pageCount - 1);

        // endPage 보정
        endPage = Math.min(endPage, realEndPage);

        // prev / next 버튼 여부
        boolean prev = startPage > 1;
        boolean next = endPage < realEndPage;

        // 6) 요약 카드(오늘/이번달/누적) — ‘PAID’ 기준 합계
        Map<String, Object> summary = sellerMyPageDAO.saleSummary(businessNumber);
        System.out.println("summary : "+summary);
        
        // 7) JSP에 전달
        request.setAttribute("saleList", saleList);
        request.setAttribute("summary", summary);

        request.setAttribute("page", page);
        request.setAttribute("startPage", startPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("prev", prev);
        request.setAttribute("next", next);
        request.setAttribute("totalCount", total);
        request.setAttribute("tab", "total"); // 탭 하이라이트용

        // 8) 포워딩 (.se 경로 필수, JSP는 div-only 버전 사용)
        Result result = new Result();
        result.setPath("/app/sellerMyPage/salesHistoryList.jsp");
        return result;
	}
}
