package com.bapseguen.app.comment;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.comment.dao.CommentDAO;
import com.bapseguen.app.dto.CommentDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CommentWriteOkController implements Execute {
    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        CommentDTO commentDTO = new CommentDTO();
        CommentDAO commentDAO = new CommentDAO();
        System.out.println("세션에 저장된 멤버 = " + session.getAttribute("memberNumber"));
        
        request.setCharacterEncoding("utf-8");
        // JSON 응답
        Gson gson = new Gson();

        // JSON 데이터 읽기
        BufferedReader reader = request.getReader();
        JsonObject json = JsonParser.parseString(reader.lines().collect(Collectors.joining())).getAsJsonObject();

        // 필수 파라미터 확인
        if (!json.has("postNumber") || !json.has("memberNumber") || !json.has("commentContent")) {
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(gson.toJson(Map.of("status","fail","message","필수 데이터가 없습니다")));
            return null;
        }

        // DTO 설정 
        commentDTO.setPostNumber(json.get("postNumber").getAsInt());
        commentDTO.setMemberNumber(json.get("memberNumber").getAsInt());
        commentDTO.setCommentContent(json.get("commentContent").getAsString());
        System.out.println("commentDTO 확인 :" + commentDTO);
        //DB 저장
        commentDAO.insert(commentDTO);
        System.out.println("댓글 작성 완료: " + commentDTO);
        //JSON 응답
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(gson.toJson(Map.of("status","success","commentNumber", commentDTO.getCommentNumber())));
        return null;
    }
}