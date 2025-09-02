package com.bapseguen.app.sellerMyPage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.ItemImageDTO;
import com.bapseguen.app.dto.ItemListDTO;
import com.bapseguen.app.img.dao.ItemImageDAO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class FoodDetailOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("FoodDetailOkController 진입 성공 ===");
    	Result result = new Result();
		
		String itemNumberStr = request.getParameter("itemNumber"); //!!!!!!!!어디서 주어야 하는지 
		System.out.println("[FoodDetailOkController] : "+itemNumberStr);
		
		//itemNumber가 빈 문자열이거나 null인경우
		if(itemNumberStr == null || itemNumberStr.trim().isEmpty()){
			System.out.println("[FoodDetailOkController] itemNumberStr 값이 없습니다");
			result.setPath("/sellerMyPage/storeInfo.se"); //게시글 목록 페이지로 리다이렉트
			result.setRedirect(true);
			return result;
		}
		
		int itemNumber = Integer.parseInt(itemNumberStr); ////!!!!!!!!!!!!!!!!!!!!!
		
		SellerMyPageDAO sellerDAO = new SellerMyPageDAO();
		ItemImageDAO fileDAO = new ItemImageDAO();

		//DB에서 게시글 가져오기
		ItemListDTO ItemListDTO = sellerDAO.detaileItem(itemNumber);
		
		//게시글이 존재하지 않을 경우 처리
		if(ItemListDTO == null) {
			System.out.println("[FoodDetailOkController] 존재하지 않는 게시글입니다. " + itemNumber);
			result.setPath("/sellerMyPage/storeInfo.se");
			result.setRedirect(true);
			return result;
		}
		
		//첨부파일 가져오기
		List<ItemImageDTO> files = fileDAO.select(itemNumber);
		System.out.println("======파일 확인======");
		System.out.println(files);
		System.out.println("===================");
		
		//첨부파일 붙이기
		ItemListDTO.setFiles(files);
		
		//로그인한 사용자 번호 가져오기
		Integer currItemNumber = (Integer) request.getSession().getAttribute("itemNumber");
		System.out.println("선택한 메뉴 번호 : " + currItemNumber);
		
		
		request.setAttribute("item", ItemListDTO);
		result.setPath("app/sellerMyPage/foodSalesView.jsp");
		result.setRedirect(false);
		
		return result;
    }
}