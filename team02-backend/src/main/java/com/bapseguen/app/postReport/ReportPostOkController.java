package com.bapseguen.app.postReport;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.postReport.dao.PostReportDAO;

public class ReportPostOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json; charset=UTF-8");
        HttpSession session = request.getSession(false);

        // 1) 로그인 체크
        Integer memberNumber = (session == null) ? null : (Integer) session.getAttribute("memberNumber");
        if (memberNumber == null) {
            response.getWriter().write("{\"ok\":false,\"reason\":\"LOGIN_REQUIRED\"}");
            return null;
        }

        try {
            // 2) 파라미터
            int postNumber = Integer.parseInt(request.getParameter("postNumber"));
            String reason = request.getParameter("reason"); // 'ADV','BADWORDS','PORN','PERSONAL','ETC'

            // (선택) 한글 라디오값 -> 코드 매핑을 서버에서 하고 싶다면 여기에 매핑 로직 추가
            // reason = mapKoToCode(reason);

            // 3) 신고 처리 (트랜잭션 포함)
            PostReportDAO dao = new PostReportDAO();
            boolean ok = dao.reportOnce(postNumber, memberNumber, reason);

            if (ok) {
                // 동기화된 총합을 다시 읽고 싶으면 DAO에 getPostReportCount 추가해서 내려줘도 됨
                response.getWriter().write("{\"ok\":true}");
            } else {
                response.getWriter().write("{\"ok\":false,\"reason\":\"ALREADY_REPORTED\"}");
            }
        } catch (Exception e) {
            response.getWriter().write("{\"ok\":false,\"reason\":\"ERROR\"}");
        }
        return null;
    }
}
