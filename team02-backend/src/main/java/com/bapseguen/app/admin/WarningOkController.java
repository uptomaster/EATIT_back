package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.MemberDTO;

public class WarningOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[ADMIN] 경고 주기 처리 시작");

        int memberNumber = Integer.parseInt(request.getParameter("memberNumber"));
        String memberType = request.getParameter("memberType");
        int warningCount = Integer.parseInt(request.getParameter("warningCount"));

        AdminDAO dao = new AdminDAO();
        dao.giveWarning(memberNumber, memberType, warningCount);

        Result result = new Result();
        result.setPath(request.getContextPath() + "/admin/memberlistlist.ad");
        result.setRedirect(true);

        System.out.println("[ADMIN] 경고 처리 완료");
        return result;
    }
}
