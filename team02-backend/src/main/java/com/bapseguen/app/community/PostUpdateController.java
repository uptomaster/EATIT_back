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
		
		// 1. 파라미터에서 게시글 번호 가져오기
        int postNumber = Integer.parseInt(request.getParameter("postNumber"));

        // 2. DAO 생성
        CommunityDAO communityDAO = new CommunityDAO();
        Result result = new Result();

        // 3. 게시글 타입 확인
        String postType = communityDAO.getPostType(postNumber);
        PostDetailDTO post = null;

        // 4. 게시판 타입에 따라 상세 조회
        switch (postType) {
            case "FREE":
                post = communityDAO.freePostselect(postNumber);
                break;
            case "PROMOTION":
                post = communityDAO.promoPostSelect(postNumber);
                break;
            case "RECIPE":
                post = communityDAO.recipePostSelect(postNumber);
                break;
            default:
                throw new IllegalArgumentException("지원하지 않는 게시판 타입: " + postType);
        }

        // 5. JSP로 데이터 전달
        request.setAttribute("post", post);
        request.setAttribute("postType", postType);

        // 6. 수정 페이지로 포워드
        result.setPath("/app/community/updatePost.jsp");
        result.setRedirect(false);

        return result;
        
	}
}
