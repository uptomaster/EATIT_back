<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>관리자 대시보드</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/assets/css/admin/dashboard.css">
<link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
    crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    <div class="admin_innerwrapper">
        <!-- 좌측 사이드바 -->
        <aside class="sidebar">
            <a href="${pageContext.request.contextPath}/admin/dashboard.ad">
                <img src="${pageContext.request.contextPath}/assets/img/admin_logo.png"
                     alt="admin_logo" class="admin_logo">
            </a>
            <ul class="sidebar_ul">
                <li class="sidebar_list active" id="sidebar_list_dashboard">
                    <a href="${pageContext.request.contextPath}/admin/dashboard.ad">대시보드</a>
                </li>
                <li class="sidebar_list">
                    <a href="${pageContext.request.contextPath}/admin/member/list.ad">회원관리</a>
                </li>
                <li class="sidebar_list">
                    <a href="${pageContext.request.contextPath}/admin/notice/list.ad">게시글 관리</a>
                </li>
                <li class="sidebar_list">
                    <a href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a>
                </li>
                <li class="sidebar_list">
                    <a href="${pageContext.request.contextPath}/admin/faq/list.ad">고객센터</a>
                </li>
            </ul>
            <form action="${pageContext.request.contextPath}/admin/logoutOk.ad" method="post">
                <button id="admin_logoutbtn">로그아웃</button>
            </form>
        </aside>

        <!-- 메인 컨텐츠 -->
        <div class="admin_inner">
            <h1>대시보드</h1>

            <!-- 상단 3칸 -->
            <div class="dashboard_topWrapper">
                <div class="dashboard_topbox">
                    <i class="fas fa-users dashboard_icon"></i>
                    <div class="dashboard_topbox_key">총 회원 수</div>
                    <div class="dashboard_topbox_value">${totalMembers}</div>
                </div>
                <div class="dashboard_topbox">
                    <i class="fas fa-bullhorn dashboard_icon"></i>
                    <div class="dashboard_topbox_key">공지사항</div>
                    <div class="dashboard_topbox_value">${totalNotices}</div>
                </div>
                <div class="dashboard_topbox">
                    <i class="fas fa-question-circle dashboard_icon"></i>
                    <div class="dashboard_topbox_key">FAQ</div>
                    <div class="dashboard_topbox_value">${totalFaqs}</div>
                </div>
            </div>

            <!-- 하단 2칸 -->
            <div class="dashboard_bottomWrapper">
                <div class="dashboard_bottombox">
                    <div class="dashboard_bottombox_title">최근 문의글</div>
                    <ul>
                        <c:forEach var="inq" items="${recentInquiries}">
                            <li class="dashboard_bottombox_usetop3">
                                <span class="inq_title">${inq.postTitle}</span>
                                <span class="inq_status">(${inq.inquiryStatus})</span>
                            </li>
                        </c:forEach>
                        <c:if test="${empty recentInquiries}">
                            <li>등록된 문의글이 없습니다.</li>
                        </c:if>
                    </ul>
                </div>
                <div class="dashboard_bottombox">
                    <div class="dashboard_bottombox_title">최근 신고</div>
                    <ul>
                        <c:forEach var="rep" items="${recentReports}">
                            <li class="dashboard_bottombox_zimtop3">
                                <span class="rep_reason">[${rep.postReportReason}]</span>
                                <span class="rep_date">${rep.postReportDate}</span>
                            </li>
                        </c:forEach>
                        <c:if test="${empty recentReports}">
                            <li>등록된 신고가 없습니다.</li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
