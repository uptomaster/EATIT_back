package com.bapseguen.app.admin;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

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

        System.out.println("[ADMIN] 공지/이벤트 글 작성 처리");

        Result result = new Result();
        AdminDAO dao = new AdminDAO();

        // 업로드 경로
        String uploadPath = request.getServletContext().getRealPath("/") + "upload/admin/";
        File folder = new File(uploadPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // multipart 처리
        MultipartRequest multi = new MultipartRequest(
                request,
                uploadPath,
                1024 * 1024 * 20, // 20MB
                "UTF-8",
                new DefaultFileRenamePolicy()
        );

        // 로그인한 관리자 번호
        HttpSession session = request.getSession();
        int adminNumber = (int) session.getAttribute("adminNumber");

        // 게시글 정보
        AdminPostDTO postDTO = new AdminPostDTO();
        postDTO.setAdminNumber(adminNumber);
        postDTO.setPostTitle(multi.getParameter("postTitle"));
        postDTO.setNoticeContent(multi.getParameter("noticeContent"));

        // 1) 게시글 등록
        dao.insertNoticePost(postDTO);
        dao.insertNotice(postDTO);

        // 2) 이미지 등록
        Enumeration<?> fileNames = multi.getFileNames();
        while (fileNames.hasMoreElements()) {
            String paramName = (String) fileNames.nextElement();
            String systemName = multi.getFilesystemName(paramName);
            String originalName = multi.getOriginalFileName(paramName);

            if (systemName != null) {
                AdminImageDTO imgDTO = new AdminImageDTO();
                imgDTO.setAdminImageSystemName(systemName);
                imgDTO.setAdminImageOriginalName(originalName);
                imgDTO.setAdminNumber(adminNumber);
                imgDTO.setPostNumber(postDTO.getPostNumber());

                dao.insertAdminImage(imgDTO);
            }
        }

        result.setPath(request.getContextPath() + "/admin/notice/list.ad");
        result.setRedirect(true);
        return result;
    }
}
