package com.bapseguen.app.admin;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;

public class AdminLoginController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
    System.out.println("[ADMIN] 관리자 로그인 페이지 요청");

	Result result = new Result();
	Cookie[] cookies = request.getCookies();
	//사용자가 브라우저에서 서버로 요청을 보낼 때 브라우저는 자신이 가지고 있는 쿠키들을 함께 전송
	//request.getCookies() 쿠키들을 배열로 꺼내는 메소드
	
	if (cookies != null) {
		//쿠키가 전혀없으면 getCookes()는 null을 반환하기 때문에 null 체크를 먼저 진행함
		for (Cookie cookie : cookies) {
			//쿠키 배열이 여러개 있을 수 있으니 for-each문으로 하나씩 꺼낸다
			if (cookie.getName().equals("memberId")) {
				//쿠키마다 이름이 존재하는데 memberId라는 이름을 가진 쿠키를 찾는다
				request.setAttribute("memberId", cookie.getValue());
				//찾은 memberId 쿠키의 값을 request속성에 넣어준다
				//${memberId} 형태로 꺼내 쓸수가 있다
			}
		}
	}
	
    result.setPath("/app/admin/adminLogin.jsp");
    result.setRedirect(false);
    return result;
	

	}

}
