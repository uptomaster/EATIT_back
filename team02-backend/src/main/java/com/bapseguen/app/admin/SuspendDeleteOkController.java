package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;

public class SuspendDeleteOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("→ [ADMIN] SuspendDeleteOkController 실행");

        int memberNumber = Integer.parseInt(request.getParameter("memberNumber"));

        AdminDAO adminDAO = new AdminDAO();
        adminDAO.deleteSuspend(memberNumber);

        Result result = new Result();
        result.setPath(request.getContextPath() + "/admin/suspend/list.ad");
        result.setRedirect(true);
        return result;
    }
}
