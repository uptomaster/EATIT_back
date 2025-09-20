package com.bapseguen.app.join;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.join.dao.JoinDAO;

public class CheckPhoneController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String phone = request.getParameter("phone");
        boolean available = !new JoinDAO().existsPhone(phone);

        response.setContentType("application/json; charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print("{\"available\":" + available + "}");
        }
        // 페이지 이동 없음
        return null;
    }
}
