package com.bapseguen.app.userMyPage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.userMyPage.dao.UserMyPageDAO;

public class EditUserInfoOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("====EditUserInfoOkController 실행====");
		
		
		HttpSession session = request.getSession();
		int memberNumber = (int) session.getAttribute("memberNumber");
		String newPassword = request.getParameter("newPassword");
		String newPhone = request.getParameter("newPhone");
		
		System.out.println("memberNumber: " + memberNumber);
		System.out.println("newPassword: " + newPassword);
		System.out.println("newPhone: " + newPhone);

		UserMyPageDAO userMyPageDao = new UserMyPageDAO();
		int updateCount = 0;

		// 파라미터 Map 생성
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("memberNumber", memberNumber);

		if (newPassword != null && !newPassword.isEmpty() && !"null".equals(newPassword)) {
		    paramMap.put("memberPassword", newPassword);
		    int result = userMyPageDao.updatePassword(paramMap);
		    updateCount += result;
		}

		if (newPhone != null && !newPhone.isEmpty() && !"null".equals(newPhone)) {
		    paramMap.put("memberPhoneNumber", newPhone);
		    int result = userMyPageDao.updatePhone(paramMap);
		    updateCount += result;
		}
		
		

		// JSON 응답
		response.setContentType("application/json; charset=UTF-8");
		String jsonResult = updateCount > 0 ? "{\"status\":\"success\"}" : "{\"status\":\"fail\"}";
		response.getWriter().write(jsonResult);
		
		Result result = new Result(); // 최소 반환용
		return result;
	}
}
