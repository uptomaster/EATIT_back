package com.bapseguen.app.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;
import com.bapseguen.app.dto.view.InquiryCommentDTO;

public class InquiryCommentOkController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("[ADMIN] 문의 댓글 등록 요청");

		int postNumber = Integer.parseInt(request.getParameter("postNumber"));
		String commentContent = request.getParameter("commentContent");

		HttpSession session = request.getSession();
		Integer adminNumber = (Integer) session.getAttribute("adminNumber");

		// 댓글 DTO
		InquiryCommentDTO commentDTO = new InquiryCommentDTO();
		commentDTO.setPostNumber(postNumber);
		commentDTO.setAdminNumber(adminNumber);
		commentDTO.setCommentContent(commentContent);

		AdminDAO dao = new AdminDAO();
		dao.insertInquiryComment(commentDTO);

		// 댓글 등록 성공 후 상태 자동 업데이트 (최초 댓글이면 IN_PROGRESS)
		AdminPostDTO dto = new AdminPostDTO();
		dto.setPostNumber(postNumber);
		dto.setInquiryStatus("IN_PROGRESS");
		dao.updateInquiryStatus(dto);

		Result result = new Result();
		result.setPath(request.getContextPath() + "/admin/inquiry/detail.ad?postNumber=" + postNumber);
		result.setRedirect(true);
		return result;
	}
}
