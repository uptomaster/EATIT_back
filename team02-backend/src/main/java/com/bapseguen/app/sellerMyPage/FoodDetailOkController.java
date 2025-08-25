package com.bapseguen.app.sellerMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.view.ItemWithImgDTO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class FoodDetailOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("FoodDetailOkController 진입 성공 ===");
    	ItemWithImgDTO ItemWithImgDTO = new ItemWithImgDTO();
    	SellerMyPageDAO  sellerDAO = new SellerMyPageDAO();
    	Result result = new Result();
//    	String path = null;
    	HttpSession session = request.getSession(false);
//    	int businuessNumber = (int) session.getAttribute("busineeNumber");

    	// 만약 게시글이 존재하지 않는다면
    	String itemNumberstr = request.getParameter("itemNumber");
    	itemNumberstr = "7"; // 임시 더미값 선정
    	if(itemNumberstr == null || itemNumberstr.trim().isEmpty()){
    		System.out.println("존재하지 않는 상품입니다.");
    		result.setPath("/SellerMyPage/storeInfo.se"); // 사업장 관리 페이지로 돌아감
    		result.setRedirect(true);
    		return result;
    	}
    	// 게시글이 존재하는 경우
    	int itemNumber = Integer.parseInt(itemNumberstr);
    	//DB에서 메뉴 가져오기
    	ItemWithImgDTO = sellerDAO.detaileFood(itemNumber);
    	System.out.println(ItemWithImgDTO);
    	
    	
        request.setAttribute("item", ItemWithImgDTO);
        result.setRedirect(false);
        result.setPath("/app/sellerMyPage/foodSalesView.jsp");
        return result;
    }
}