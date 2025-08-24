package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.InquiryDTO;

public class InquiryDetailController implements Execute {
    private int toInt(String v){ try { return Integer.parseInt(v); } catch(Exception e){ return -1; } }

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 문의글 상세");

        HttpSession s = request.getSession(false);
        if (s == null || !"ADMIN".equals(String.valueOf(s.getAttribute("memberType")))) {
            Result r = new Result();
            r.setPath(request.getContextPath() + "/admin/login.ad");
            r.setRedirect(true);
            return r;
        }

        int postNumber = toInt(request.getParameter("postNumber"));
        InquiryDTO dto = new AdminDAO().selectInquiryDetail(postNumber);
        request.setAttribute("inquiry", dto);

        Result result = new Result();
        result.setPath("/app/admin/inquiry/inquiryDetail.jsp");
        result.setRedirect(false);
        return result;
    }
}
