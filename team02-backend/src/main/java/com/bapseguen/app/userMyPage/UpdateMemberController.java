package com.bapseguen.app.userMyPage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.userMyPage.dao.UserMyPageDAO;

public class UpdateMemberController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        Result result = new Result();
        HttpSession session = request.getSession(false);
        Integer memberNumber = (session != null) ? (Integer) session.getAttribute("memberNumber") : null;

        if (memberNumber == null) {
            result.setRedirect(true);
            result.setPath(request.getContextPath() + "/login/login.lo?login=required");
            return result;
        }

        String currentPw   = request.getParameter("currentPassword");
        String newPw       = request.getParameter("newPassword");
        String confirmPw   = request.getParameter("confirmPassword");
        String newPhone    = request.getParameter("newPhone");

        UserMyPageDAO dao = new UserMyPageDAO();

        // 1) 현재 비밀번호 검증
        if (currentPw == null || currentPw.isEmpty() || !dao.checkPassword(memberNumber, currentPw)) {
            request.setAttribute("updateError", "현재 비밀번호가 일치하지 않습니다.");
            // 다시 편집화면으로 forward
            request.setAttribute("my", dao.MyPageSelect(memberNumber));
            result.setRedirect(false);
            result.setPath("/app/userMyPage/editUserInfo.jsp");
            return result;
        }

        // 2) 새 비번 변경
        if (newPw != null && !newPw.isEmpty()) {
            if (!newPw.equals(confirmPw)) {
                request.setAttribute("updateError", "새 비밀번호가 서로 일치하지 않습니다.");
                request.setAttribute("my", dao.MyPageSelect(memberNumber));
                result.setRedirect(false);
                result.setPath("/app/userMyPage/editUserInfo.jsp");
                return result;
            }
            dao.updatePassword(memberNumber, newPw);
        }

        // 3) 새 전화번호 변경(간단 적용)
        if (newPhone != null && !newPhone.isEmpty()) {
            if (Boolean.TRUE.equals(session.getAttribute("phoneVerified"))) {
                String digits = newPhone.replaceAll("\\D", "");
                dao.updatePhone(memberNumber, digits);

                // 사용 후 초기화(다음 변경엔 다시 인증)
                session.removeAttribute("phoneVerified");
                session.removeAttribute("pendingPhoneTarget");
                session.removeAttribute("devPhoneCode");
            } else {
                request.setAttribute("updateError", "전화번호 인증이 필요합니다.");
                request.setAttribute("my", dao.MyPageSelect(memberNumber));
                result.setRedirect(false);
                result.setPath("/app/userMyPage/editUserInfo.jsp");
                return result;
            }
        }

        // 성공 후 리다이렉트(새로고침 중복방지)
        result.setRedirect(true);
        result.setPath(request.getContextPath() + "/UserMyPage/editUserInfo.my?updated=1");
        return result;
    }
}