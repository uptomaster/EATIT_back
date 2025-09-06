package com.bapseguen.app.comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

            //세션/권한 확인
            HttpSession session = request.getSession();
            Integer loginMember = (session == null) ? null : (Integer) session.getAttribute("memberNumber");
            Integer loginAdmin  = (session == null) ? null : (Integer) session.getAttribute("adminNumber");
            
            // 댓글이 속한 게시판 타입
            String postType = commentDAO.findPostTypeByComment(commentNumber); 
            if (postType == null) {
                response.setContentType("application/json; charset=utf-8");
                response.getWriter().print(gson.toJson(java.util.Map.of("status","fail","message","대상이 없습니다")));
                return null;
            }

            boolean isInquiry   = "INQUIRY".equalsIgnoreCase(postType);
            boolean isCommunity = "FREE".equalsIgnoreCase(postType)
                               || "PROMOTION".equalsIgnoreCase(postType)
                               || "RECIPE".equalsIgnoreCase(postType);

            boolean authorized = false;
            if (isInquiry) {
                // 문의: 관리자만 삭제
                authorized = (loginAdmin != null);
            } else if (isCommunity) {
                // 커뮤니티: 작성자 본인 or 관리자
                if (loginAdmin != null) {
                    authorized = true;
                } else if (loginMember != null) {
                    Map<String,Integer> author = commentDAO.findAuthor(commentNumber);
                    Integer ownerMember = (author == null) ? null : author.get("memberNumber");
                    authorized = (ownerMember != null && ownerMember.equals(loginMember));
                }
            }

            if (!authorized) {
                response.setContentType("application/json; charset=utf-8");
                response.getWriter().print(gson.toJson(java.util.Map.of("status","fail","message","삭제 권한이 없습니다")));
                return null;
            }

            // 삭제 실행
            int affected = commentDAO.delete(commentNumber);

            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print(gson.toJson(
                affected > 0
                    ? java.util.Map.of("status", "success")
                    : java.util.Map.of("status", "fail", "message", "삭제 실패")
            ));
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().print(gson.toJson(java.util.Map.of("status", "fail")));
        }
        return null;
    }
}
