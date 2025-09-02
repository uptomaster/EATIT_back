package com.bapseguen.app.admin;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.AdminImageDTO;
import com.bapseguen.app.dto.view.AdminPostDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class NoticeWriteOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 공지 작성 + 이미지 업로드 처리");

        Result result = new Result();
        HttpSession session = request.getSession();
        Integer adminNumber = (Integer) session.getAttribute("adminNumber");

        // 업로드 경로 (웹 서버 기준)
        String uploadPath = request.getServletContext().getRealPath("/") + "upload/admin/";
        File folder = new File(uploadPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        int fileSize = 1024 * 1024 * 10; // 10MB 제한
        MultipartRequest multi = new MultipartRequest(
                request,
                uploadPath,
                fileSize,
                "UTF-8",
                new DefaultFileRenamePolicy()
        );

        // 글 데이터
        String postTitle = multi.getParameter("postTitle");
        String noticeContent = multi.getParameter("noticeContent");

        AdminPostDTO postDTO = new AdminPostDTO();
        postDTO.setAdminNumber(adminNumber);
        postDTO.setPostTitle(postTitle);
        postDTO.setNoticeContent(noticeContent);

        AdminDAO dao = new AdminDAO();
        dao.insertNoticePost(postDTO); // TBL_POST + TBL_NOTICE insert

        // 업로드된 이미지 처리
        Enumeration<String> fileNames = multi.getFileNames();
        while (fileNames.hasMoreElements()) {
            String paramName = fileNames.nextElement();
            String systemName = multi.getFilesystemName(paramName);   // 서버에 저장된 이름
            String originalName = multi.getOriginalFileName(paramName); // 사용자가 업로드한 이름

            if (systemName != null) {
                AdminImageDTO imageDTO = new AdminImageDTO();
                imageDTO.setAdminNumber(adminNumber);
                imageDTO.setPostNumber(postDTO.getPostNumber()); // insert 후 CURRVAL or selectKey로 세팅
                imageDTO.setAdminImageSystemName(systemName);
                imageDTO.setAdminImageOriginalName(originalName);

                dao.insertAdminImage(imageDTO);
            }
        }

        result.setPath(request.getContextPath() + "/admin/notice/list.ad");
        result.setRedirect(true);
        return result;
    }
}
