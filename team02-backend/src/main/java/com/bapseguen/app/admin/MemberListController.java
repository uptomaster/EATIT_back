package com.bapseguen.app.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.MemberListDTO;

public class MemberListController implements Execute {

	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("====memberListController 실행====");
		AdminDAO adminDAO = new AdminDAO();
		Result result = new Result();

		String temp = request.getParameter("page");
		int page = (temp == null) ? 1 : Integer.valueOf(temp); // 페이지 번호 기본값 1로 설정
		int rowCount = 10; // 한 페이지당 게시글 수
		int pageCount = 5; // 페이지 버튼 수

		// 페이징 처리
		int startRow = (page - 1) * rowCount + 1;
		int endRow = startRow + rowCount - 1;

		Map<String, Integer> pageMap = new HashMap<>();
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);

		// 게시글 목록 조회
		List<MemberListDTO> memberListList = adminDAO.selectMember(pageMap);
		request.setAttribute("memberList", memberListList); // null 오류 수정

		// 페이징 정보 설정		
		int total = adminDAO.getTotal(); // static 호출 제거
		int realEndPage = (int) Math.ceil(total / (double) rowCount);
		int endPage = (int) (Math.ceil(page / (double) pageCount) * pageCount);
		int startPage = endPage - (pageCount - 1);
		endPage = Math.min(endPage, realEndPage);

		boolean prev = startPage > 1;
		boolean next = endPage < realEndPage;

		// request에 저장
		request.setAttribute("page", page);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);

		System.out.println("====페이징정보 확인====");
		System.out.println("pageMap : " + pageMap);
		System.out.println("memberList : " + memberListList);
		System.out.println("startPage : " + startPage + ", endPage : " + endPage + ", prev : " + prev + ", next : " + next);
		System.out.println("====================");

		result.setPath("/app/admin/memberList.jsp"); // 적절한 JSP 경로로 수정
		result.setRedirect(false);

		return result;
	}
}
