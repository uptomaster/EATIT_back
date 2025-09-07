package com.bapseguen.app.community;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.community.dao.CommunityDAO;

public class PostLikeController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("====PostLikeController 실행====");
		Result result = new Result();
        CommunityDAO communityDAO = new CommunityDAO();

        int postNumber = Integer.parseInt(request.getParameter("postNumber"));
        int memberNumber = (Integer) request.getSession().getAttribute("memberNumber");
        int postAuthorNumber = communityDAO.getAuthorNumber(postNumber);
        System.out.println("세션 memberNumber: " + memberNumber);
        System.out.println("작성자 memberNumber: " + postAuthorNumber);
        
        // 작성자가 본인인지 확인
        if(memberNumber == postAuthorNumber) {
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print("{\"success\":false,\"message\":\"본인 글은 추천할 수 없습니다.\"}");
            return result;
        }

        // 이미 추천했는지 확인
        boolean liked = communityDAO.hasLiked(postNumber, memberNumber);
        if (!liked) {
            communityDAO.insertLike(postNumber, memberNumber);
            communityDAO.updateLikeCount(postNumber);
        }

        // 페이지 이동 없이 추천 수만 반환
        int newLikeCount = communityDAO.getLikeCount(postNumber);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print("{\"success\":true,\"likeCount\":" + newLikeCount + "}");
        
        return result;
	
	}
	
}
