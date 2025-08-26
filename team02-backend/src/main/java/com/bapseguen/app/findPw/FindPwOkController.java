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
		Integer memberNumber = findPwDAO.findPw(id, phone);
		
		response.setContentType("application/json; charset=UTF-8");
		
		try(java.io.PrintWriter out = response.getWriter()){
			if(memberNumber != null) {
				out.print("{\"ok\":true, \"memberNumber\":" + memberNumber + "}");
			}else {
				out.print("{\"ok\":false}");
			}
			out.flush();
		}
		result.setRedirect(false);
	    result.setPath(null);
		return result;
	}
}
