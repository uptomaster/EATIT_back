package com.bapseguen.app.admin;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;

public class BoardFreeListController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("==== [ADMIN] 자유게시판 목록 컨트롤러 실행 ====");

        Result result = new Result();
        AdminDAO adminDAO = new AdminDAO();

        // 페이지네이션
        int page = 1;
        String temp = request.getParameter("page");
        if (temp != null && !temp.isBlank()) {
            page = Integer.parseInt(temp);
        }

        int rowCount = 10;
        int pageCount = 5;
        int startRow = (page - 1) * rowCount + 1;
        int endRow = page * rowCount;

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("startRow", startRow);
        paramMap.put("endRow", endRow);

        List<Map<String, Object>> freeList = adminDAO.selectBoardFreeList(paramMap);

        request.setAttribute("freeList", freeList);
        request.setAttribute("page", page);

        result.setPath("/app/admin/boardFreeList.jsp");
        result.setRedirect(false);

        System.out.println("==== [ADMIN] 자유게시판 목록 컨트롤러 완료 ====");
        return result;
    }
}
