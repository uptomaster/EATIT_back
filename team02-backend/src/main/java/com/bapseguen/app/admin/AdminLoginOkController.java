package com.bapseguen.app.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.AdminDTO;
import com.bapseguen.app.dto.MemberDTO;

public class AdminLoginOkController implements Execute {

    private final AdminDAO adminDAO = new AdminDAO();

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 관리자 로그인 처리 요청");

        Result result = new Result();

        String adminId = request.getParameter("adminId");
        String adminPassword = request.getParameter("adminPw");

        // 1) 입력값 검증
        if (adminId == null || adminId.isBlank() || adminPassword == null || adminPassword.isBlank()) {
            request.setAttribute("loginError", "아이디와 비밀번호를 입력하세요.");
            request.setAttribute("inputAdminId", adminId);
            result.setPath("/app/admin/adminLogin.jsp");
            result.setRedirect(false);
            return result;
        }

        // 2) 인증
        AdminDTO admin = adminDAO.loginAdmin(MemberDTO dto);

        if (admin != null) {
            // 3) 세션 저장
            HttpSession session = request.getSession();
            session.setAttribute("MemberNumber", admin.getMemberNumber());
            session.setAttribute("MemberId", admin.getAdminId());
            session.setAttribute("Name", admin.getAdminName());
            session.setAttribute("memberType", "ADMIN");

            // 4) 성공 시 관리자 메인으로
            // 프론트컨트롤러 매핑 사용 시: "/admin/main.ad" 등으로 변경
            result.setPath("/app/admin/adminMain.jsp");
            result.setRedirect(true);
        } else {
            // 실패 시 메시지 + 입력 아이디 유지
            request.setAttribute("loginError", "일치하는 관리자 정보가 없습니다.");
            request.setAttribute("inputAdminId", adminId);
            result.setPath("/app/admin/adminLogin.jsp");
            result.setRedirect(false);
        }

        return result;
    }
}
