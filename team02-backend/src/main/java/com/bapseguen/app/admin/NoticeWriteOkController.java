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

public class NoticeWriteOkController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("[ADMIN] 공지/이벤트 작성 처리");

		// 업로드 경로 (webapp/upload)
		String uploadPath = request.getServletContext().getRealPath("/upload");
		File dir = new File(uploadPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		// MultipartRequest로 파일 업로드 처리
		MultipartRequest multi = new MultipartRequest(request, uploadPath, 10 * 1024 * 1024, // 10MB
				"UTF-8", new DefaultFileRenamePolicy());

		String postTitle = multi.getParameter("postTitle");
		String noticeContent = multi.getParameter("noticeContent");

		// 세션에서 관리자 번호 가져오기
		HttpSession session = request.getSession();
		Integer adminNumber = (Integer) session.getAttribute("adminNumber");

		// 글 등록
		AdminPostDTO dto = new AdminPostDTO();
		dto.setPostTitle(postTitle);
		dto.setNoticeContent(noticeContent);
		dto.setAdminNumber(adminNumber);

		AdminDAO dao = new AdminDAO();
		dao.insertNoticePost(dto);
		dao.insertNotice(dto);

		int postNumber = dto.getPostNumber(); // selectKey 로 채워진 값

		// 이미지 업로드 처리
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

		// 목록으로 리다이렉트
		Result result = new Result();
		result.setPath(request.getContextPath() + "/admin/notice/list.ad");
		result.setRedirect(true);
		return result;
	}
}
