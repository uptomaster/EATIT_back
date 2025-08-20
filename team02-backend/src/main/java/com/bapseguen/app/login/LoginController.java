package com.bapseguen.app.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;

public class LoginController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Result result = new Result();
		Cookie[] cookies = request.getCookies();
		
		if(cookies !=null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("memberId")) {
					request.setAttribute("memberId", cookie.getValue());
				}
			}
		}
		
		result.setPath("/app/login/login.jsp");
		result.setRedirect(false);
		return result;
	}
	
}
