package com.bapseguen.app.userMyPage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.join.SmsService;

public class UserMypageSMSController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("====UserMypageSMSController 실행====");
        String mode = request.getParameter("mode");
        System.out.println("mode = " + mode);

        HttpSession session = request.getSession();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain"); // 중요: JSP 전체가 아니라 순수 텍스트 반환

        if ("send".equals(mode)) {
            String phoneNumber = request.getParameter("newPhone");
            SmsService smsService = new SmsService();
            try {
                String verificationCode = smsService.sendVerificationSms(phoneNumber);
                session.setAttribute("verificationCode", verificationCode);
                response.getWriter().write("인증번호가 발송되었습니다."); // 화면 표시용 텍스트
            } catch (Exception e) {
                response.getWriter().write("SMS 발송 실패: " + e.getMessage());
            }
        } else if ("check".equals(mode)) {
            String userCode = request.getParameter("phoneCode");
            String sessionCode = (String) session.getAttribute("verificationCode");

            if (sessionCode != null && sessionCode.equals(userCode)) {
                response.getWriter().write("인증 성공");
            } else {
                response.getWriter().write("인증 실패");
            }
        }

        return null;
    }
}
