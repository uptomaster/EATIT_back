package com.bapseguen.app.community;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.community.dao.CommunityDAO;
import com.bapseguen.app.dto.PostImageDTO;
import com.bapseguen.app.img.dao.PostImageDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class WriteInquiryOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Result result = new Result();
        PostImageDAO postImageDAO = new PostImageDAO();
        CommunityDAO communityDAO = new CommunityDAO();

        // 로그인 체크
        Object attr = request.getSession().getAttribute("memberNumber");
        if (attr == null) {
            result.setPath(request.getContextPath() + "/app/login/login.jsp");
            result.setRedirect(true);
            return result;
        }
        int memberNumber = ((Number) attr).intValue();

        // 업로드 경로 및 제한
        final String UPLOAD_PATH = request.getSession().getServletContext().getRealPath("/") + "upload/";
        final int FILE_SIZE = 1024 * 1024 * 5; // 5MB
        File dir = new File(UPLOAD_PATH);
        if (!dir.exists()) dir.mkdirs();

        // multipart 처리
        MultipartRequest multi = new MultipartRequest(
                request, UPLOAD_PATH, FILE_SIZE, "UTF-8", new DefaultFileRenamePolicy());

        // 게시글 파라미터
        String postTitle = multi.getParameter("postTitle");
        String content = multi.getParameter("inquiryContent");

        if (postTitle == null || postTitle.isBlank() || content == null || content.isBlank()) {
            request.setAttribute("error", "제목/내용을 입력하세요.");
            result.setPath("/app/community/writeCustomerService.jsp");
            result.setRedirect(false);
            return result;
        }

        Map<String, Object> postParams = new HashMap<>();
        postParams.put("memberNumber", memberNumber);
        postParams.put("postTitle", postTitle);
        postParams.put("inquiryContent", content);

        // 1. 게시글 insert
        int postNumber = communityDAO.insertInquiryPost(postParams);

        // 2. 파일 업로드 반복 (게시글 insert 후)
        Enumeration<?> files = multi.getFileNames();
        while (files.hasMoreElements()) {
            String fileName = (String) files.nextElement();
            String originalName = multi.getOriginalFileName(fileName);
            String savedName = multi.getFilesystemName(fileName);

            if (savedName != null) {
                PostImageDTO fileDTO = new PostImageDTO();
                fileDTO.setPostImageOriginalName(originalName);
                fileDTO.setPostImageSystemName(savedName);
                fileDTO.setPostNumber(postNumber);
                postImageDAO.insert(fileDTO);
            }
        }

        // 3. 완료 후 목록 페이지로 이동
        result.setPath(request.getContextPath() + "/community/inquiryListOk.co");
        result.setRedirect(true);
        return result;
    }
}

