package com.bapseguen.app.sellerMyPage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.StoreImageDTO;
import com.bapseguen.app.dto.view.ReviewWithUserDTO;
import com.bapseguen.app.dto.view.SellerInfoDTO;
import com.bapseguen.app.img.dao.StoreImageDAO;
import com.bapseguen.app.review.dao.ReviewDAO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class SellerStoreReviewController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			System.out.println("==== SellerStoreReviewController 실행 ====");
	        
	        // DAO 객체 생성
	        SellerMyPageDAO sellerDAO = new SellerMyPageDAO();
	        StoreImageDAO imageDAO = new StoreImageDAO();
	        ReviewDAO reviewDAO = new ReviewDAO();
	        
	        // 로그인된 사업자 번호를 세션에서 가져오기
	        HttpSession session = request.getSession(); // 기존 세션만 사용
	        String businessNumber = (String) session.getAttribute("businessNumber");
//	        int businessNumberInt = Integer.valueOf(businessNumber);
//	        System.out.println("사업자 번호 "+businessNumberInt+ businessNumber.typeof());
	        Integer memberNumber = (Integer) session.getAttribute("memberNumber");
	        System.out.println("[사업장관리] 사업자번호: "+businessNumber);
	        System.out.println("[사업장관리] 세션: "+session);
	        
	        // 세션 가져오기 실패로 주석처리함
	        if (session == null || session.getAttribute("businessNumber") == null) {
	            // 세션이 만료되었거나 로그인 상태가 아니면 로그인으로 리다이렉트
	            Result r = new Result();
	            r.setRedirect(true);
	            r.setPath(request.getContextPath() + "/login/login.lo");
	            return r;
	        }
			// -------------------- 페이징 --------------------
			int page = 1;
			int limit = 5; // 한 페이지당 리뷰 개수
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException ignore) {
			}

			int offset = (page - 1) * limit;

			// 전체 리뷰 개수
			int totalCount = reviewDAO.countReviewsByBusiness(businessNumber);
			int maxPage = (int) Math.ceil((double) totalCount / limit);

			// -------------------- DAO 호출 --------------------
			// 가게 정보 조회
	        SellerInfoDTO storeInfo = sellerDAO.selectSellerInfo(memberNumber);
	        System.out.println("storeInfo : "+storeInfo);
	        request.setAttribute("storeInfo", storeInfo);
	        
	        //가게 이미지 조회
	        StoreImageDTO images = imageDAO.selectone(businessNumber);
			System.out.println("images : "+images);
			request.setAttribute("images", images); // null 오류 수정		
			
			List<ReviewWithUserDTO> reviews = reviewDAO.selectReviewsByBusiness(businessNumber);

			int start = Math.min(offset, reviews.size());
			int end = Math.min(offset + limit, reviews.size());
			List<ReviewWithUserDTO> pageList = reviews.subList(start, end);

			// -------------------- 바인딩 --------------------
			double avgRating = reviewDAO.selectAvgRatingByBusiness(businessNumber);
			request.setAttribute("avgRating", avgRating);

			request.setAttribute("businessNumber", businessNumber);
			request.setAttribute("reviews", pageList);
			request.setAttribute("page", page);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("totalCount", totalCount);

			// 가게 정보 (리뷰 DTO에 storeName, storeTel, storeAddress 포함됨)
			if (!reviews.isEmpty()) {
				request.setAttribute("storeName", reviews.get(0).getStoreName());
				request.setAttribute("storeTel", reviews.get(0).getStoreTel());
				request.setAttribute("storeAddress", reviews.get(0).getStoreAddress());
			}
			System.out.println("====페이징정보 확인====");
			System.out.println("storeInfo : " + storeInfo);
			System.out.println("storeimages : " + images);
			System.out.println("reviews : " + pageList);
			System.out.println("startPage : " + page + ", endPage : " + maxPage );
			System.out.println("====================");
			
	        // 결과 설정
	        Result result = new Result();
	        result.setPath("/app/sellerMyPage/storeReview.jsp");
//	        result.setRedirect(false);
	        
	        return result;
	}

	
}
