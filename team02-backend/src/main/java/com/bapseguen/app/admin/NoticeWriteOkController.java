package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.admin.dao.AdminDAO;
import com.bapseguen.app.dto.view.AdminNoticeDTO;

public class NoticeWriteOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("[ADMIN] 공지/이벤트 작성 처리 컨트롤러 시작");

        Result result = new Result();

        // ===== 관리자 로그인/권한 체크 =====
        HttpSession session = request.getSession(false);
        if (session == null || !"ADMIN".equals(String.valueOf(session.getAttribute("memberType")))) {
            System.out.println("[ADMIN] 비관리자 접근 차단 → 로그인 페이지 이동");
            result.setPath(request.getContextPath() + "/admin/login.ad");
            result.setRedirect(true);
            return result;
        }

        // ===== 파라미터 수집 =====
        String postTitle = request.getParameter("postTitle");
        String noticeContent = request.getParameter("noticeContent");
        String noticeCategory = request.getParameter("noticeCategory"); // 공지 or 이벤트

        Integer adminNumber = (Integer) session.getAttribute("memberNumber");

        System.out.println("제목: " + postTitle);
        System.out.println("카테고리: " + noticeCategory);
        System.out.println("작성자 번호: " + adminNumber);

        // ===== DTO 생성 =====
        AdminNoticeDTO dto = new AdminNoticeDTO();
        dto.setPostTitle(postTitle);
        dto.setNoticeContent(noticeContent);
        dto.setNoticeCategory(noticeCategory);
        dto.setMemberNumber(adminNumber);

        // ===== DAO 처리 =====
        AdminDAO dao = new AdminDAO();
        dao.insertNoticePost(dto);  // TBL_POST insert
        dao.insertNotice(dto);      // TBL_NOTICE insert

        // ===== 결과 설정 =====
        result.setPath(request.getContextPath() + "/admin/notice/list.ad");
        result.setRedirect(true);

        System.out.println("[ADMIN] 공지/이벤트 작성 완료 → 목록으로 이동");

        return result;
    }
}
