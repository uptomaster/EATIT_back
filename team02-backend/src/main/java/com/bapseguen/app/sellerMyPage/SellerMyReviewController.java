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
import com.bapseguen.app.dto.view.ReviewWriteDTO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class SellerMyReviewController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("[판페] SellerMyReviewController 진입 성공 ===");
		SellerMyPageDAO sellerDAO = new SellerMyPageDAO();
		HttpSession session = request.getSession(false);
		int memberNumber = (int) session.getAttribute("memberNumber");
		System.out.println("세션의 회원번호 : "+memberNumber);
			
		
		Result result = new Result();
		String temp = request.getParameter("page"); // 목록의 현재 페이지 네이션 위치
		int page = (temp == null) ? 1 :Integer.valueOf(temp); //페이지 번호의 기본값을 1로 설정하겠다
		int rowCount = 10; // 한 페이지에서 보여지는 목록 내용의 개수
		int pageCount = 5; //페이지네이션 한묶음의 개수
		
		// 한 페이지에 보여지는 게시글 시작, 끝 계산
		int startRow = (page - 1) * rowCount + 1; // 시작행(1, 11, 21, ..)
		int endRow = startRow + rowCount - 1; // 끝 행(10, 20, 30, ..)

		Map<String, Integer> pageMap = new HashMap<>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		pageMap.put("memberNumber", memberNumber);
		
		// 게시글 목록 조회
		List<ReviewWriteDTO> myReviewList = sellerDAO.selectAllmyReview(pageMap);
		request.setAttribute("myReviewList", myReviewList);

		// 페이징 정보 설정
		// BoardMapper.xml의 getTotal을 이용하여 전체 게시글 개수 조회
		// 실제 마지막 페이지 번호(realEndPage)를 계산함

		int total = sellerDAO.myReviewCount(pageMap);
		int realEndPage = (int) Math.ceil(total / (double) rowCount); // 실제 마지막 페이지(전체 게시글 기준으로 계산)
		int endPage = (int) (Math.ceil(page / (double) pageCount) * pageCount); // 현재 페이지 그룹에서의 마지막 페이지
		int startPage = endPage - (pageCount - 1); // 현재 페이지 그룹에서의 첫 페이지

		// endPage가 실제 존재하는 마지막 페이지보다 크면 조정
		endPage = Math.min(endPage, realEndPage);

		// prev, next 버튼 활성화 여부 확인
		boolean prev = startPage > 1;
		boolean next = endPage < realEndPage;

		request.setAttribute("page", page);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);

		System.out.println("====페이징정보 확인====");
		System.out.println("pageMap : " + pageMap);
		System.out.println("myReviewList : " + myReviewList);
		System.out.println("startPage : " + startPage + ", endPage : " + endPage + ", prev : " + prev + ", next : " + next);
		System.out.println("====================");

		result.setPath("/app/sellerMyPage/sellerMyReviewsList.jsp");
		result.setRedirect(false);

		return result;

	}
	
}
