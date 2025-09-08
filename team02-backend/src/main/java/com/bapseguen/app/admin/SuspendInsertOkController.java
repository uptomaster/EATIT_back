package com.bapseguen.app.admin;

import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.MemberSuspendDTO;

import java.time.LocalDate;

public class SuspendInsertOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) {
        Result result = new Result();
        AdminDAO adminDAO = new AdminDAO();

        int memberNumber = Integer.parseInt(request.getParameter("memberNumber"));

        MemberSuspendDTO dto = new MemberSuspendDTO();
        dto.setMemberNumber(memberNumber);

        // 현재 날짜 + 7일을 String으로 변환 (YYYY-MM-DD)
        String endDate = LocalDate.now().plusDays(7).toString();
        dto.setSuspendEndDate(endDate);

        dto.setSuspendReportCount(1); // 신고 횟수 1로 기본 세팅

        adminDAO.insertSuspend(dto);

        // 정지 목록으로 리다이렉트
        result.setPath(request.getContextPath() + "/admin/suspend/list.ad");
        result.setRedirect(true);
        return result;
    }
}
