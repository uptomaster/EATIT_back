package com.bapseguen.app.userMyPage;

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
import com.bapseguen.app.dto.view.MyPurchaseDTO;
import com.bapseguen.app.dto.view.ReviewWriteDTO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;
import com.bapseguen.app.userMyPage.dao.UserMyPageDAO;

public class WriteReviewController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("====WriteReviewController 실행====");
		
		Result result = new Result();
        UserMyPageDAO userMyPagedao = new UserMyPageDAO();

        HttpSession session = request.getSession();
        Integer memberNumber = (Integer) session.getAttribute("memberNumber");

        String ordersNumberStr = request.getParameter("ordersNumber");
        int ordersNumber = 0;
        if (ordersNumberStr != null && !ordersNumberStr.isEmpty()) {
            ordersNumber = Integer.parseInt(ordersNumberStr);
        }

        // 페이징 없는 전체 주문 조회
        List<MyPurchaseDTO> buylist = userMyPagedao.selectOrderItems(memberNumber, ordersNumber);

        request.setAttribute("buylist", buylist);
        request.setAttribute("ordersNumber", ordersNumber);
        request.setAttribute("memberNumber", memberNumber);

        result.setPath("/app/userMyPage/writeReview.jsp");
        result.setRedirect(false);
        return result;
		
	}
	

}
