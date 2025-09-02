package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;

public class FaqDeleteOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] FAQ 삭제 처리");

        int postNumber = Integer.parseInt(request.getParameter("postNumber"));

        AdminDAO dao = new AdminDAO();
        dao.deleteFaq(postNumber);

        Result result = new Result();
        result.setPath(request.getContextPath() + "/admin/faq/list.ad");
        result.setRedirect(true);
        return result;
    }
}
