package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;

public class BoardFreeDeleteOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("==== [ADMIN] 자유게시판 삭제 컨트롤러 실행 ====");

        Result result = new Result();
        String temp = request.getParameter("postNumber");

        if (temp != null && !temp.isBlank()) {
            int postNumber = Integer.parseInt(temp);

            AdminDAO adminDAO = new AdminDAO();
            adminDAO.deleteBoardFree(postNumber);

            request.setAttribute("alertMessage", "게시글이 삭제되었습니다.");
        }

        result.setPath(request.getContextPath() + "/admin/board/freeList.ad");
        result.setRedirect(true);

        System.out.println("==== [ADMIN] 자유게시판 삭제 컨트롤러 완료 ====");
        return result;
    }
}
