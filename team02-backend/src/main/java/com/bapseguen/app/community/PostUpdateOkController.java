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
        CommunityDAO communityDAO = new CommunityDAO();
        PostDetailDTO postDetailDTO = new PostDetailDTO();
        PostImageDAO postImageDAO = new PostImageDAO();
        Result result = new Result();

        final String UPLOAD_PATH = request.getSession().getServletContext().getRealPath("/") + "upload/";
        final int FILE_SIZE = 1024 * 1024 * 5; // 5MB

        // MultipartParser 실행
        MultipartParser parser = new MultipartParser(request, FILE_SIZE);
        parser.setEncoding("utf-8");
        System.out.println("MultipartParser 초기화 완료");

        int postNumber = 0;
        boolean isFileUpload = false;

        Part part;
        while ((part = parser.readNextPart()) != null) {

            if (part.isParam()) {
                ParamPart paramPart = (ParamPart) part;
                String paramName = paramPart.getName();
                String paramValue = paramPart.getStringValue();
                System.out.println("파라미터: " + paramName + " = " + paramValue);

                // 게시글 번호 DTO에 세팅
                if ("postNumber".equals(paramName)) {
                    postNumber = Integer.parseInt(paramValue);
                    postDetailDTO.setPostNumber(postNumber);
                } 
                // 게시판 타입
                else if ("postType".equals(paramName)) {
                    postDetailDTO.setPostType(paramValue);
                } 
                // 게시글 제목
                else if ("postTitle".equals(paramName)) {
                    postDetailDTO.setPostTitle(paramValue);
                }
                // 게시글 내용
                else if ("freeContent".equals(paramName)) {
                    postDetailDTO.setFreeContent(paramValue);
                } else if ("promoContent".equals(paramName)) {
                    postDetailDTO.setPromoContent(paramValue);
                } else if ("recipeContent".equals(paramName)) {
                    postDetailDTO.setRecipeContent(paramValue);
                }

            } else if (part.isFile() && !isFileUpload) {
                FilePart filePart = (FilePart) part;
                filePart.setRenamePolicy(new DefaultFileRenamePolicy());
                String fileOriginalName = filePart.getFileName();

                // 기존 파일 삭제
                if (postNumber != 0) {
                    List<PostImageDTO> existingFiles = postImageDAO.select(postNumber);
                    for (PostImageDTO file : existingFiles) {
                        File oldFile = new File(UPLOAD_PATH, file.getPostImageSystemName());
                        if (oldFile.exists()) {
                            System.out.println("기존 파일 삭제: " + oldFile.getAbsolutePath());
                            oldFile.delete();
                        }
                    }
                    postImageDAO.delete(postNumber);
                    System.out.println("기존 파일 DB 삭제 완료");
                }

                // 새 파일 업로드
                if (fileOriginalName != null) {
                    String newFileName = System.currentTimeMillis() + "_" + fileOriginalName;
                    File newFile = new File(UPLOAD_PATH, newFileName);
                    filePart.writeTo(newFile);

                    // DB 저장
                    PostImageDTO postImageDTO = new PostImageDTO();
                    postImageDTO.setPostImageSystemName(newFileName);
                    postImageDTO.setPostImageOriginalName(fileOriginalName);
                    postImageDTO.setPostImageNumber(postNumber);
                    postImageDAO.insert(postImageDTO);
                    System.out.println("새로운 파일 DB 저장 완료: " + postImageDTO);

                    isFileUpload = true;
                } else {
                    System.out.println("업로드된 파일이 없습니다 (파일 선택하지 않음)");
                }
            }
        }

        // 게시글 작성자 번호 세팅
        postDetailDTO.setMemberNumber((Integer) request.getSession().getAttribute("memberNumber"));

        // 게시글 업데이트 실행
        communityDAO.update(postDetailDTO);
        System.out.println("게시글 수정 완료");

        // 게시판 타입에 따라 분기
        String postType = postDetailDTO.getPostType();
        switch (postType) {
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
                result.setPath("/community/communityMainOk.co");
                break;
        }

        result.setRedirect(true);
        return result;
    }
}
