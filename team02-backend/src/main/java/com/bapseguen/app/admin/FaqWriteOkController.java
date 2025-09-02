package com.bapseguen.app.admin;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.AdminImageDTO;
import com.bapseguen.app.dto.view.AdminPostDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FaqWriteOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] FAQ 작성 + 이미지 업로드 처리");

        Result result = new Result();
        HttpSession session = request.getSession();
        Integer adminNumber = (Integer) session.getAttribute("adminNumber");

        // 업로드 경로
        String uploadPath = request.getServletContext().getRealPath("/") + "upload/admin/faq/";
        File folder = new File(uploadPath);
        if (!folder.exists()) { folder.mkdirs(); }

        int fileSize = 1024 * 1024 * 10; // 10MB
        MultipartRequest multi = new MultipartRequest(
                request,
                uploadPath,
                fileSize,
                "UTF-8",
                new DefaultFileRenamePolicy()
        );

        String postTitle = multi.getParameter("postTitle");
        String faqContent = multi.getParameter("faqContent");

        AdminPostDTO postDTO = new AdminPostDTO();
        postDTO.setAdminNumber(adminNumber);
        postDTO.setPostTitle(postTitle);
        postDTO.setFaqContent(faqContent);

        AdminDAO dao = new AdminDAO();
        dao.insertFaqPost(postDTO); // TBL_POST insert
        dao.insertFaq(postDTO);     // TBL_FAQ insert

        // 파일 업로드 처리
        Enumeration<String> fileNames = multi.getFileNames();
        while (fileNames.hasMoreElements()) {
            String paramName = fileNames.nextElement();
            String systemName = multi.getFilesystemName(paramName);
            String originalName = multi.getOriginalFileName(paramName);

            if (systemName != null) {
                AdminImageDTO imageDTO = new AdminImageDTO();
                imageDTO.setAdminNumber(adminNumber);
                imageDTO.setPostNumber(postDTO.getPostNumber());
                imageDTO.setAdminImageSystemName(systemName);
                imageDTO.setAdminImageOriginalName(originalName);

                dao.insertAdminImage(imageDTO);
            }
        }

        result.setPath(request.getContextPath() + "/admin/faq/list.ad");
        result.setRedirect(true);
        return result;
    }
}
