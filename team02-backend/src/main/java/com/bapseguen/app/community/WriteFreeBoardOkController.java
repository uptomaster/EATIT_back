package com.bapseguen.app.community;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.community.dao.CommunityDAO;
import com.bapseguen.app.dto.FreeBoardDTO;
import com.bapseguen.app.dto.PostDTO;
import com.bapseguen.app.dto.PostImageDTO;
import com.bapseguen.app.img.dao.PostImageDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class WriteFreeBoardOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("==== WriteFreeBoardOkController 실행 ====");

		Result result = new Result();
		CommunityDAO communityDAO = new CommunityDAO();
		PostImageDAO postImageDAO = new PostImageDAO();

		PostDTO postDTO = new PostDTO();
		FreeBoardDTO freeBoardDTO = new FreeBoardDTO();
		PostImageDTO postImageDTO = new PostImageDTO();

		// 로그인 사용자 확인
		Integer memberNumber = (Integer) request.getSession().getAttribute("memberNumber");
		if (memberNumber == null) {
			System.out.println("오류 : 로그인된 사용자가 없습니다");
			response.sendRedirect(request.getContextPath() + "/member/login.me");
			return null;
		}

		// 업로드 경로
		final String UPLOAD_PATH = request.getServletContext().getRealPath("/upload");
		final int FILE_SIZE = 1024 * 1024 * 5; // 5MB

		File uploadDir = new File(UPLOAD_PATH);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs(); // 디렉토리 없으면 생성
		}

		// multipart/form-data 요청인지 확인
		String contentType = request.getContentType();
		if (contentType == null || !contentType.toLowerCase().startsWith("multipart/")) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "multipart/form-data 요청 아님");
			return null;
		}

		// MultipartRequest로 데이터 파싱
		MultipartRequest multi = new MultipartRequest(request, UPLOAD_PATH, FILE_SIZE, "utf-8",
				new DefaultFileRenamePolicy());

		// 게시글 정보
		postDTO.setPostTitle(multi.getParameter("postTitle"));
		freeBoardDTO.setFreeContent(multi.getParameter("freeContent"));
		postDTO.setMemberNumber(memberNumber);

		System.out.println("게시글 추가 - PostDTO : " + postDTO);

		// 게시글 insert 후 번호 반환
		int postNumber = communityDAO.insertPost(postDTO);
		System.out.println("생성된 게시글 번호 : " + postNumber);

		// 이미지 파일 처리
		Enumeration<String> fileNames = multi.getFileNames();
		while (fileNames.hasMoreElements()) {
			String name = fileNames.nextElement();
			String systemName = multi.getFilesystemName(name);
			String originalName = multi.getOriginalFileName(name);

			if (systemName == null)
				continue;

			postImageDTO.setPostImageSystemName(systemName);
			postImageDTO.setPostImageOriginalName(originalName);
			postImageDTO.setPostNumber(postNumber);

			System.out.println("업로드 된 파일 정보 : " + postImageDTO);
			postImageDAO.insert(postImageDTO);
		}

		// 완료 후 목록으로 리다이렉트
		result.setPath(request.getContextPath() + "/community/freeBoardListOk.co");
		result.setRedirect(true);

		return result;
	}
}
