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
		int postNumber = Integer.parseInt(request.getParameter("postNumber"));
        int memberNumber = (Integer) request.getSession().getAttribute("memberNumber");

        response.setContentType("application/json; charset=UTF-8");

        try {
            // 작성자가 본인인지 체크
            int postAuthor = communityDAO.getAuthorNumber(postNumber);
            if (memberNumber == postAuthor) {
                response.getWriter().print("{\"success\":false,\"message\":\"본인 글은 추천할 수 없습니다.\"}");
                return null;
            }

            // 이미 추천했는지 확인
            if (communityDAO.hasLiked(postNumber, memberNumber)) {
                response.getWriter().print("{\"success\":false,\"message\":\"이미 추천했습니다.\"}");
                return null;
            }

            // 추천 추가 + 카운트 증가
            communityDAO.insertLikeAndUpdateCount(postNumber, memberNumber);

            // 최신 추천 수 조회
            int newLikeCount = communityDAO.getLikeCount(postNumber);

            response.getWriter().print("{\"success\":true,\"likeCount\":" + newLikeCount + "}");
            response.getWriter().flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("{\"success\":false,\"message\":\"추천 처리 중 오류가 발생했습니다.\"}");
        }

        // JSON 반환이므로 JSP 이동 필요 없음
        return null;
	}
	
}
