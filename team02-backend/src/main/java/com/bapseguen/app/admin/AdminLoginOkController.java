package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.MemberDTO;

public class AdminLoginOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 관리자 로그인 처리 요청");

        Result result = new Result();

        String adminIdInput = request.getParameter("adminId");
        String adminPwInput = request.getParameter("adminPw");

        if (adminIdInput == null || adminIdInput.isBlank() ||
            adminPwInput == null || adminPwInput.isBlank()) {
            request.setAttribute("loginError", "아이디와 비밀번호를 입력하세요.");
            request.setAttribute("inputAdminId", adminIdInput);
            result.setPath("/app/admin/adminLogin.jsp");
            result.setRedirect(false);
            return result;
        }

        MemberDTO dto = new MemberDTO();
        dto.setMemberId(adminIdInput);
        dto.setMemberPassword(adminPwInput);

        AdminDAO dao = new AdminDAO();
        int memberNumber = dao.loginAdmin(dto); // 일치하면 회원번호, 아니면 -1

        if (memberNumber > 0) {
            HttpSession session = request.getSession();
            session.setAttribute("adminNumber", memberNumber); // 프로젝트에서 쓰는 키 유지
            session.setAttribute("memberId", adminIdInput);    // DTO/DB 명칭과 통일
            session.setAttribute("memberType", "ADMIN");       // 통일 포인트

            result.setPath(request.getContextPath() + "/admin/main.ad");
            result.setRedirect(true);
        } else {
            request.setAttribute("loginError", "일치하는 관리자 정보가 없습니다.");
            request.setAttribute("inputAdminId", adminIdInput);
            result.setPath("/app/admin/adminLogin.jsp");
            result.setRedirect(false);
        }

        return result;
    }
}
