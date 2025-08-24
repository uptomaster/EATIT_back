package com.bapseguen.app.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.BannerDTO;

public class BannerUpdateOkController implements Execute {
//	아직 미완성
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("[ADMIN] 배너 수정 처리");
		Result result = new Result();
		
		HttpSession s = request.getSession(false);
		if (s == null || !"ADMIN".equals(String.valueOf(s.getAttribute("memberType")))) {
			Result r = new Result();
			r.setPath(request.getContextPath() + "/admin/login.ad");
			r.setRedirect(true);
			return r;
		}

		BannerDTO dto = new BannerDTO();
		return result;

	}
}
