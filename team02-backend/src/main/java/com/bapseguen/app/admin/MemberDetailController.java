package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.MemberDTO;

public class MemberDetailController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("==== MemberDetailController 실행 ====");

        String temp = request.getParameter("memberNumber");
        if (temp == null) {
            System.out.println("memberNumber 파라미터 없음 → 목록으로 이동");
            Result result = new Result();
            result.setPath(request.getContextPath() + "/admin/memberlistlist.ad");
            result.setRedirect(true);
            return result;
        }

        int memberNumber = Integer.parseInt(temp);

        AdminDAO adminDAO = new AdminDAO();
        MemberDTO memberDetail = adminDAO.selectMemberDetail(memberNumber);

        if (memberDetail == null) {
            System.out.println("해당 회원 없음 → 목록으로 이동");
            Result result = new Result();
            result.setPath(request.getContextPath() + "/admin/memberlistlist.ad");
            result.setRedirect(true);
            return result;
        }

        request.setAttribute("memberDetail", memberDetail);

        Result result = new Result();
        result.setPath("/app/admin/memberDetail.jsp");
        result.setRedirect(false);

        System.out.println("==== MemberDetailController 완료 ====");
        return result;
    }
}
