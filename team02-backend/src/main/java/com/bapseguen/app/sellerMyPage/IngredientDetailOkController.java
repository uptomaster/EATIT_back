package com.bapseguen.app.sellerMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.ItemDTO;
import com.bapseguen.app.dto.ItemImageDTO;
import com.bapseguen.app.dto.ItemListDTO;
import com.bapseguen.app.img.dao.ItemImageDAO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class IngredientDetailOkController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("FoodDetailOkController 진입 성공 ===");
    	Result result = new Result();
		
		String itemNumberStr = request.getParameter("itemNumber"); //!!!!!!!!어디서 주어야 하는지 
		System.out.println("[IngredientDetailOkController] : "+itemNumberStr);
		
		//itemNumber가 빈 문자열이거나 null인경우
		if(itemNumberStr == null || itemNumberStr.trim().isEmpty()){
			System.out.println("[IngredientDetailOkController] itemNumberStr 값이 없습니다");
			result.setPath("/sellerMyPage/storeIngre.se"); //게시글 목록 페이지로 리다이렉트
			result.setRedirect(true);
			return result;
		}
		
		int itemNumber = Integer.parseInt(itemNumberStr); //null 로 들어와서 형변환 실패했던 것
		
		SellerMyPageDAO sellerDAO = new SellerMyPageDAO();
		ItemImageDAO fileDAO = new ItemImageDAO();

		//DB에서 게시글 가져오기
		ItemListDTO ItemListDTO = sellerDAO.detailItemList(itemNumber);
		
		//게시글이 존재하지 않을 경우 처리
		if(ItemListDTO == null) {
			System.out.println("[IngredientDetailOkController] 존재하지 않는 게시글입니다. " + itemNumber);
			result.setPath("/sellerMyPage/storeInfo.se");
			result.setRedirect(true);
			return result;
		}
		
		//첨부파일 가져오기
		ItemImageDTO files = fileDAO.selectone(itemNumber);
		System.out.println("======파일 확인======");
		System.out.println(files);
		System.out.println("===================");
		
		//첨부파일 붙이기
		ItemListDTO.setImg(files);
		
		//이 페이지의 메뉴 번호 가져오기
		System.out.println("선택한 메뉴 번호 : " + itemNumber);
		// 보내기전 DTO 확인
		System.out.println(ItemListDTO);
		
		request.setAttribute("item", ItemListDTO);
		request.setAttribute("itemImage", files);
		
		result.setPath("/app/sellerMyPage/ingredientSalesView.jsp");
		result.setRedirect(false);
		
		return result;
	}
}