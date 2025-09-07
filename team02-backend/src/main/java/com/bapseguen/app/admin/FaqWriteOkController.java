package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;

public class FaqWriteOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("==== [ADMIN] FaqWriteOkController 실행 ====");

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
		String postTitle = request.getParameter("postTitle");
		String faqContent = request.getParameter("faqContent");

		// 필수값 검증
		if (postTitle == null || postTitle.isBlank() || faqContent == null || faqContent.isBlank()) {
			request.setAttribute("writeError", "제목과 내용을 모두 입력해주세요.");
			result.setPath("/app/admin/adminFaqWrite.jsp");
			result.setRedirect(false);
			return result;
		}

		// 3) DTO 생성
		AdminPostDTO faqDTO = new AdminPostDTO();
		faqDTO.setAdminNumber(adminNumber);
		faqDTO.setPostTitle(postTitle);
		faqDTO.setFaqContent(faqContent);
		faqDTO.setPostType("FAQ");

		// 4) DB 저장
		AdminDAO dao = new AdminDAO();
		dao.insertFaqPost(faqDTO);
		dao.insertFaq(faqDTO);

		// 5) 저장 완료 후 목록으로 이동
		result.setPath(request.getContextPath() + "/admin/faq/list.ad");
		result.setRedirect(true);

		System.out.println("==== [ADMIN] FaqWriteOkController 완료 ====");
		return result;
	}
}
