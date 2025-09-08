package com.bapseguen.app.community;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.community.dao.CommunityDAO;

public class PostViewCountController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("====PostViewCountController 실행====");
		
		CommunityDAO communityDAO = new CommunityDAO();
		int postNumber = Integer.parseInt(request.getParameter("postNumber"));

	    //communityDAO.updateReadCount(postNumber);  // 조회수 증가
	    int viewCount = communityDAO.getViewCount(postNumber); // 현재 조회수 가져오기

	    System.out.println("현재 조회수: " + viewCount);
	    
	    response.setContentType("application/json; charset=UTF-8");
	    response.getWriter().write("{\"viewCount\":" + viewCount + "}");
	    response.getWriter().flush();
	    response.getWriter().close();

	    return null; // Result 없이 바로 끝
	}
}
