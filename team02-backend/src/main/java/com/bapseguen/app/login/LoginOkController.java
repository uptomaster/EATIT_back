package com.bapseguen.app.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.MemberDTO;
import com.bapseguen.app.dto.MemberSuspendDTO;
import com.bapseguen.app.login.dao.LoginDAO;

public class LoginOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Result result = new Result();
        LoginDAO loginDAO = new LoginDAO();
        HttpSession session = request.getSession();

        String ctx = request.getContextPath();
        String memberId = request.getParameter("login_input_id");
        String memberPassword = request.getParameter("login_input_pw");

        // 아이디 존재 여부
        if (memberId == null || memberId.isEmpty() || !loginDAO.existsMemberId(memberId)) {
            result.setRedirect(true);
            result.setPath(ctx + "/login/login.lo?login=noid"); // 등록된 아이디가 없습니다.
            return result;
        }

        // 아이디+비밀번호 검증
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(memberId);
        memberDTO.setMemberPassword(memberPassword);

        int memberNumber = loginDAO.login(memberDTO); // 일치시 번호, 불일치시 -1
        if (memberNumber == -1) {
            result.setRedirect(true);
            result.setPath(ctx + "/login/login.lo?login=wrongpw"); // 비밀번호 불일치
            return result;
        }
        // 블랙이면
        if (loginDAO.isBlacklisted(memberNumber)) {
            result.setRedirect(true);
            result.setPath(ctx + "/login/login.lo?login=blacklisted");
            return result;
        }
        String memberType = loginDAO.getMemberType(memberNumber);
        
        MemberSuspendDTO active = loginDAO.findActiveSuspend(memberNumber);
        if (active != null) {
            String msg = "현재 정지 상태이므로 로그인할 수 없습니다.";
            if (active.getSuspendStartDate() != null && active.getSuspendEndDate() != null) {
                msg = "정지기간(" + active.getSuspendStartDate() + " ~ " + active.getSuspendEndDate() + ") 동안 로그인할 수 없습니다.";
            }
            session.setAttribute("flashLoginMsg", msg);
            result.setRedirect(true);
            result.setPath(ctx + "/login/login.lo");
            return result;
        } 

        // 세션 설정 및 메인으로
        session.setAttribute("memberNumber", memberNumber);
        session.setAttribute("memberType", memberType);
        session.setAttribute("memberId", memberId);

        result.setRedirect(true);
        System.out.println("회원유형 : " + memberType);
        result.setPath(ctx + "/main.ma");
        return result;
    }
}