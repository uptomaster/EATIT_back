package com.bapseguen.app.sellerMyPage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.view.ReviewWriteDTO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class sellerwriteReviewContorller implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("====sellerwriteReviewContorller 실행====");
		
		request.setCharacterEncoding("UTF-8");
		Result result = new Result();
		ReviewWriteDTO infodto = new ReviewWriteDTO();
		SellerMyPageDAO dao = new SellerMyPageDAO();
		System.out.println("세션 받아오기");
		HttpSession session = request.getSession();
		Integer memberNumber = (Integer)session.getAttribute("memberNumber");
		String businessNumber = (String) session.getAttribute("businessNumber");
		System.out.println("memnum : "+memberNumber + "  businNum : "+businessNumber);
		
		//memberNumber 값이 null이거나 0일때
		if (memberNumber == null || memberNumber == 0) {
		    response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script>");
		    out.println("alert('로그인이 필요합니다.');");
		    out.println("location.href='/app/login/login.jsp';");
		    out.println("</script>");
		    out.close();
		    return null;
		}
		// 가게 정보 보내기
		// 필수 파라미터
//	    int ordersNumber = Integer.parseInt(request.getParameter("ordersNumber"));
//	    int itemNumber  = Integer.parseInt(request.getParameter("itemNumber"));
	    String ordersNumberstr = request.getParameter("ordersNumber");
	    int ordersNumber = Integer.parseInt(ordersNumberstr);
	    System.out.println("[sellerwriteReviewContorller] orderNum : "+ordersNumber );
	    
	    List<ReviewWriteDTO> infoList = dao.selectOrderList(ordersNumber);
	    System.out.println("[sellerwriteReviewContorller] infoList  "+infoList);
	    request.setAttribute("infoList", infoList);
	    request.setAttribute("ordersNumber", ordersNumber);
	    request.setAttribute("businessNumber", businessNumber);
	    request.setAttribute("memberNumber", memberNumber);
	    
		
		result.setPath("/app/sellerMyPage/sellerwriteReview.jsp");
		result.setRedirect(false);
		return result;
	}
	
}
