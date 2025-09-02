package com.bapseguen.app.admin;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.AdminImageDTO;
import com.bapseguen.app.dto.view.AdminPostDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FaqUpdateOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] FAQ 수정 + 이미지 교체 처리");

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

        int postNumber = Integer.parseInt(multi.getParameter("postNumber"));
        String postTitle = multi.getParameter("postTitle");
        String faqContent = multi.getParameter("faqContent");

        AdminPostDTO dto = new AdminPostDTO();
        dto.setPostNumber(postNumber);
        dto.setPostTitle(postTitle);
        dto.setFaqContent(faqContent);

        AdminDAO dao = new AdminDAO();
        // 1) FAQ 기본정보 업데이트
        dao.updateFaqTitle(dto);
        dao.updateFaqContent(dto);

        // 2) 기존 이미지 삭제
        List<AdminImageDTO> oldImages = dao.selectAdminImagesByPost(postNumber);
        for (AdminImageDTO img : oldImages) {
            // DB 삭제
            dao.deleteAdminImagesByPost(postNumber);

            // 실제 파일 삭제
            File oldFile = new File(uploadPath, img.getAdminImageSystemName());
            if (oldFile.exists()) {
                oldFile.delete();
            }
        }

        // 3) 새 이미지 업로드 (있을 경우만)
        Enumeration<String> fileNames = multi.getFileNames();
        while (fileNames.hasMoreElements()) {
            String paramName = fileNames.nextElement();
            String systemName = multi.getFilesystemName(paramName);
            String originalName = multi.getOriginalFileName(paramName);

            if (systemName != null) {
                AdminImageDTO imgDTO = new AdminImageDTO();
                imgDTO.setAdminNumber(adminNumber);
                imgDTO.setPostNumber(postNumber);
                imgDTO.setAdminImageSystemName(systemName);
                imgDTO.setAdminImageOriginalName(originalName);

                dao.insertAdminImage(imgDTO);
            }
        }

        result.setPath(request.getContextPath() + "/admin/faq/detail.ad?postNumber=" + postNumber);
        result.setRedirect(true);
        return result;
    }
}
