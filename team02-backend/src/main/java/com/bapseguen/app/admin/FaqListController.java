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
        Result result = new Result();

        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        int rowCount = 10;
        int startRow = (page - 1) * rowCount + 1;
        int endRow = page * rowCount;

        Map<String, Object> params = new HashMap<>();
        params.put("startRow", startRow);
        params.put("endRow", endRow);
        params.put("searchWord", request.getParameter("searchWord"));

        List<AdminPostDTO> faqList = dao.selectFaqList(params);
        int totalCount = dao.countFaqs(params);

        request.setAttribute("faqList", faqList);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("page", page);
        request.setAttribute("rowCount", rowCount);

        result.setPath("/app/admin/adminFaqList.jsp");
        result.setRedirect(false);
        return result;
    }
}
