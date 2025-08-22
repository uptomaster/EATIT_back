package com.bapseguen.app.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;

public class AdminDashboardController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 System.out.println("[ADMIN] 대시보드 컨트롤러 시작");
		
		Result result = new Result();
		AdminDAO adminDAO = new AdminDAO();
		
//	 흠..
		
		
		
		return result;
	}

}
