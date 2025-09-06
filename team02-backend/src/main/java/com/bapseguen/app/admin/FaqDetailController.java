package com.bapseguen.app.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;

public class FaqDetailController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("==== [ADMIN] FaqDetailController 실행 ====");

		Result result = new Result();
		AdminDAO adminDAO = new AdminDAO();

		// 1) 파라미터 가져오기
		String temp = request.getParameter("postNumber");
		if (temp == null || temp.isBlank()) {
			result.setPath(request.getContextPath() + "/admin/faq/list.ad");
			result.setRedirect(true);
			return result;
		}

		int postNumber = Integer.parseInt(temp);

		// 2) 상세 조회
		AdminPostDTO faqDetail = adminDAO.selectFaqDetail(postNumber);

		if (faqDetail == null) {
			// 없는 글이면 목록으로
			result.setPath(request.getContextPath() + "/admin/faq/list.ad");
			result.setRedirect(true);
			return result;
		}

		// 3) JSP로 전달
		request.setAttribute("faqDetail", faqDetail);

		result.setPath("/app/admin/adminFaqDetail.jsp");
		result.setRedirect(false);

		System.out.println("==== [ADMIN] FaqDetailController 완료 (postNumber=" + postNumber + ") ====");
		return result;
	}
}
