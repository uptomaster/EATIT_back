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
import com.bapseguen.app.dto.view.PostDetailDTO;
import com.bapseguen.app.img.dao.PostImageDAO;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

public class PostUpdateOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("====PostUpdateOkController 실행====");
        
        Result result = new Result();
        CommunityDAO communityDAO = new CommunityDAO();
        PostImageDAO postImageDAO = new PostImageDAO();
        PostDetailDTO postDetailDTO = new PostDetailDTO();

        final String UPLOAD_PATH = request.getSession().getServletContext().getRealPath("/") + "upload/";
        final int FILE_SIZE = 1024 * 1024 * 5; // 5MB

        MultipartParser parser = new MultipartParser(request, FILE_SIZE);
        parser.setEncoding("utf-8");

        int postNumber = 0;
        boolean isFileUpload = false;

        try {
            Part part;
            while ((part = parser.readNextPart()) != null) {

                if (part.isParam()) {
                    ParamPart paramPart = (ParamPart) part;
                    String name = paramPart.getName();
                    String value = paramPart.getStringValue();
                    System.out.println("파라미터: " + name + " = " + value);

                    switch (name) {
                        case "postNumber":
                            postNumber = Integer.parseInt(value);
                            postDetailDTO.setPostNumber(postNumber);
                            break;
                        case "postType":
                            postDetailDTO.setPostType(value);
                            break;
                        case "postTitle":
                            postDetailDTO.setPostTitle(value);
                            break;
                        case "freeContent":
                            postDetailDTO.setFreeContent(value);
                            break;
                        case "promoContent":
                            postDetailDTO.setPromoContent(value);
                            break;
                        case "recipeContent":
                            postDetailDTO.setRecipeContent(value);
                            break;
                    }

                } else if (part.isFile() && !isFileUpload) {
                    FilePart filePart = (FilePart) part;
                    filePart.setRenamePolicy(new DefaultFileRenamePolicy());
                    String originalFileName = filePart.getFileName();

                    // 기존 파일 삭제
                    if (postNumber != 0) {
                        List<PostImageDTO> existingFiles = postImageDAO.select(postNumber);
                        for (PostImageDTO file : existingFiles) {
                            File oldFile = new File(UPLOAD_PATH, file.getPostImageSystemName());
                            if (oldFile.exists()) {
                                oldFile.delete();
                                System.out.println("기존 파일 삭제: " + oldFile.getAbsolutePath());
                            }
                        }
                        postImageDAO.delete(postNumber);
                        System.out.println("기존 파일 DB 삭제 완료");
                    }

                    if (originalFileName != null) {
                        String newFileName = System.currentTimeMillis() + "_" + originalFileName;
                        File newFile = new File(UPLOAD_PATH, newFileName);
                        filePart.writeTo(newFile);

                        PostImageDTO postImageDTO = new PostImageDTO();
                        postImageDTO.setPostImageNumber(postNumber);
                        postImageDTO.setPostImageOriginalName(originalFileName);
                        postImageDTO.setPostImageSystemName(newFileName);
                        postImageDAO.insert(postImageDTO);

                        System.out.println("새 파일 업로드 완료: " + newFileName);
                        isFileUpload = true;
                    }
                }
            }

            // 게시글 DTO에 세션 멤버번호 세팅 (필요하면)
            postDetailDTO.setMemberNumber((Integer) request.getSession().getAttribute("memberNumber"));

            // 게시글 업데이트
            communityDAO.update(postDetailDTO);
            System.out.println("게시글 수정 완료");

            // 리다이렉트
            String path;
            switch (postDetailDTO.getPostType()) {
	            case "FREE":
	                path = request.getContextPath() + "/community/freeBoardReadOk.co?postNumber=" + postDetailDTO.getPostNumber();
	                break;
	            case "PROMOTION":
	                path = request.getContextPath() + "/community/promoBoardReadOk.co?postNumber=" + postDetailDTO.getPostNumber();
	                break;
	            case "RECIPE":
	                path = request.getContextPath() + "/community/recipeBoardReadOk.co?postNumber=" + postDetailDTO.getPostNumber();
	                break;
	            default:
	                path = request.getContextPath() + "/community/communityMainOk.co";
	        }
            result.setPath(path);
            result.setRedirect(true);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMsg", "게시글 수정 중 오류 발생: " + e.getMessage());
            result.setPath("/app/error/error.jsp");
            result.setRedirect(false);
        }

        return result;
    }
}
