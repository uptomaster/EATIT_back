package com.bapseguen.app.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.MemberBlacklistDTO;

public class BlacklistListController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 블랙리스트 목록 요청");

        AdminDAO dao = new AdminDAO();
        List<MemberBlacklistDTO> blacklist = dao.selectBlacklistList();

        request.setAttribute("blacklist", blacklist);

        Result result = new Result();
        result.setPath("/app/admin/blacklistList.jsp");
        result.setRedirect(false);
        return result;
    }
}
