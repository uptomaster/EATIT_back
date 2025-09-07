package com.bapseguen.app.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;

public class WriteFreeBoardController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		System.out.println("====WriteFreeBoardController 실행====");
		
		
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
		
		
		result.setPath("/app/community/writeFreeBoard.jsp");
		result.setRedirect(false);
		request.setAttribute("memberId", session.getAttribute("memberId"));
		
		// 클릭한 게시판 타입 분기처리
		String postType = request.getParameter("postType");
		   if(postType == null || postType.isEmpty()) {
	        postType = " ";
		}
		request.setAttribute("postType", postType);
		
		return result;
		
	}
}
