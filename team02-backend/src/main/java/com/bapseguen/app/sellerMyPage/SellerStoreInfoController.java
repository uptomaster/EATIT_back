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
import com.bapseguen.app.dto.view.ItemWithImgDTO;
import com.bapseguen.app.dto.view.SellerInfoDTO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class SellerStoreInfoController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			System.out.println("==== SellerStoreInfoController 실행 ====");
	        
	        // DAO 객체 생성
	        SellerMyPageDAO sellerDAO = new SellerMyPageDAO();
	        
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
	        String temp = request.getParameter("page");
			int page = (temp == null) ? 1 : Integer.valueOf(temp); // 페이지 번호 기본값 1로 설정
			int rowCount = 4; // 한 페이지당 게시글 수
			int pageCount = 5; // 페이지 버튼 수

			// 페이징 처리
			int startRow = (page - 1) * rowCount + 1;
			int endRow = startRow + rowCount - 1;

			Map<String, Object> foodpageMap = new HashMap<>();
			foodpageMap.put("startRow", startRow);
			foodpageMap.put("endRow", endRow);
			foodpageMap.put("businessNumber", businessNumber);
			
			Map<String, Object> ingrepageMap = new HashMap<>();
			ingrepageMap.put("startRow", startRow);
			ingrepageMap.put("endRow", endRow);
			foodpageMap.put("businessNumber",businessNumber);

	        
	        // 가게 정보 조회
//	        SellerInfoDTO storeInfo = sellerDAO.takeSellerInfoDTO(businessNumber);
//	        request.setAttribute("storeInfo", storeInfo);
	        
	         //음식 판매 목록 조회
	        List<ItemWithImgDTO> foodList = sellerDAO.foodList(foodpageMap);
	        System.out.println("foodList : "+foodList);
			request.setAttribute("foodList", foodList); // null 오류 수정
			// 음식 판매 목록 조회
			List<ItemWithImgDTO> ingreList = sellerDAO.ingredientList(ingrepageMap);
			System.out.println("ingreList : "+ingreList);
			request.setAttribute("ingreList", ingreList); // null 오류 수정

	        //
	        // request에 데이터 저장
//	        request.setAttribute("storeInfo", storeInfo);
//	        request.setAttribute("foodList", foodList);
	        
	        // 결과 설정
	        Result result = new Result();
	        result.setPath("/app/sellerMyPage/storeInfo.jsp");
//	        result.setRedirect(false);
	        
	        return result;
	}
	

}
