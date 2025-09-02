package com.bapseguen.app.community;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.community.dao.CommunityDAO;

public class PostUpdateController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		System.out.println("====PostUpdateController 실행====");
		int postNumber = Integer.valueOf(request.getParameter("postNumber"));
        CommunityDAO communityDAO = new CommunityDAO();
        Result result = new Result();
		
		request.setAttribute("post", communityDAO.select(postNumber));
		
		result.setPath("/app/community/viewOtherPost.jsp");
		result.setRedirect(false);
			
		return result;
        
	}
}
