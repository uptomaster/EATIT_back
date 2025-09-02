package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.MemberBlacklistDTO;

public class BlacklistInsertOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 블랙리스트 등록 처리");

        int memberNumber = Integer.parseInt(request.getParameter("memberNumber"));

        MemberBlacklistDTO dto = new MemberBlacklistDTO();
        dto.setMemberNumber(memberNumber);
        dto.setBlacklistStartDate(java.time.LocalDate.now().toString());

        AdminDAO dao = new AdminDAO();
        dao.insertBlacklist(dto);

        Result result = new Result();
        result.setPath(request.getContextPath() + "/admin/blacklist/list.ad");
        result.setRedirect(true);
        return result;
    }
}
