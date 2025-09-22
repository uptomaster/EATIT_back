package com.bapseguen.app.community;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.community.dao.CommunityDAO;
import com.bapseguen.app.dto.PostImageDTO;
import com.bapseguen.app.img.dao.PostImageDAO;

public class PostDeleteOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("====PostDeleteOkController 실행====");
		
		int postNumber = Integer.parseInt(request.getParameter("postNumber"));

        CommunityDAO communityDAO = new CommunityDAO();
        PostImageDAO postImageDAO = new PostImageDAO();
        Result result = new Result();

        // 1. 파일 삭제 (있는 경우만)
        String uploadPath = request.getSession().getServletContext().getRealPath("/") + "upload/";
        List<PostImageDTO> images = postImageDAO.select(postNumber);
        for (PostImageDTO image : images) {
            File file = new File(uploadPath, image.getPostImageSystemName());
            if (file.exists()) {
                file.delete();
                System.out.println("파일 삭제: " + file.getName());
            }
        }

        // 2. 게시글 삭제
        String postType = communityDAO.getPostType(postNumber);
        
        // postType이 null일 경우 처리
        if (postType == null) {
            // "FREE"로 기본값 설정
            postType = "FREE";  
        }

        // 게시글 삭제
        communityDAO.delete(postNumber);

        // postType에 따라 리다이렉트 경로 설정
        switch (postType.toUpperCase()) {
            case "FREE":
                result.setPath("/community/freeBoardListOk.co");
                break;
            case "PROMOTION":
                result.setPath("/community/promoBoardListOk.co");
                break;
            case "RECIPE":
                result.setPath("/community/recipeListOk.co");
                break;
            default:
                // 예상치 못한 값이 들어왔을 때, 디폴트 경로로 처리
                result.setPath("/community/communityMainOk.co");  
                System.out.println("잘못된 postType이 들어왔습니다. 기본 경로로 리다이렉트됩니다.");
                break;
        }

        // 리다이렉트 설정
        result.setRedirect(true);

        System.out.println("=============확인용 Result path: " + result.getPath());
        return result;
    }
		
}

