package com.bapseguen.app.admin;

import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.MemberBlacklistDTO;

public class BlacklistInsertOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) {
        Result result = new Result();
        AdminDAO adminDAO = new AdminDAO();

        int memberNumber = Integer.parseInt(request.getParameter("memberNumber"));

        MemberBlacklistDTO dto = new MemberBlacklistDTO();
        dto.setMemberNumber(memberNumber);

        adminDAO.insertBlacklist(dto);

        // 블랙리스트 목록으로 리다이렉트
        result.setPath(request.getContextPath() + "/admin/blacklist/list.ad");
        result.setRedirect(true);
        return result;
    }
}
