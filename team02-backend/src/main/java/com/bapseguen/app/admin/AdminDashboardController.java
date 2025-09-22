package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.List;
import java.util.Map;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;
import com.bapseguen.app.dto.PostReportDTO;

public class AdminDashboardController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("[ADMIN] 대시보드 컨트롤러 시작");

		Result result = new Result();

		HttpSession session = request.getSession(false);
		if (session == null || !"ADMIN".equals(String.valueOf(session.getAttribute("memberType")))) {
			result.setPath(request.getContextPath() + "/admin/login.ad");
			result.setRedirect(true);
			return result;
		}

		AdminDAO dao = new AdminDAO();

		// 상단 통계
		int totalMembers = dao.memberListCount();
		int totalNotices = dao.countNotices();
		int totalFaqs = dao.countFaqs();
		int totalInquiries = dao.countInquiries();
		int unansweredInquiries = dao.countUnansweredInquiries();
		int totalReports = dao.countReports();
		int totalGeneralMembers = dao.countGeneralMembers();
		int totalSellerMembers = dao.countSellerMembers();
		int todayMembers = dao.countTodayMembers(); // 오늘 가입자 수 추가

		request.setAttribute("totalMembers", totalMembers);
		request.setAttribute("totalNotices", totalNotices);
		request.setAttribute("totalFaqs", totalFaqs);
		request.setAttribute("totalInquiries", totalInquiries);
		request.setAttribute("unansweredInquiries", unansweredInquiries);
		request.setAttribute("totalReports", totalReports);
		request.setAttribute("totalGeneralMembers", totalGeneralMembers);
		request.setAttribute("totalSellerMembers", totalSellerMembers);
		request.setAttribute("todayMembers", todayMembers); // 오늘 가입자 수 확인

		// 최근 데이터 
		List<AdminPostDTO> recentInquiries = dao.selectInquiryList(java.util.Map.of("startRow", 1, "endRow", 3));
		List<PostReportDTO> recentReports = dao.selectReportList();
		List<Map<String, Object>> recentMembers = dao.selectRecentMembers();
		List<Map<String, Object>> recentOrders = dao.selectRecentOrders();

		request.setAttribute("recentInquiries", recentInquiries);
		request.setAttribute("recentReports", recentReports);
		request.setAttribute("recentMembers", recentMembers);
		request.setAttribute("recentOrders", recentOrders);

		// 차트 데이터
		List<Map<String, Object>> monthlyMembers = dao.countMonthlyMembers();
		List<Map<String, Object>> categorySales = dao.countCategorySales();

		request.setAttribute("monthlyMembers", monthlyMembers);
		request.setAttribute("categorySales", categorySales);

		// 대시보드로 포워딩
		result.setPath("/app/admin/dashboard.jsp");
		result.setRedirect(false);

		System.out.println("[ADMIN] 대시보드 컨트롤러 완료");
		return result;
	}
}
