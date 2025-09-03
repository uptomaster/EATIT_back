package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.AdminDTO;

public class AdminLoginOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 관리자 로그인 처리 요청");

        Result result = new Result();

        String adminIdInput = request.getParameter("adminId");
        String adminPwInput = request.getParameter("adminPw");

        // 입력값 검증
        if (adminIdInput == null || adminIdInput.isBlank() ||
            adminPwInput == null || adminPwInput.isBlank()) {
            request.setAttribute("loginError", "아이디와 비밀번호를 입력하세요.");
            request.setAttribute("inputAdminId", adminIdInput);
            result.setPath("/app/admin/adminLogin.jsp");
            result.setRedirect(false);
            return result;
        }

        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setAdminId(adminIdInput);
        adminDTO.setAdminPassword(adminPwInput);

        AdminDAO dao = new AdminDAO();
        AdminDTO loginAdmin = dao.loginAdmin(adminDTO);

        if (loginAdmin != null) {
            // 로그인 성공 → 세션 저장
            HttpSession session = request.getSession();
            session.setAttribute("adminNumber", loginAdmin.getAdminNumber());
            session.setAttribute("adminId", loginAdmin.getAdminId());
            session.setAttribute("adminGrade", loginAdmin.getAdminTreeGrade());
            session.setAttribute("memberType", "ADMIN");

            result.setPath(request.getContextPath() + "/admin/dashboard.ad");
            result.setRedirect(true);
        } else {
            // 로그인 실패
            request.setAttribute("loginError", "일치하는 관리자 정보가 없습니다.");
            request.setAttribute("inputAdminId", adminIdInput);
            result.setPath("/app/admin/adminLogin.jsp");
            result.setRedirect(false);
        }

        return result;
    }
}
