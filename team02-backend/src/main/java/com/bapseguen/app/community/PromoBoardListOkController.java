package com.bapseguen.app.community;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;

public class PromoBoardListOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		System.out.println("====PromoBoardListOkController 실행====");
		Result result = new Result();
		HttpSession session = request.getSession();
		Integer memberNumber = (Integer)session.getAttribute("memberNumber");
		String path = null;
		
		if(memberNumber == null) {
			path = "/app/login/login.jsp";
		}else{
			path = "/app/community/promoBoardList.jsp";
		}
		
		result.setPath(path);
		result.setRedirect(false);
		request.setAttribute("memberId", session.getAttribute("memberId"));
		
		// 클릭한 게시판 타입 분기처리
		String postType = request.getParameter("postType");
		   if(postType == null || postType.isEmpty()) {
	        postType = "FREE"; // 기본값 자유게시판
		}
		request.setAttribute("postType", postType);
		
		return result;
	
	}

}
