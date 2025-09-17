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
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class sellerwriteReviewOkContorller implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Result result = new Result();
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

		// 필요한 최소 파라미터만 받기
		int ordersNumber = Integer.parseInt(request.getParameter("ordersNumber"));
		System.out.println("ordersNumber " + ordersNumber);
		String businessNumber = request.getParameter("businessNumber");
		System.out.println("businessNumber " + businessNumber);
		
		System.out.println("reviewRating1 "+request.getParameter("reviewRating"));
		int reviewRating = Integer.parseInt(request.getParameter("reviewRating"));
		System.out.println("reviewRating2 "+reviewRating);
		
		// int reviewRating = 3; 
		String reviewContent = request.getParameter("reviewContent");
		System.out.println("reviewContent " + reviewContent);
		String itemType = request.getParameter("itemType");
		System.out.println("itemType " + itemType);

		SellerMyPageDAO dao = new SellerMyPageDAO();
		ReviewWriteDTO dto = new ReviewWriteDTO();

		// INSERT DTO 구성해서 그대로 저장
		dto.setOrdersNumber(ordersNumber);
		dto.setBusinessNumber(businessNumber);
		dto.setMemberNumber(memberNumber);
		dto.setReviewRating(reviewRating);
		dto.setReviewContent(reviewContent);
		System.out.println("[okcontroller] " + dto);

		dao.insertReview(dto);

		// 5) 완료 후 이동 (원하는 목적지로 교체 가능)
		String path = "/sellerMyPage/sellerfoodPurchaseList.se";
		if (itemType.equals("FOOD")) {
			path = "/sellerMyPage/sellerfoodPurchaseList.se";
		} else if (itemType.equals("INGREDIENT")) {
			path = "/sellerMyPage/selleringredientPurchaseList.se";
		}

		System.out.println("[sellerwriteReviewOkContorller] 지정한 path : " + path);
		result.setPath(path);
		return result;
	}

}
