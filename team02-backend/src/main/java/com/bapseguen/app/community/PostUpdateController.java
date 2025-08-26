package com.bapseguen.app.community;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.community.dao.CommunityDAO;
import com.bapseguen.app.dto.view.PostDetailDTO;

public class PostUpdateController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		System.out.println("====PostUpdateController 실행====");
		int postNumber = Integer.valueOf(request.getParameter("postNumber"));
        CommunityDAO communityDAO = new CommunityDAO();
        Result result = new Result();
		
		//postNumber가 빈 문자열이거나 null인경우
		String postNumberStr = request.getParameter("postNumber");
		if(postNumberStr == null || postNumberStr.trim().isEmpty()){
			System.out.println("postNumber 값이 없습니다");
			result.setPath("/app/community/viewOtherPost.jsp"); //게시글 개별 페이지로 리다이렉트
			result.setRedirect(true);
			return result;
		}
		
        // JSP에 전달
        request.setAttribute("post", communityDAO.select(postNumber));

        result.setPath("/community/freeBoardReadOk.co");
        result.setRedirect(false);
        return result;
        
	}
}
