package com.bapseguen.app.userMyPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.userMyPage.dao.UserMyPageDAO;

public class CheckPwOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//System.out.println("====CheckPwOkController 실행====");
    	HttpSession session = request.getSession(false);
        Integer memberNumber = (session != null) ? (Integer) session.getAttribute("memberNumber") : null;
        String inputPw = request.getParameter("currentPassword");

        boolean ok = false;
        if(memberNumber != null && inputPw != null){
            UserMyPageDAO userMyPageDAO = new UserMyPageDAO();
            ok = userMyPageDAO.checkPassword(memberNumber, inputPw);
        }

        // JSON 직접 출력
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write("{\"result\":\"" + (ok ? "success" : "fail") + "\"}");
        return null; // JSON 출력했으니 Result는 필요 없음
	
	}
	
}
