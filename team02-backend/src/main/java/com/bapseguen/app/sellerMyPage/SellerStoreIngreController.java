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
import com.bapseguen.app.dto.StoreImageDTO;
import com.bapseguen.app.dto.view.ItemWithImgDTO;
import com.bapseguen.app.dto.view.SellerInfoDTO;
import com.bapseguen.app.img.dao.StoreImageDAO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class SellerStoreIngreController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			System.out.println("==== SellerStoreIngreController 실행 ====");
	        
	        // DAO 객체 생성
	        SellerMyPageDAO sellerDAO = new SellerMyPageDAO();
	        StoreImageDAO imageDAO = new StoreImageDAO();
	        
	        // 로그인된 사업자 번호를 세션에서 가져오기
	        HttpSession session = request.getSession(); // 기존 세션만 사용
	        String businessNumber = (String) session.getAttribute("businessNumber");
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
	        String temp = request.getParameter("page");
			int page = (temp == null) ? 1 : Integer.valueOf(temp); // 페이지 번호 기본값 1로 설정
			int rowCount = 4; // 한 페이지당 게시글 수
			int pageCount = 5; // 페이지 버튼 수

			// request에 데이터 저장
			int foodListCount = sellerDAO.foodCount(businessNumber);
			int ingreListCount = sellerDAO.ingredientCount(businessNumber);
			
	        request.setAttribute("foodListCount", foodListCount);
	        request.setAttribute("ingreListCount", ingreListCount);
			
			int realEndPage = (int) Math.ceil(foodListCount / (double) rowCount); // 실제 마지막 페이지(전체 게시글 기준으로 계산)
			int endPage = (int) (Math.ceil(page / (double) pageCount) * pageCount); // 현재 페이지 그룹에서의 마지막 페이지
			int startPage = endPage - (pageCount - 1); // 현재 페이지 그룹에서의 첫 페이지

			// endPage가 실제 존재하는 마지막 페이지보다 크면 조정
			endPage = Math.min(endPage, realEndPage);
			
			// 페이징 처리
			int startRow = (page - 1) * rowCount + 1; // 시작행(1, 11, 21, ..)
			int endRow = startRow + rowCount - 1; // 끝 행(10, 20, 30, ..)

			
			Map<String, Object> foodpageMap = new HashMap<>();
			foodpageMap.put("startRow", startRow);
			foodpageMap.put("endRow", endRow);
			foodpageMap.put("businessNumber", (String) session.getAttribute("businessNumber"));
			
			Map<String, Object> ingrepageMap = new HashMap<>();
			ingrepageMap.put("startRow", startRow);
			ingrepageMap.put("endRow", endRow);
			ingrepageMap.put("businessNumber",(String) session.getAttribute("businessNumber"));

			
			// 가게 정보 조회
	        SellerInfoDTO storeInfo = sellerDAO.selectSellerInfo(memberNumber);
	        System.out.println("storeInfo : "+storeInfo);
	        request.setAttribute("storeInfo", storeInfo);
	        
	        //가게 이미지 조회
	        StoreImageDTO images = imageDAO.selectone(businessNumber);
			System.out.println("images : "+images);
			request.setAttribute("images", images); // null 오류 수정			
			
	         //음식 판매 목록 조회
	        List<ItemWithImgDTO> foodList = sellerDAO.foodList(foodpageMap);
	        System.out.println("foodList : "+foodList);
			request.setAttribute("foodList", foodList); // null 오류 수정
			// 음식 판매 목록 조회
			List<ItemWithImgDTO> ingreList = sellerDAO.ingredientList(ingrepageMap);
			System.out.println("ingreList : "+ingreList);
			request.setAttribute("ingreList", ingreList); // null 오류 수정

			// prev, next 버튼 활성화 여부 확인
			boolean prev = startPage > 1;
			boolean next = endPage < realEndPage;

			request.setAttribute("page", page);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("prev", prev);
			request.setAttribute("next", next);

			System.out.println("====페이징정보 확인====");
			System.out.println("pageMap : " + ingrepageMap);
			System.out.println("ingreList : " + ingreList);
			System.out.println("startPage : " + startPage + ", endPage : " + endPage + ", prev : " + prev + ", next : " + next);
			System.out.println("====================");
			
	        // 결과 설정
	        Result result = new Result();
	        result.setPath("/app/sellerMyPage/storeIngre.jsp");
//	        result.setRedirect(false);
	        
	        return result;
	}
	

}
