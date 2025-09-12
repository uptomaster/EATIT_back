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

public class InquiryDeleteOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    System.out.println("====InquiryDeleteOkController 실행====");

	    int postNumber = Integer.parseInt(request.getParameter("postNumber"));

	    CommunityDAO communityDAO = new CommunityDAO();
	    PostImageDAO postImageDAO = new PostImageDAO();
	    Result result = new Result();

	    // 🔸 삭제 전에 postType 조회
	    String postType = communityDAO.getPostType(postNumber);
	    System.out.println("삭제 전 조회된 postType: " + postType);

	    // 1. 파일 삭제
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
	    communityDAO.delete(postNumber);

	    // 3. 게시판 타입에 따라 이동 경로 결정
	    if (postType == null) {
	        // postType 조회 실패 시
	        result.setPath("/community/inquiryListOk.co");
	    } else if ("INQUIRY".equals(postType)) {
	        result.setPath("/community/inquiryListOk.co");
	    } else {
	        throw new IllegalArgumentException("Unknown postType: " + postType);
	    }

	    result.setRedirect(true);
	    System.out.println("Result path: " + result.getPath());
	    return result;
	}
}