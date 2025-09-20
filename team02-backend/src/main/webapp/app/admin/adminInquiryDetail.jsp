<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>고객문의 상세</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/assets/css/admin/adminInquiryDetail.css">
<link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
    crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    <div class="admin_innerwrapper">
        <!-- ===== 사이드바 ===== -->
        <aside class="sidebar">
            <a href="${pageContext.request.contextPath}/admin/dashboard.ad">
                <img src="${pageContext.request.contextPath}/assets/img/admin_logo.png"
                    alt="admin_logo" class="admin_logo">
            </a>
            <ul class="sidebar_ul">
                <li class="sidebar_list"><a
                    href="${pageContext.request.contextPath}/admin/dashboard.ad">대시보드</a></li>
                <li class="sidebar_list"><a
                    href="${pageContext.request.contextPath}/admin/member/list.ad">회원관리</a></li>
                <li class="sidebar_list"><a
                    href="${pageContext.request.contextPath}/admin/notice/list.ad">게시글 관리</a></li>
                <li class="sidebar_list" id="sidebar_list_warning"><a
                    href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a></li>
                <li class="sidebar_list active" id="sidebar_list_customerservice"><a
                    href="${pageContext.request.contextPath}/admin/faq/list.ad">고객센터</a></li>
            </ul>
            <form action="${pageContext.request.contextPath}/admin/logoutOk.ad"
                method="post" class="logout_form">
                <button id="admin_logoutbtn">로그아웃</button>
            </form>
        </aside>

        <!-- ===== 메인 컨텐츠 ===== -->
        <div class="admin_inner">
            <h1 class="admin_pagetitle">고객센터 - 고객문의 상세</h1>
            <div class="admin_listwrapper">
                <div class="admin_whitebox">

                    <!-- 게시글 제목 영역 -->
                    <div class="admin_post_titlebox">
                        <div class="admin_post_tag"><p>[문의]</p></div>
                        <div class="admin_post_icon">
                            <img src="${pageContext.request.contextPath}/assets/img/새싹.png" alt="등급아이콘">
                        </div>
                        <div class="admin_post_userid"><p>${inquiry.memberName}</p></div>
                        <div class="admin_post_title"><p>${inquiry.postTitle}</p></div>
                        <div class="admin_post_date"><p>(${inquiry.postCreatedDate})</p></div>
                        <div class="admin_post_status">
                            <span id="post_status_text">
                                <c:choose>
                                    <c:when test="${inquiry.inquiryStatus == 'YET'}">접수</c:when>
                                    <c:when test="${inquiry.inquiryStatus == 'IN_PROGRESS'}">처리중</c:when>
                                    <c:when test="${inquiry.inquiryStatus == 'COMPLETE'}">완료</c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </span>
                        </div>
                    </div>

                    <!-- 게시글 내용 -->
                    <div class="admin_post_content">
                        <p><c:out value="${inquiry.inquiryContent}" /></p>
                    </div>

                    <!-- 답변 영역 -->
                    <div class="admin_reply_section">
                        <h2 class="reply_title">관리자 답변</h2>

                        <c:choose>
                            <c:when test="${not empty inquiry.answerContent and inquiry.inquiryStatus eq 'COMPLETE'}">
                                <div class="reply_completed">
                                    <p class="reply_text"><c:out value="${inquiry.answerContent}" /></p>
                                    <p class="reply_date">답변 완료일: ${inquiry.answerDate}</p>
                                </div>
                            </c:when>

                            <c:when test="${not empty inquiry.answerContent}">
                                <form class="reply_form" method="post"
                                    action="${pageContext.request.contextPath}/admin/inquiry/replyOk.ad">
                                    <input type="hidden" name="postNumber" value="${inquiry.postNumber}">
                                    <textarea name="replyContent" required>${inquiry.answerContent}</textarea>
                                    <div class="reply_actions">
                                        <button type="submit" id="reply_submit">수정</button>
                                    </div>
                                </form>
                                <p class="reply_date">최근 답변일: ${inquiry.answerDate}</p>
                            </c:when>

                            <c:otherwise>
                                <form class="reply_form" method="post"
                                    action="${pageContext.request.contextPath}/admin/inquiry/replyOk.ad">
                                    <input type="hidden" name="postNumber" value="${inquiry.postNumber}">
                                    <textarea name="replyContent" placeholder="답변을 입력하세요" required></textarea>
                                    <div class="reply_actions">
                                        <button type="submit" id="reply_submit">등록</button>
                                    </div>
                                </form>
                            </c:otherwise>
                        </c:choose>

                        <c:if test="${not empty inquiry.answerContent and inquiry.inquiryStatus ne 'COMPLETE'}">
                            <form action="${pageContext.request.contextPath}/admin/inquiry/updateStatus.ad"
                                method="post" onsubmit="return confirm('답변을 완료 처리하시겠습니까?');">
                                <input type="hidden" name="postNumber" value="${inquiry.postNumber}">
                                <input type="hidden" name="inquiryStatus" value="COMPLETE">
                                <button type="submit" class="admin_updatebtn">답변 완료</button>
                            </form>
                        </c:if>
                    </div>
                </div>

                <!-- 하단 버튼 -->
                <div class="admin_detail_delete">
                    <form action="${pageContext.request.contextPath}/admin/inquiry/list.ad" method="get">
                        <button type="submit" id="admin_listbtn">목 록</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
