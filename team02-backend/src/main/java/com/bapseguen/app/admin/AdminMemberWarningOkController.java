package com.bapseguen.app.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;

public class AdminMemberWarningOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("==== AdminMemberWarningOkController 실행 ====");

        String tempNumber = request.getParameter("memberNumber");
        String memberType = request.getParameter("memberType");

        Result result = new Result();

        if (tempNumber == null || tempNumber.isBlank() || memberType == null || memberType.isBlank()) {
            System.out.println("⚠ 파라미터 누락 → 목록으로 이동");
            result.setPath(request.getContextPath() + "/admin/member/list.ad");
            result.setRedirect(true);
            return result;
        }

        try {
            int memberNumber = Integer.parseInt(tempNumber);

            AdminDAO adminDAO = new AdminDAO();
            adminDAO.giveWarning(memberNumber, memberType);

            // 알림창 띄우고 목록으로 리다이렉트
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print("<script>alert('경고가 부여되었습니다.'); location.href='" 
                                        + request.getContextPath() + "/admin/member/list.ad';</script>");
            response.getWriter().close();
            return null; // 이미 직접 응답했으므로 Result 반환하지 않음

        } catch (NumberFormatException e) {
            System.out.println("⚠ memberNumber 파싱 실패: " + tempNumber);
            result.setPath(request.getContextPath() + "/admin/member/list.ad");
            result.setRedirect(true);
            return result;
        }
    }
}
