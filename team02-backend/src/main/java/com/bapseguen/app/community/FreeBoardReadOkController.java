package com.bapseguen.app.community;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
		Integer memberNumber = (Integer)session.getAttribute("memberNumber");
		String path = null;
		
		//memberNumber 값이 null이거나 0일때
		if (memberNumber == null || memberNumber == 0) {
		    response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script>");
		    out.println("alert('로그인이 필요합니다.');");
		    out.println("location.href='/app/login/login.jsp';");
		    out.println("</script>");
		    out.close();
		    return null;
		}
		
		//postNumber가 빈 문자열이거나 null인경우
		String postNumberStr = request.getParameter("postNumber");
		if(postNumberStr == null || postNumberStr.trim().isEmpty()){
			System.out.println("postNumber 값이 없습니다");
			result.setPath("/community/freeBoardListOk.co"); //게시글 목록 페이지로 리다이렉트
			result.setRedirect(true);
			return result;
		}
		
		int postNumber = Integer.parseInt(postNumberStr);
		
		CommunityDAO communityDAO = new CommunityDAO();
		PostImageDAO postImageDAO = new PostImageDAO();
		 

		//DB에서 게시글 가져오기
		PostDetailDTO postDetailDTO = communityDAO.freePostselect(postNumber);
		// 이미지 리스트 가져오기
		List<PostImageDTO> postImages = postImageDAO.select(postNumber);
		request.setAttribute("postImages", postImages);
		
		//게시글이 존재하지 않을 경우 처리
		if(postDetailDTO == null) {
			System.out.println("존재하지 않는 게시글입니다. " + postNumber);
			result.setPath("/community/freeBoardListOk.co");
			result.setRedirect(true);
			return result;
		}
	
		
		//로그인한 사용자 번호 가져오기
		Integer loginMemberNumber = (Integer) request.getSession().getAttribute("memberNumber");
		System.out.println("로그인 한 멤버 번호 : " + loginMemberNumber);
		
		System.out.println("postdetailDTO.getMemberNumber() 호출 전");
		//현재 게시글의 작성자 번호 가져오기
		int postWriterNumber = postDetailDTO.getMemberNumber();
		System.out.println("postdetailDTO.getMemberNumber() 반환값: " + postWriterNumber);
		
		//로그인한 사용자가 작성자가 아닐 때만 조회수 증가
		if(!Objects.equals(loginMemberNumber, postWriterNumber)) {
			communityDAO.updateReadCount(postNumber);
		}
		
		request.setAttribute("postImages", postImages);
		request.setAttribute("post", postDetailDTO);
		result.setPath("/app/community/viewOtherPost.jsp");
		result.setRedirect(false);		
		return result;
	}

}
