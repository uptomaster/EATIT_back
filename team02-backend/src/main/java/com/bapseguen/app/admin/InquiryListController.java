package com.bapseguen.app.admin;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.InquiryDTO;

public class InquiryListController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 문의글 목록");

        HttpSession s = request.getSession(false);
        if (s == null || !"ADMIN".equals(String.valueOf(s.getAttribute("memberType")))) {
            Result r = new Result();
            r.setPath(request.getContextPath() + "/admin/login.ad");
            r.setRedirect(true);
            return r;
        }

        List<InquiryDTO> list = new AdminDAO().selectInquiryList();
        request.setAttribute("inquiryList", list);

        Result result = new Result();
        result.setPath("/app/admin/inquiry/inquiryList.jsp");
        result.setRedirect(false);
        return result;
    }
}
