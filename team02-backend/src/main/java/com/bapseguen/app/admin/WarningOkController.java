package com.bapseguen.app.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.MemberDetailDTO;

public class WarningOkController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Result result = new Result();
		AdminDAO adminDAO = new AdminDAO();

		String memberNumberParam = request.getParameter("memberNumber");
		if (memberNumberParam == null || memberNumberParam.isBlank()) {
			// 값이 안 들어온 경우 처리
			request.setAttribute("errorMessage", "회원 번호가 없습니다.");
			result.setPath(request.getContextPath() + "/admin/member/list.ad");
			result.setRedirect(true);
			return result;
		}

		int memberNumber = Integer.parseInt(memberNumberParam);

		// 회원 유형 가져오기 (폼에서 안 넘어오면 DB에서 조회)
		String memberType = request.getParameter("memberType");
		if (memberType == null || memberType.isBlank()) {
			memberType = adminDAO.getMemberType(memberNumber);
		}

		// 경고 증가
		int updated = 0;
		if ("GENERAL".equalsIgnoreCase(memberType)) {
			updated = adminDAO.increaseWarningCount(memberNumber);
		} else if ("SELLER".equalsIgnoreCase(memberType)) {
			updated = adminDAO.increaseWarningCountSeller(memberNumber);
		}

		System.out.println("⚡ WarningOkController: memberNumber=" + memberNumber + ", memberType=" + memberType
				+ ", updatedRows=" + updated);

		// 처리 후 목록으로 이동
		result.setPath(request.getContextPath() + "/admin/member/list.ad");
		result.setRedirect(true);
		return result;
	}

}
