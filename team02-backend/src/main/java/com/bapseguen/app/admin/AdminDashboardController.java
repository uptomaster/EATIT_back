package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;
import com.bapseguen.app.dto.PostReportDTO;

import java.util.List;

public class AdminDashboardController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 대시보드 컨트롤러 시작");

        Result result = new Result();

        // ===== 로그인/권한 체크 =====
        HttpSession session = request.getSession(false);
        if (session == null || !"ADMIN".equals(String.valueOf(session.getAttribute("memberType")))) {
            System.out.println("[ADMIN] 로그인 세션 없음 → 로그인 페이지로 이동");
            result.setPath(request.getContextPath() + "/admin/login.ad");
            result.setRedirect(true);
            return result;
        }

        // ===== DAO 준비 =====
        AdminDAO dao = new AdminDAO();

        // ===== 대시보드 주요 통계 =====
        int totalMembers = dao.memberListCount();
        int totalNotices = dao.countNotices();
        int totalFaqs = dao.countFaqs();
        int totalInquiries = dao.countInquiries();
        int unansweredInquiries = dao.countUnansweredInquiries();
        int totalReports = dao.countReports();

        // JSP에 데이터 전달
        request.setAttribute("totalMembers", totalMembers);
        request.setAttribute("totalNotices", totalNotices);
        request.setAttribute("totalFaqs", totalFaqs);
        request.setAttribute("totalInquiries", totalInquiries);
        request.setAttribute("unansweredInquiries", unansweredInquiries);
        request.setAttribute("totalReports", totalReports);

        // ===== 최근 문의글 / 신고글 =====
        List<AdminPostDTO> recentInquiries = dao.selectInquiryList(
            java.util.Map.of("startRow", 1, "endRow", 3)  // 최근 3개만
        );
        List<PostReportDTO> recentReports = dao.selectReportList();

        request.setAttribute("recentInquiries", recentInquiries);
        request.setAttribute("recentReports", recentReports);

        // ===== JSP forward =====
        result.setPath("/app/admin/dashboard.jsp");
        result.setRedirect(false);

        System.out.println("[ADMIN] 대시보드 컨트롤러 완료");
        return result;
    }
}
