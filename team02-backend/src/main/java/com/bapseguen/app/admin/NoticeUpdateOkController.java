package com.bapseguen.app.admin;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;
import com.bapseguen.app.dto.AdminImageDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class NoticeUpdateOkController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("[ADMIN] 공지/이벤트 수정 처리");

		// 업로드 경로 (webapp/upload)
		String uploadPath = request.getServletContext().getRealPath("/upload");
		File dir = new File(uploadPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		// MultipartRequest로 파일 업로드 처리
		MultipartRequest multi = new MultipartRequest(request, uploadPath, 10 * 1024 * 1024, // 10MB
				"UTF-8", new DefaultFileRenamePolicy());

		int postNumber = Integer.parseInt(multi.getParameter("postNumber"));
		String postTitle = multi.getParameter("postTitle");
		String noticeContent = multi.getParameter("noticeContent");

		AdminPostDTO dto = new AdminPostDTO();
		dto.setPostNumber(postNumber);
		dto.setPostTitle(postTitle);
		dto.setNoticeContent(noticeContent);

		AdminDAO dao = new AdminDAO();
		dao.updateNoticeTitle(dto);
		dao.updateNoticeContent(dto);

		// 기존 이미지 삭제
		dao.deleteAdminImagesByPost(postNumber);

		// 관리자 번호 (세션에서 가져오기)
		HttpSession session = request.getSession();
		Integer adminNumber = (Integer) session.getAttribute("adminNumber");

		// 새 이미지 업로드
		Enumeration<?> files = multi.getFileNames();
		while (files.hasMoreElements()) {
			String paramName = (String) files.nextElement();
			String systemName = multi.getFilesystemName(paramName);
			String originalName = multi.getOriginalFileName(paramName);

			if (systemName != null) { // 업로드된 파일이 있을 때만
				AdminImageDTO imageDTO = new AdminImageDTO();
				imageDTO.setAdminNumber(adminNumber);
				imageDTO.setPostNumber(postNumber);
				imageDTO.setAdminImageSystemName(systemName);
				imageDTO.setAdminImageOriginalName(originalName);

				dao.insertAdminImage(imageDTO);
			}
		}

		// 상세 페이지로 리다이렉트
		Result result = new Result();
		result.setPath(request.getContextPath() + "/admin/notice/detail.ad?postNumber=" + postNumber);
		result.setRedirect(true);
		return result;
	}
}
