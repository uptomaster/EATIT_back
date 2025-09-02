package com.bapseguen.app.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.AdminImageDTO;

public class FaqDeleteOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] FAQ 삭제 처리");

        Result result = new Result();
        AdminDAO dao = new AdminDAO();

        int postNumber = Integer.parseInt(request.getParameter("postNumber"));

        // 1) 기존 이미지 조회
        List<AdminImageDTO> images = dao.selectAdminImagesByPost(postNumber);

        // 2) DB에서 이미지 삭제
        dao.deleteAdminImagesByPost(postNumber);

        // 3) 실제 파일 삭제
        String uploadPath = request.getServletContext().getRealPath("/") + "upload/admin/faq/";
        for (AdminImageDTO img : images) {
            File file = new File(uploadPath, img.getAdminImageSystemName());
            if (file.exists()) {
                file.delete();
            }
        }

        // 4) 게시글 삭제 (TBL_POST 삭제 시 CASCADE로 TBL_FAQ도 같이 삭제됨)
        dao.deleteFaq(postNumber);

        result.setPath(request.getContextPath() + "/admin/faq/list.ad");
        result.setRedirect(true);
        return result;
    }
}
