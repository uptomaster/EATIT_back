package com.bapseguen.app.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.PostReportDTO;

public class ReportListController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("[ADMIN] 신고 목록 요청");
        AdminDAO dao = new AdminDAO();
        request.setAttribute("reportList", dao.selectReportList());
        Result result = new Result();
        result.setPath("/app/admin/reportList.jsp");
        result.setRedirect(false);
        return result;
    }
}

