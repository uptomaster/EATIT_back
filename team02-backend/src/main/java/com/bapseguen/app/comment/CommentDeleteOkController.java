package com.bapseguen.app.comment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.comment.dao.CommentDAO;
import com.google.gson.Gson;

public class CommentDeleteOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

    	CommentDAO commentDAO = new CommentDAO();
        Gson gson = new Gson();
       
        try {
			int commentNumber = Integer.parseInt(request.getParameter("commentNumber"));
			commentDAO.delete(commentNumber);
			
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(gson.toJson(java.util.Map.of("status", "success")));
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(gson.toJson(java.util.Map.of("status", "fail")));
		}
        return null;
    }
}