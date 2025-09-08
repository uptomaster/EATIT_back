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
        Integer loginMember = (session == null) ? null : (Integer) session.getAttribute("memberNumber");
        Integer loginAdmin  = (session == null) ? null : (Integer) session.getAttribute("adminNumber");    
        request.setCharacterEncoding("utf-8");
        // JSON 응답
        Gson gson = new Gson();

        // JSON 데이터 읽기
        BufferedReader reader = request.getReader();
        JsonObject json = JsonParser.parseString(reader.lines().collect(Collectors.joining())).getAsJsonObject();

        // 필수 파라미터 확인
        if (!json.has("postNumber") || !json.has("commentContent")) {
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(gson.toJson(Map.of("status","fail","message","필수 데이터가 없습니다")));
            return null;
        }

        int postNumber = json.get("postNumber").getAsInt();
        String commentContent = json.get("commentContent").getAsString().trim();
        if (commentContent.isEmpty()) {
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(gson.toJson(Map.of("status","fail","message","내용을 입력하세요")));
            return null;
        }

        // 게시판 타입
        String postType = commentDAO.findPostType(postNumber);
        boolean isInquiry   = "INQUIRY".equalsIgnoreCase(postType);
        boolean isCommunity = "FREE".equalsIgnoreCase(postType)
                           || "PROMOTION".equalsIgnoreCase(postType)
                           || "RECIPE".equalsIgnoreCase(postType);

        if (postType == null) {
            response.getWriter().write(gson.toJson(Map.of("status","fail","message","게시글이 존재하지 않습니다")));
            return null;
        }
        if (isInquiry) {
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(gson.toJson(Map.of("status","fail","message","문의 답변은 관리자페이지에서만 등록됩니다")));
            return null;
        }
        
        // 커뮤니티만 작성 허용(회원만, 관리자는 불가)
        if (!isCommunity || loginMember == null || loginAdmin != null) {
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(gson.toJson(Map.of("status","fail","message","권한이 없습니다")));
            return null;
        }

        // === DTO 설정(한 번에) ===
        commentDTO.setPostNumber(postNumber);
        commentDTO.setCommentContent(commentContent);
        commentDTO.setMemberNumber(loginMember);

        // DB 저장
        int affected = isInquiry? commentDAO.insertInquiry(commentDTO) : commentDAO.insertCommunity(commentDTO);

        // JSON 응답
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(
        		gson.toJson(affected > 0 ? 
        		Map.of("status","success","commentNumber", commentDTO.getCommentNumber()) : Map.of("status","fail","message","댓글 저장 실패"))
        );
        return null;
    }
}