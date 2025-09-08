package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;

public class BlacklistDeleteOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("→ [ADMIN] BlacklistDeleteOkController 실행");

        int blacklistNumber = Integer.parseInt(request.getParameter("blacklistNumber"));

        AdminDAO adminDAO = new AdminDAO();
        adminDAO.deleteBlacklist(blacklistNumber);

        Result result = new Result();
        result.setPath(request.getContextPath() + "/admin/blacklist/list.ad");
        result.setRedirect(true);
        return result;
    }
}
