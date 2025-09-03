package com.bapseguen.app.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.MemberSuspendDTO;

public class SuspendListController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 정지 회원 목록 요청");

        AdminDAO dao = new AdminDAO();
        List<MemberSuspendDTO> suspendList = dao.selectSuspendList();

        request.setAttribute("suspendList", suspendList);

        Result result = new Result();
        result.setPath("/app/admin/suspendList.jsp");
        result.setRedirect(false);
        return result;
    }
}
