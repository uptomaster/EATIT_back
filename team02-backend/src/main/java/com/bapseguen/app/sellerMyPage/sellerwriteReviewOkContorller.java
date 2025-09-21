package com.bapseguen.app.sellerMyPage;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.ItemImageDTO;
import com.bapseguen.app.dto.view.ReviewWriteDTO;
import com.bapseguen.app.img.dao.ItemImageDAO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;
import com.bapseguen.app.userMyPage.dao.UserMyPageDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class sellerwriteReviewOkContorller implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("====sellerwriteReviewOkContorller 실행====");

		request.setCharacterEncoding("UTF-8");

		// 메뉴 정보 관련
		ReviewWriteDTO reviewDTO = new ReviewWriteDTO();
		SellerMyPageDAO sellerDAO = new SellerMyPageDAO();

		// 1) 세션 정보 확인하기
//		System.out.println(""+multipartRequest.get);
		// 로그인 한 회원 정보 가져오기
		Integer memberNumber = (Integer) request.getSession().getAttribute("memberNumber");
		System.out.println("회원번호 : " + memberNumber);

		if (memberNumber == null) {
			System.out.println("오류 : 로그인된 사용자가 없습니다");
			response.sendRedirect("/login/login.lo");
			return null;
		}
		System.out.println("로그인 확인은 벗어남");

		// ordersNumber 안전하게 받기
        String ordersNumberStr = request.getParameter("ordersNumber");
        int ordersNumber = 0;
        if (ordersNumberStr != null && !ordersNumberStr.isEmpty()) {
            ordersNumber = Integer.parseInt(ordersNumberStr);
        } else {
            // 없으면 예외 처리하거나 이전 페이지로 돌려보낼 수 있음
            ordersNumber = -1; // 임시 처리
        }

        String businessNumber = request.getParameter("businessNumber");
        if (businessNumber == null) {
            businessNumber = ""; // 기본값 처리
        }

        // reviewRating 안전하게 받기
        String ratingStr = request.getParameter("reviewRating");
        int reviewRating = 0;
        if (ratingStr != null && !ratingStr.isEmpty()) {
            reviewRating = Integer.parseInt(ratingStr);
        } else {
            reviewRating = 1; // 기본값 1~5
        }

        String reviewContent = request.getParameter("reviewContent");
        if (reviewContent == null) reviewContent = "";

     // DTO 세팅
        ReviewWriteDTO review = new ReviewWriteDTO();
        review.setMemberNumber(memberNumber);
        review.setOrdersNumber(ordersNumber);
        review.setBusinessNumber(businessNumber);
        review.setReviewRating(reviewRating);
        review.setReviewContent(reviewContent);

		System.out.println("[okcontroller] " + review);
		
		// DB 저장
        SellerMyPageDAO dao = new SellerMyPageDAO();
        int result = dao.insertReview(review);

		 // 결과 반환
        Result res = new Result();
        if(result > 0) {
            // 리뷰 작성 성공 → 내 리뷰 목록 페이지로 이동
            res.setPath(request.getContextPath() + "/userMyPage/myReviewListOk.my");
            res.setRedirect(true);
        } else {
            // 실패 → 이전 페이지로 돌아가기
            res.setPath(request.getHeader("Referer"));
            res.setRedirect(true);
        }
		return res;
	}

}
