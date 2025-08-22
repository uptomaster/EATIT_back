package com.bapseguen.app.join;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.join.dao.JoinDAO;

public class CheckIdOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Result result = new Result();
        JoinDAO joinDAO = new JoinDAO();

        String memberId = request.getParameter("memberId");
		boolean isAvailable = joinDAO.checkId(memberId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
			out.print("{\"available\" : " + isAvailable + "}");
			out.flush();
		}

        result.setRedirect(false);
        result.setPath(null);
        return result;
    }
}
