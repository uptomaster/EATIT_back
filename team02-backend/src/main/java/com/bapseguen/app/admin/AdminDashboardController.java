package com.bapseguen.app.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;

public class AdminDashboardController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 대시보드 컨트롤러 시작");

        Result result = new Result();

        // 로그인/권한 체크
        HttpSession session = request.getSession(false);
        if (session == null || !"ADMIN".equals(String.valueOf(session.getAttribute("memberType")))) {
            System.out.println("[ADMIN] 로그인 세션 없음 → 로그인 페이지로 이동");
            result.setPath(request.getContextPath() + "/admin/login.ad");
            result.setRedirect(true);
            return result;
        }

        // ===== 대시보드 통계 조회 =====
        AdminDAO dao = new AdminDAO();
        int totalMembers = dao.memberListCount();
        int totalNotices = dao.countNotices();
        int totalFaqs = dao.countFaqs();
        int totalInquiries = dao.countInquiries();
        int unansweredInquiries = dao.countUnansweredInquiries();
        int totalReports = dao.countReports();
        int activeBanners = dao.countActiveBanners();

        // JSP에 데이터 전달
        request.setAttribute("totalMembers", totalMembers);
        request.setAttribute("totalNotices", totalNotices);
        request.setAttribute("totalFaqs", totalFaqs);
        request.setAttribute("totalInquiries", totalInquiries);
        request.setAttribute("unansweredInquiries", unansweredInquiries);
        request.setAttribute("totalReports", totalReports);
        request.setAttribute("activeBanners", activeBanners);

        // JSP forward
        result.setPath("/app/admin/dashboard.jsp");
        result.setRedirect(false);

        return result;
    }
}
