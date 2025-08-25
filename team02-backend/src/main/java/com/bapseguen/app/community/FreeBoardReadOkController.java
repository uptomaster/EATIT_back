package com.bapseguen.app.community;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.community.dao.CommunityDAO;
import com.bapseguen.app.dto.PostImageDTO;
import com.bapseguen.app.dto.view.PostDetailDTO;
import com.bapseguen.app.img.dao.PostImageDAO;

public class FreeBoardReadOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("====FreeBoardReadOkController 실행====");
		Result result = new Result();
		
		//postNumber가 빈 문자열이거나 null인경우
		String postNumberStr = request.getParameter("postNumber");
		if(postNumberStr == null || postNumberStr.trim().isEmpty()){
			System.out.println("postNumber 값이 없습니다==========");
			result.setPath("/community/freeBoardListOk.co"); //게시글 목록 페이지로 리다이렉트
			result.setRedirect(true);
			return result;
		}
		
		int postNumber = Integer.parseInt(postNumberStr);
		
		CommunityDAO communityDAO = new CommunityDAO();
		PostImageDAO postImageDAO = new PostImageDAO();
		PostImageDTO postImageDTO = new PostImageDTO();

		//DB에서 게시글 가져오기
		PostDetailDTO postDetailDTO = communityDAO.select(postNumber);
		
		//
		PostDetailDTO freeBoard = communityDAO.selectPost(postNumber); // 상세 조회
		request.setAttribute("freeBoard", freeBoard);
		
		//게시글이 존재하지 않을 경우 처리
		if(postDetailDTO == null) {
			System.out.println("존재하지 않는 게시글입니다. " + postNumber);
			result.setPath("/app/community/communityMainUser.jsp");
			result.setRedirect(true);
			return result;
		}
		
		//첨부파일 가져오기
		List<PostImageDTO> files = postImageDAO.select(postNumber);
		System.out.println("======파일 확인======");
		System.out.println(files);
		System.out.println("===================");
		
		//첨부파일 붙이기
		postDetailDTO.setFiles(files);
		
		//로그인한 사용자 번호 가져오기
		Integer loginMemberNumber = (Integer) request.getSession().getAttribute("memberNumber");
		System.out.println("로그인 한 멤버 번호 : " + loginMemberNumber);
		
		//현재 게시글의 작성자 번호 가져오기
		int postWriterNumber = postDetailDTO.getMemberNumber();
		System.out.println("현재 게시글 작성자 번호 : " + postWriterNumber);
		
		//로그인한 사용자가 작성자가 아닐 때만 조회수 증가
		if(!Objects.equals(loginMemberNumber, postWriterNumber)) {
			communityDAO.updateReadCount(postNumber);
		}
		
		request.setAttribute("post", postDetailDTO);
		result.setPath("/app/community/viewOtherPost.jsp");
		result.setRedirect(false);		
		
		return result;
	}

}
