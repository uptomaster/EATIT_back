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

        // 2. 이미지 DB 삭제
        //postImageDAO.delete(postNumber);

        // 3. 게시글 삭제
        communityDAO.delete(postNumber);

        // 4. 리다이렉트
        System.out.println("확인용 Result path: " + result.getPath());
        result.setPath("/community/freeBoardListOk.co");
        result.setRedirect(true);
        return result;
		
	}

}
