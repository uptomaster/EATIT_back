package com.bapseguen.app.userMyPage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.userMyPage.dao.UserMyPageDAO;

public class CheckReviewController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("====CheckReviewController 실행====");
		
		response.setContentType("application/json; charset=UTF-8");

        HttpSession session = request.getSession();
        Integer memberNumber = (Integer) session.getAttribute("memberNumber");

        int ordersNumber = Integer.parseInt(request.getParameter("ordersNumber"));

        UserMyPageDAO dao = new UserMyPageDAO();
        int exists = dao.hasReview(ordersNumber, memberNumber);

        JSONObject json = new JSONObject();
        json.put("exists", exists > 0);

        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.close();
        
		return null;
        
	}
	
}
