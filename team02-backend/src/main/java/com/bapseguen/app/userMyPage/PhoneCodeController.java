package com.bapseguen.app.userMyPage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;

public class PhoneCodeController implements Execute {

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

        String mode = request.getParameter("mode");          // "send" 또는 "check"
        String newPhone = request.getParameter("newPhone");  // 사용자가 입력한 새 번호
        String codeInput = request.getParameter("phoneCode");// 사용자가 입력한 코드

        if ("send".equals(mode)) {
            String digits = (newPhone == null ? "" : newPhone.replaceAll("\\D", ""));
            // 엄청 단순 검증(길이만)
            if (digits.length() < 10) {
                flash(session, "전화번호 형식이 올바르지 않습니다.", "red", null);
            } else {
                int code = (int)(Math.random() * 900000) + 100000; // 100000~999999
                session.setAttribute("pendingPhoneTarget", digits);
                session.setAttribute("devPhoneCode", String.valueOf(code));
                // 실제 SMS 연동 대신, 개발용으로 코드 노출
                flash(session, "인증번호를 전송했습니다.", "green", String.valueOf(code));
            }
        } else if ("check".equals(mode)) {
            String savedCode = (String) session.getAttribute("devPhoneCode");

            if (savedCode != null && savedCode.equals(codeInput)) {
                session.setAttribute("phoneVerified", Boolean.TRUE);
                flash(session, "전화번호 인증이 완료되었습니다.", "green", null);
            } else {
                flash(session, "인증번호가 올바르지 않습니다.", "red", null);
            }
        } else {
            flash(session, "잘못된 요청입니다.", "red", null);
        }

        result.setRedirect(true);
        result.setPath(request.getContextPath() + "/UserMyPage/editUserInfo.my");
        return result;
    }

    private void flash(HttpSession s, String msg, String color, String devCode) {
        s.setAttribute("phoneFlashMsg", msg);
        s.setAttribute("phoneFlashColor", color);
        if (devCode != null) s.setAttribute("phoneDevCode", devCode);
    }
}