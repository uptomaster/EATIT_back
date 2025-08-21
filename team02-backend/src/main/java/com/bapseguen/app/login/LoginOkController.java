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
		
		String memberId = request.getParameter("login_input_id");
		String memberPassword = request.getParameter("login_input_pw");
		HttpSession session = request.getSession();
		
		memberDTO.setMemberId(memberId);
		memberDTO.setMemberPassword(memberPassword);
		memberNumber = loginDAO.login(memberDTO);
		
		if(memberNumber != -1) {
			memberDTO.setMemberNumber(memberNumber);
            String memberType = loginDAO.getMemberType(memberDTO);
            if(memberType == null || memberType.isEmpty()){
                memberType = "GENERAL";
            }

            session.setAttribute("memberNumber", memberNumber);
            session.setAttribute("memberType", memberType);

            System.out.println("세션값 memberNumber : " + memberNumber);
            System.out.println("세션값 memberType   : " + memberType);
			path = "/main.jsp";
		}else {
			path = "/login/login.lo?login=fail";
		}
		result.setRedirect(true);
		result.setPath(path);
		return result;
	}

	
}
