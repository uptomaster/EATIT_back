package com.bapseguen.app.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.MemberDTO;
import com.bapseguen.app.login.dao.LoginDAO;

public class LoginOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberDTO memberDTO = new MemberDTO();
		LoginDAO loginDAO = new LoginDAO();
		int memberNumber = 0;
		Result result = new Result();
		String path = null;
		
		String memberId = request.getParameter("memberId");
		String memberPassword = request.getParameter("memberPassword");
		HttpSession session = request.getSession();
		
		memberDTO.setMemberId(memberId);
		memberDTO.setMemberPassword(memberPassword);
		memberNumber = loginDAO.login(memberDTO);
		
		if(memberNumber != -1) {
			path = "/main.jsp";
			session.setAttribute("memberNumber", memberNumber);
			System.out.println("세션값 : " + memberNumber);
		}else {
			path = "/login/login.lo?login=fail";
		}
		result.setRedirect(true);
		result.setPath(path);
		return result;
	}

	
}
