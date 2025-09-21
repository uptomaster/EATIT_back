package com.bapseguen.app.community;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.community.dao.CommunityDAO;

public class PostLikeController implements Execute{

	private CommunityDAO communityDAO = new CommunityDAO();
	
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("====PostLikeController 실행====");
		
		String method = request.getMethod();
	    response.setContentType("application/json; charset=UTF-8");

	    if("POST".equalsIgnoreCase(method)) {
	        // 기존 추천 처리 코드 그대로
	        int postNumber = Integer.parseInt(request.getParameter("postNumber"));
	        int memberNumber = (Integer) request.getSession().getAttribute("memberNumber");

	        try {
	            int postAuthor = communityDAO.getAuthorNumber(postNumber);
	            if (memberNumber == postAuthor) {
	                response.getWriter().print("{\"success\":false,\"message\":\"본인 글은 추천할 수 없습니다.\"}");
	                return null;
	            }

	            if (communityDAO.hasLiked(postNumber, memberNumber)) {
	                response.getWriter().print("{\"success\":false,\"message\":\"이미 추천한 게시글입니다.\"}");
	                return null;
	            }

	            communityDAO.insertLikeAndUpdateCount(postNumber, memberNumber);
	            int newLikeCount = communityDAO.getLikeCount(postNumber);
	            response.getWriter().print("{\"success\":true,\"likeCount\":" + newLikeCount + "}");
	            response.getWriter().flush();

	        } catch (Exception e) {
	            e.printStackTrace();
	            response.getWriter().print("{\"success\":false,\"message\":\"추천 처리 중 오류가 발생했습니다.\"}");
	        }

	    } else if("GET".equalsIgnoreCase(method)) {
	        // GET 요청: 특정 게시글의 최신 추천수 조회
	        int postNumber = Integer.parseInt(request.getParameter("postNumber"));
	        try {
	            int likeCount = communityDAO.getLikeCount(postNumber);
	            response.getWriter().print("{\"likeCount\":" + likeCount + "}");
	        } catch(Exception e) {
	            e.printStackTrace();
	            response.getWriter().print("{\"likeCount\":0}");
	        }
	    }

	    return null;
	}
	
}
