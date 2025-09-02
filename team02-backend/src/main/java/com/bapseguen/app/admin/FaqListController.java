package com.bapseguen.app.admin;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;

public class FaqListController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] FAQ 목록 요청");

        AdminDAO dao = new AdminDAO();

        // 페이징 처리
        int page = 1;
        String temp = request.getParameter("page");
        if (temp != null) { page = Integer.parseInt(temp); }

        int rowCount = 10;   // 한 페이지당 게시글 수
        int startRow = (page - 1) * rowCount + 1;
        int endRow = page * rowCount;

        String searchWord = request.getParameter("searchWord");

        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("startRow", startRow);
        pageMap.put("endRow", endRow);
        pageMap.put("searchWord", searchWord);

        List<AdminPostDTO> faqList = dao.selectFaqList(pageMap);
        int totalCount = dao.countFaqs(pageMap);

        request.setAttribute("faqList", faqList);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("page", page);
        request.setAttribute("rowCount", rowCount);

        Result result = new Result();
        result.setPath("/app/admin/faqList.jsp");
        result.setRedirect(false);
        return result;
    }
}
