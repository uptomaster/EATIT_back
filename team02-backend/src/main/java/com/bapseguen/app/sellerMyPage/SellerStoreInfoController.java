package com.bapseguen.app.sellerMyPage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
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
	        Integer memberNumber = (Integer) session.getAttribute("memberNumber");
	        System.out.println("[사업장관리] 사업자번호: "+businessNumber);
	        System.out.println("[사업장관리] 세션: "+session);
	        
	        // 세션 가져오기 실패로 주석처리함
//	        if (session == null || session.getAttribute("businessNumber") == null) {
//	            // 세션이 만료되었거나 로그인 상태가 아니면 로그인으로 리다이렉트
//	            Result r = new Result();
//	            r.setRedirect(true);
//	            r.setPath(request.getContextPath() + "/login/login.lo");
//	            return r;
//	        }


	        
	        // 가게 정보 조회
//	        SellerInfoDTO storeInfo = sellerDAO.selectStoreInfo(businessNumber);
	        
	        // 음식 판매 목록 조회
	        List<Map<String, Object>> foodList = sellerDAO.foodList(businessNumber);
	        System.out.println("foodList : "+foodList);
	        
	        // request에 데이터 저장
//	        request.setAttribute("storeInfo", storeInfo);
//	        request.setAttribute("foodList", foodList);
	        
	        // 결과 설정
	        Result result = new Result();
	        result.setPath("/app/sellerMyPage/storeInfo.jsp");
	        result.setRedirect(false);
	        
	        return result;
	}
	

}
