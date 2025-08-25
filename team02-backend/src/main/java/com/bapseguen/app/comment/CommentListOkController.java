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
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class CommentListOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // int 단일 파라미터
    	int postNumber = Integer.valueOf(request.getParameter("postNumber"));
    	CommentDAO commentDAO = new CommentDAO();
    	Gson gson = new Gson();
        JsonArray comments = new JsonArray();

        commentDAO.selectAll(postNumber).stream().map(gson::toJson).map(JsonParser::parseString).forEach(comments::add);
        
        System.out.println(comments.toString());
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(comments.toString());
        out.close();
        return null;
    }
}