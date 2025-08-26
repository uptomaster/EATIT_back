package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.MemberDetailDTO;

public class MemberDetailController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("==== MemberDetailController 실행 ====");

        Result result = new Result();

        // 파라미터(memberNumber) 가져오기
        String temp = request.getParameter("memberNumber");
        if (temp == null || temp.isBlank()) {
            System.out.println("⚠ memberNumber 파라미터 없음 → 목록으로 이동");
            result.setPath(request.getContextPath() + "/admin/member/list.ad");
            result.setRedirect(true);
            return result;
        }

        int memberNumber = -1;
        try {
            memberNumber = Integer.parseInt(temp);
        } catch (NumberFormatException e) {
            System.out.println("⚠ memberNumber 파싱 실패: " + temp);
            result.setPath(request.getContextPath() + "/admin/member/list.ad");
            result.setRedirect(true);
            return result;
        }

        // DAO 통해 상세 조회
        AdminDAO adminDAO = new AdminDAO();
        MemberDetailDTO memberDetail = adminDAO.selectMemberDetail(memberNumber);

        // 조회 결과 null 체크
        if (memberDetail == null) {
            System.out.println("⚠ 회원번호 " + memberNumber + " 상세정보 없음 → 목록으로 이동");
            result.setPath(request.getContextPath() + "/admin/member/list.ad");
            result.setRedirect(true);
            return result;
        }

        // JSP로 전달
        request.setAttribute("memberDetail", memberDetail);

        // 이동 경로 지정
        result.setPath("/app/admin/memberDetail.jsp");
        result.setRedirect(false);

        System.out.println("==== MemberDetailController 완료 (memberNumber=" + memberNumber + ") ====");
        return result;
    }
}
