package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.MemberSuspendDTO;

public class SuspendInsertOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 회원 정지 등록 처리");

        int memberNumber = Integer.parseInt(request.getParameter("memberNumber"));
        String endDate = request.getParameter("suspendEndDate"); // YYYY-MM-DD
        int reportCount = Integer.parseInt(request.getParameter("suspendReportCount"));

        MemberSuspendDTO dto = new MemberSuspendDTO();
        dto.setMemberNumber(memberNumber);
        dto.setSuspendEndDate(endDate);
        dto.setSuspendReportCount(reportCount);

        AdminDAO dao = new AdminDAO();
        dao.insertSuspend(dto);

        Result result = new Result();
        result.setPath(request.getContextPath() + "/admin/suspend/list.ad");
        result.setRedirect(true);
        return result;
    }
}
