package com.bapseguen.app.userMyPage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.view.ReviewWriteDTO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class WriteReviewOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("====WriteReviewOkController 실행====");
		System.out.println("====셀러에서 가져온거 전체적으로 수정해야함====");
		
		Result result = new Result();
		ReviewWriteDTO infodto = new ReviewWriteDTO();
		SellerMyPageDAO dao = new SellerMyPageDAO();
		
		HttpSession session = request.getSession();
		Integer memberNumber = (Integer)session.getAttribute("memberNumber");
		System.out.println("memnum : "+ memberNumber);
		
	    String ordersNumberstr = request.getParameter("ordersNumber");
	    int ordersNumber = Integer.parseInt(ordersNumberstr);
	    System.out.println("[controller] orderNum : "+ordersNumber );
	    
	    List<ReviewWriteDTO> infoList = dao.selectOrderList(ordersNumber);
	    System.out.println();
	    request.setAttribute("infoList", infoList);
	    request.setAttribute("ordersNumber", ordersNumber);
	    request.setAttribute("memberNumber", memberNumber);
	    
		
		result.setPath("/app/sellerMyPage/sellerwriteReview.jsp");
		result.setRedirect(false);
		return result;
		
		
	}
	

}
