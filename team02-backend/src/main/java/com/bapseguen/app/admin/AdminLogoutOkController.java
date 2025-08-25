package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;

// 관리자 로그아웃 처리하는 컨트롤러
public class AdminLogoutOkController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("[ADMIN] 로그아웃 처리");
		HttpSession session = request.getSession(false);
		if (session != null) {
			// 세션 만료시키기
			session.invalidate();
		}

		Result result = new Result();
		result.setPath(request.getContextPath() + "/admin/login.ad");
		result.setRedirect(true);
		return result;
	}
}