package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.view.AdminPostDTO;
import com.bapseguen.app.admin.dao.AdminDAO;

public class FaqEditOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("==== [ADMIN] FaqEditOkController 실행 ====");

		Result result = new Result();
		HttpSession session = request.getSession(false);

		// 1) 로그인/권한 체크
		Integer adminNumber = (session != null) ? (Integer) session.getAttribute("adminNumber") : null;
		String adminGrade = (session != null) ? (String) session.getAttribute("adminGrade") : null;

		if (adminNumber == null || adminGrade == null || !"관리자".equals(adminGrade)) {
			System.out.println("[ADMIN] 세션 없음 또는 관리자 권한 아님 → 로그인 페이지로 이동");
			result.setPath(request.getContextPath() + "/admin/login.ad");
			result.setRedirect(true);
			return result;
		}

		// 2) 파라미터 수집
		int postNumber = Integer.parseInt(request.getParameter("postNumber"));
		String postTitle = request.getParameter("postTitle");
		String faqContent = request.getParameter("faqContent");

		// 3) DTO 생성
		AdminPostDTO faqDTO = new AdminPostDTO();
		faqDTO.setPostNumber(postNumber);
		faqDTO.setPostTitle(postTitle);
		faqDTO.setFaqContent(faqContent);
		faqDTO.setAdminNumber(adminNumber);

		// 4) DB 업데이트
		AdminDAO adminDAO = new AdminDAO();
		adminDAO.updateFaqTitle(faqDTO);
		adminDAO.updateFaqContent(faqDTO);

		// 5) 수정 완료 후 상세 페이지로 이동
		result.setPath(request.getContextPath() + "/admin/faq/detail.ad?postNumber=" + postNumber);
		result.setRedirect(true);

		System.out.println("==== [ADMIN] FaqEditOkController 완료 (postNumber=" + postNumber + ") ====");
		return result;
	}
}
