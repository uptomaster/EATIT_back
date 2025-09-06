<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>고객문의 상세</title>
  <script defer src="${pageContext.request.contextPath}/assets/js/admin/adminInquiryDetail.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/adminInquiryDetail.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
        integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
  <script>
    function confirmDelete() {
      return confirm("정말 삭제하시겠습니까?");
    }
  </script>
</head>
<body>
  <div class="admin_innerwrapper">
    <!-- ===== 사이드바 ===== -->
    <aside class="sidebar">
      <a href="${pageContext.request.contextPath}/admin/dashboard.ad">
        <img src="${pageContext.request.contextPath}/assets/img/admin_logo.png" alt="admin_logo" class="admin_logo">
      </a>
      <ul class="sidebar_ul">
        <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/dashboard.ad">대시보드</a></li>
        <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/member/list.ad">회원관리</a></li>
        <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/notice/list.ad">게시글 관리</a></li>
        <li class="sidebar_list" id="sidebar_list_warning"><a href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a></li>
        <li class="sidebar_list active" id="sidebar_list_customerservice"><a href="${pageContext.request.contextPath}/admin/faq/list.ad">고객센터</a></li>
      </ul>
      <form action="${pageContext.request.contextPath}/admin/logoutOk.ad" method="post" class="logout_form">
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
            <div class="admin_post_userid"><p>${inquiryDetail.memberName}</p></div>
            <div class="admin_post_title"><p>${inquiryDetail.postTitle}</p></div>
            <div class="admin_post_date"><p>(${inquiryDetail.postCreatedDate})</p></div>
            <div class="admin_post_status">
              <span id="post_status_text">
                <c:choose>
                  <c:when test="${inquiryDetail.inquiryStatus == 'YET'}">접수</c:when>
                  <c:when test="${inquiryDetail.inquiryStatus == 'IN_PROGRESS'}">처리중</c:when>
                  <c:when test="${inquiryDetail.inquiryStatus == 'DONE'}">완료</c:when>
                  <c:otherwise>-</c:otherwise>
                </c:choose>
              </span>
            </div>
          </div>

          <!-- 게시글 내용 -->
          <div class="admin_post_content">
            <p><c:out value="${inquiryDetail.inquiryContent}" /></p>
          </div>

          <!-- 답변 영역 -->
          <div class="admin_reply_section">
            <h2 class="reply_title">관리자 답변</h2>
            <ul id="reply_list" class="reply_list">
              <c:forEach var="reply" items="${inquiryDetail.replies}">
                <li><c:out value="${reply}" /></li>
              </c:forEach>
            </ul>
            <!-- 답변 작성 폼 -->
            <form id="reply_form" class="reply_form" method="post" action="${pageContext.request.contextPath}/admin/inquiry/replyOk.ad">
              <input type="hidden" name="postNumber" value="${inquiryDetail.postNumber}">
              <textarea id="reply_text" name="replyContent" placeholder="답변을 입력하세요" required></textarea>
              <div class="reply_actions">
                <button type="submit" id="reply_submit">등록</button>
              </div>
            </form>
          </div>
        </div>

        <!-- 하단 버튼 영역 -->
        <div class="admin_detail_delete">
          <!-- 삭제 버튼 -->
          <form action="${pageContext.request.contextPath}/admin/inquiry/deleteOk.ad" method="post" onsubmit="return confirmDelete();">
            <input type="hidden" name="postNumber" value="${inquiryDetail.postNumber}">
            <button type="submit" id="admin_deletebtn">삭 제</button>
          </form>

          <!-- 목록으로 버튼 -->
          <form action="${pageContext.request.contextPath}/admin/inquiry/list.ad" method="get">
            <button type="submit" id="admin_listbtn">목 록</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
