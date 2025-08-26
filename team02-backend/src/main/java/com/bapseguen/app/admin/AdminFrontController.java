package com.bapseguen.app.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.bapseguen.app.Result;

public class AdminFrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminFrontController() { super(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String target = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println("============================================");
        System.out.println("[AdminFrontController] 현재 요청 경로 : " + target);
        System.out.println("============================================");

        Result result = null;

        switch (target) {

        /* =================== 로그인 =====================*/
        case "/admin/login.ad":
            System.out.println("→ [ADMIN] 로그인 페이지 이동");
            result = new AdminLoginController().execute(request, response);
            break;

        case "/admin/loginOk.ad":
            System.out.println("→ [ADMIN] 로그인 처리");
            result = new AdminLoginOkController().execute(request, response);
            break;

        case "/admin/logoutOk.ad":
            System.out.println("→ [ADMIN] 로그아웃 처리");
            result = new AdminLogoutOkController().execute(request, response);
            break;


        /* =================== 대시보드 =====================*/
        case "/admin/dashboard.ad":
            System.out.println("→ [ADMIN] 대시보드 페이지 이동");
            result = new AdminDashboardController().execute(request, response);
            break;


        /* =================== 배너 =====================*/
        case "/admin/banner/list.ad":
            System.out.println("→ [ADMIN] 배너 목록 요청");
            result = new BannerListController().execute(request, response);
            break;

        case "/admin/banner/detail.ad":
            System.out.println("→ [ADMIN] 배너 상세 요청");
            result = new BannerDetailController().execute(request, response);
            break;

        case "/admin/banner/write.ad":
            System.out.println("→ [ADMIN] 배너 작성 페이지 이동");
            result = new BannerWriteController().execute(request, response);
            break;

        case "/admin/banner/writeOk.ad":
            System.out.println("→ [ADMIN] 배너 작성 처리");
            result = new BannerWriteOkController().execute(request, response);
            break;

        case "/admin/banner/updateOk.ad":
            System.out.println("→ [ADMIN] 배너 수정 처리");
            result = new BannerUpdateOkController().execute(request, response);
            break;

        case "/admin/banner/deleteOk.ad":
            System.out.println("→ [ADMIN] 배너 삭제 처리");
            result = new BannerDeleteOkController().execute(request, response);
            break;


        /* =================== 고객센터 문의 =====================*/
        case "/admin/inquiry/list.ad":
            System.out.println("→ [ADMIN] 문의글 목록 요청");
            result = new InquiryListController().execute(request, response);
            break;

        case "/admin/inquiry/detail.ad":
            System.out.println("→ [ADMIN] 문의글 상세 요청");
            result = new InquiryDetailController().execute(request, response);
            break;

        case "/admin/inquiry/commentOk.ad":
            System.out.println("→ [ADMIN] 문의글 댓글 등록 요청");
            result = new InquiryCommentOkController().execute(request, response);
            break;


        /* =================== 공지 =====================*/
        case "/admin/notice/list.ad":
            System.out.println("→ [ADMIN] 공지 목록 요청");
            result = new NoticeListController().execute(request, response);
            break;

        case "/admin/notice/detail.ad":
            System.out.println("→ [ADMIN] 공지 상세 요청");
            result = new NoticeDetailController().execute(request, response);
            break;

        case "/admin/notice/write.ad":
            System.out.println("→ [ADMIN] 공지 작성 페이지 이동");
            result = new NoticeWriteController().execute(request, response);
            break;

        case "/admin/notice/writeOk.ad":
            System.out.println("→ [ADMIN] 공지 작성 처리 (POST + NOTICE)");
            result = new NoticeWriteOkController().execute(request, response);
            break;

        case "/admin/notice/updateOk.ad":
            System.out.println("→ [ADMIN] 공지 수정 처리");
            result = new NoticeUpdateOkController().execute(request, response);
            break;

        case "/admin/notice/deleteOk.ad":
            System.out.println("→ [ADMIN] 공지 삭제 처리");
            result = new NoticeDeleteOkController().execute(request, response);
            break;


        /* =================== FAQ =====================*/
        case "/admin/faq/list.ad":
            System.out.println("→ [ADMIN] FAQ 목록 요청");
            result = new FaqListController().execute(request, response);
            break;

        case "/admin/faq/detail.ad":
            System.out.println("→ [ADMIN] FAQ 상세 요청");
            result = new FaqDetailController().execute(request, response);
            break;

        case "/admin/faq/write.ad":
            System.out.println("→ [ADMIN] FAQ 작성 페이지 이동");
            result = new FaqWriteController().execute(request, response);
            break;

        case "/admin/faq/writeOk.ad":
            System.out.println("→ [ADMIN] FAQ 작성 처리 (POST + FAQ)");
            result = new FaqWriteOkController().execute(request, response);
            break;

        case "/admin/faq/updateOk.ad":
            System.out.println("→ [ADMIN] FAQ 수정 처리");
            result = new FaqUpdateOkController().execute(request, response);
            break;

        case "/admin/faq/deleteOk.ad":
            System.out.println("→ [ADMIN] FAQ 삭제 처리");
            result = new FaqDeleteOkController().execute(request, response);
            break;


        /* =================== 신고 / 정지 / 블랙리스트 =====================*/
        case "/admin/report/list.ad":
            System.out.println("→ [ADMIN] 신고 목록 요청");
            result = new ReportListController().execute(request, response);
            break;

        case "/admin/suspend/list.ad":
            System.out.println("→ [ADMIN] 정지 회원 목록 요청");
            result = new SuspendListController().execute(request, response);
            break;

        case "/admin/blacklist/list.ad":
            System.out.println("→ [ADMIN] 블랙리스트 목록 요청");
            result = new BlacklistListController().execute(request, response);
            break;


        /* =================== 회원 =====================*/
        case "/admin/member/list.ad":
            System.out.println("→ [ADMIN] 회원 목록 요청");
            result = new MemberListController().execute(request, response);
            break;

        case "/admin/member/detail.ad":
            System.out.println("→ [ADMIN] 회원 상세 요청");
            result = new MemberDetailController().execute(request, response);
            break;

        case "/admin/member/warningOk.ad":
            System.out.println("[ADMIN] 회원 경고 처리 요청");
            result = new WarningOkController().execute(request, response);
            break;
            

        /* =================== 기본/예외 =====================*/
        default:
            System.out.println(" [ADMIN] 잘못된 요청 => 대시보드로 리다이렉트");
            result = new Result();
            result.setPath(request.getContextPath() + "/admin/dashboard.ad");
            result.setRedirect(true);
            break;
        }

        // 공통 이동 처리
        if (result != null) {
            if (result.isRedirect()) {
                System.out.println(" 리다이렉트 이동 → " + result.getPath());
                response.sendRedirect(result.getPath());
            } else {
                System.out.println(" 포워드 이동 → " + result.getPath());
                request.getRequestDispatcher(result.getPath()).forward(request, response);
            }
        }
        System.out.println("============================================\n");
    }
}
