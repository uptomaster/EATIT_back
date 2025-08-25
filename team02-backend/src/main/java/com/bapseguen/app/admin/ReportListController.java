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

        System.out.println("==== ReportListController 실행 ====");

        // 1. DAO 호출
        AdminDAO adminDAO = new AdminDAO();
        List<PostReportDTO> reportList = adminDAO.selectReportList();

        // 2. request에 저장
        request.setAttribute("reportList", reportList);

        // 3. 이동 경로 지정 (포워드)
        Result result = new Result();
        result.setPath("/app/admin/reportList.jsp");
        result.setRedirect(false);

        System.out.println("==== ReportListController 완료 ====");
        return result;
    }
}
