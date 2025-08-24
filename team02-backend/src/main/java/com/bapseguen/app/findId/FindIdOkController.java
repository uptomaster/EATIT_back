package com.bapseguen.app.findId;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.findId.dao.FindIdDAO;


public class FindIdOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Result result = new Result();
		FindIdDAO findIdDAO = new FindIdDAO();
		
		String name = request.getParameter("findIdAuth_input_name");
		String phone = request.getParameter("findIdAuth_input_phone");
		String id = findIdDAO.findId(name, phone);
		if(id == null) {
			result.setRedirect(true);
			result.setPath(request.getContextPath()+ "/app/findId/findUserIdFail.jsp");
		}else{
			request.setAttribute("foundId", id);
			result.setRedirect(false);
			result.setPath("/app/findId/findUserIdAuth.jsp");
		}
		return result;
	}

}
