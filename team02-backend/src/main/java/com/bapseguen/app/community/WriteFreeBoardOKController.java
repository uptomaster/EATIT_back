package com.bapseguen.app.community;

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
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class WriteFreeBoardOKController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CommunityDAO communityDAO = new CommunityDAO();
		PostDTO postDTO=new PostDTO();
		FreeBoardDTO freeBoardDTO = new FreeBoardDTO(); 
		Result result = new Result();
		PostImageDTO postImageDTO = new PostImageDTO();
		
		//로그인 한 회원 정보 가져오기
		Integer memberNumber = (Integer)request.getSession().getAttribute("memberNumber");
		
		if(memberNumber == null) {
			System.out.println("오류 : 로그인된 사용자가 없습니다");
			response.sendRedirect("login.jsp");
			return null;
		}
		
		//파일 업로드 환경 설정
		final String UPLOAD_PATH = request.getSession().getServletContext().getRealPath("/") + "upload/";
		final int FILE_SIZE = 1024 * 1024 * 5; //5MB
		System.out.println("파일 업로드 경로 : " + UPLOAD_PATH);
		
		//MultipartRequest를 이용한 데이터 파싱
		MultipartRequest multipartRequest = new MultipartRequest(request, UPLOAD_PATH, FILE_SIZE, "utf-8", new DefaultFileRenamePolicy());
		//request : HTTP 요청객체
		//UPLOAD_PATH : 파일을 저장할 경로
		//FILE_SIZE : 파일의 최대 크기
		//"utf-8" : 파일명 인코딩 방식
		//new DefaultFileRenamePolicy() : 파일명이 중복될 경우 자동으로 이름 변경해주는 정책

		//게시글 정보 설정
		postDTO.setPostTitle(multipartRequest.getParameter("postTitle"));
		freeBoardDTO.setFreeContent(multipartRequest.getParameter("freeContent"));
		postDTO.setMemberNumber(memberNumber);
		System.out.println("게시글 추가 - PostDTO : " + postDTO);
		
		//게시글 추가
		int postNumber = communityDAO.insertPost(postDTO);
		System.out.println("생성된 게시글 번호 : " + boardNumber);
		
		//파일 업로드 처리
		//Enumeration : java.util 패키지에 포함된 인터페이스, Iterator와 비슷한 역할함
		Enumeration<String> fileNames = multipartRequest.getFileNames();
		while(fileNames.hasMoreElements()) {
			String name = fileNames.nextElement();
			String fileSystemName = multipartRequest.getFilesystemName(name);
			String fileOriginalName = multipartRequest.getOriginalFileName(name);
			
			if(fileSystemName == null) {
				continue;
			}
			
			fileDTO.setFileSystemName(fileSystemName);
			fileDTO.setFileOriginalName(fileOriginalName);
			fileDTO.setBoardNumber(boardNumber);
			
			System.out.println("업로드 된 파일 정보 : " + fileDTO);
			fileDAO.insert(fileDTO);
		}
		
		result.setPath("/board/boardListOk.bo");
		result.setRedirect(false);
		
		return result;
		
	}

}
