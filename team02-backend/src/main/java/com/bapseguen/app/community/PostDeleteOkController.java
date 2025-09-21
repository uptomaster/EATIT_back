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
            postType = "FREE";  // 기본값 설정. 필요시 다른 기본값을 설정할 수 있음.
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
                result.setPath("/community/recipeBoardListOk.co");
                break;
            default:
                throw new IllegalArgumentException("Unknown postType: " + postType);
        }

        result.setRedirect(true);
        System.out.println("확인용 Result path: " + result.getPath());
        return result;
    }
		
}

