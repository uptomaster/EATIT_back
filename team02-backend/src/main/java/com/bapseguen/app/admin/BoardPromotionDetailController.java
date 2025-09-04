package com.bapseguen.app.admin;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;

public class BoardPromotionDetailController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int postNumber = Integer.parseInt(request.getParameter("postNumber"));
        AdminDAO adminDAO = new AdminDAO();
        Map<String, Object> promotionDetail = adminDAO.selectBoardPromotionDetail(postNumber);

        request.setAttribute("promotionDetail", promotionDetail);

        Result result = new Result();
        result.setRedirect(false);
        result.setPath("/app/admin/boardPromotionDetail.jsp"); // 상세 JSP 경로
        return result;
    }
}
