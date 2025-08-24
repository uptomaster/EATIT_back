package com.bapseguen.app.findPw;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.findPw.dao.FindPwDAO;

public class FindPwOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Result result = new Result();
		FindPwDAO findPwDAO = new FindPwDAO();
		
		String id = request.getParameter("findPw_input_id");
		String phone = request.getParameter("findPw_input_phone");
		Integer pw = findPwDAO.findPw(id, phone);
		result.setRedirect(true);
		if(id == null) {
			result.setPath(request.getContextPath()+ "/app/findPw/findPwFail.jsp");
		}else{
			result.setPath(request.getContextPath() + "/app/login/login.jsp");
		}
		return result;
	}

	
}
