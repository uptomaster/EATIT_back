package com.bapseguen.app.admin;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;

public class BoardFreeDetailController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("==== [ADMIN] 자유게시판 상세 컨트롤러 실행 ====");

        Result result = new Result();
        String temp = request.getParameter("postNumber");

        if (temp == null || temp.isBlank()) {
            result.setPath(request.getContextPath() + "/admin/board/freeList.ad");
            result.setRedirect(true);
            return result;
        }

        int postNumber = Integer.parseInt(temp);

        AdminDAO adminDAO = new AdminDAO();
        Map<String, Object> freeDetail = adminDAO.selectBoardFreeDetail(postNumber);

        request.setAttribute("freeDetail", freeDetail);

        result.setPath("/app/admin/boardFreeDetail.jsp");
        result.setRedirect(false);

        System.out.println("==== [ADMIN] 자유게시판 상세 컨트롤러 완료 ====");
        return result;
    }
}
