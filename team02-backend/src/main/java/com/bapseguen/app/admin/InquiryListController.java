package com.bapseguen.app.admin;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminPostDTO;

public class InquiryListController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 문의글 목록 요청");

        AdminDAO dao = new AdminDAO();

        int page = 1;
        String temp = request.getParameter("page");
        if (temp != null) { page = Integer.parseInt(temp); }

        int rowCount = 10;
        int startRow = (page - 1) * rowCount + 1;
        int endRow = page * rowCount;

        String searchWord = request.getParameter("searchWord");

        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("startRow", startRow);
        pageMap.put("endRow", endRow);
        pageMap.put("searchWord", searchWord);

        List<AdminPostDTO> inquiryList = dao.selectInquiryList(pageMap);
        int totalCount = dao.countInquiries(pageMap);

        request.setAttribute("inquiryList", inquiryList);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("page", page);
        request.setAttribute("rowCount", rowCount);

        Result result = new Result();
        result.setPath("/app/admin/inquiryList.jsp");
        result.setRedirect(false);
        return result;
    }
}
