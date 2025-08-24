package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;

public class InquiryCommentOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//    	아직 미완성
        System.out.println("[ADMIN] 문의 댓글 등록 (아직 댓글파트 안되었음)");

        HttpSession s = request.getSession(false);
        if (s == null || !"ADMIN".equals(String.valueOf(s.getAttribute("memberType")))) {
            Result r = new Result();
            r.setPath(request.getContextPath() + "/admin/login.ad");
            r.setRedirect(true);
            return r;
        }

        // AdminDAO.insertInquiryComment구현 후 연결 예정
        Result result = new Result();
        result.setPath(request.getContextPath() + "/admin/inquiry/list.ad");
        result.setRedirect(true);
        return result;
    }
}
