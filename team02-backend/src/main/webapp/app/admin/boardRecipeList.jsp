<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>레시피 게시판 관리</title>
  <script defer src="${pageContext.request.contextPath}/assets/js/admin/boardRecipeList.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/boardRecipeList.css">
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
        integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
  <div class="admin_innerwrapper">
    <!-- 좌측 사이드바 -->
    <aside class="sidebar">
      <a href="${pageContext.request.contextPath}/admin/dashboard.ad">
        <img src="${pageContext.request.contextPath}/assets/img/admin_logo.png" alt="admin_logo" class="admin_logo">
      </a>
      <ul class="sidebar_ul">
        <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/dashboard.ad">대시보드</a></li>
        <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/member/list.ad">회원관리</a></li>
        <li class="sidebar_list active"><a href="${pageContext.request.contextPath}/admin/notice/list.ad">게시글 관리</a></li>
        <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a></li>
        <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/inquiry/list.ad">고객센터</a></li>
      </ul>
      <form action="${pageContext.request.contextPath}/admin/logoutOk.ad" method="post">
        <button id="admin_logoutbtn">로그아웃</button>
      </form>
    </aside>

    <!-- 메인 컨텐츠 -->
    <main class="admin_inner">
      <h1 class="admin_pagetitle">게시글 관리 - 레시피</h1>
      <div class="admin_listwrapper">
        <!-- 탭 메뉴 -->
        <div class="admin_list_title">
          <ul class="admin_list">
            <li class="admin_list_menu"><a href="${pageContext.request.contextPath}/admin/notice/list.ad">공지사항</a></li>
            <li class="admin_list_menu"><a href="${pageContext.request.contextPath}/admin/boardFree/list.ad">자유게시판</a></li>
            <li class="admin_list_menu"><a href="${pageContext.request.contextPath}/admin/boardPromotion/list.ad">홍보게시판</a></li>
            <li class="admin_list_menu active"><a href="${pageContext.request.contextPath}/admin/boardRecipe/list.ad">레시피</a></li>
          </ul>
        </div>

        <!-- 게시글 목록 -->
        <div class="admin_list_whitebox">
          <div class="admin_list_namebox">
            <ul class="admin_list_name">
              <li class="admin_list_row">글번호</li>
              <li class="admin_list_row">제목</li>
              <li class="admin_list_row">작성자</li>
              <li class="admin_list_row">등록일</li>
              <li class="admin_list_row">조회</li>
              <li class="admin_list_row">추천</li>
              <li class="admin_list_row">관리</li>
            </ul>
          </div>
          <ul class="admin_list_valuebox">
            <c:choose>
              <c:when test="${not empty recipeList}">
                <c:forEach var="post" items="${recipeList}">
                  <li class="admin_list_value">
                    <p class="admin_list_row">${post.postNumber}</p>
                    <p class="admin_list_row">
                      <a href="${pageContext.request.contextPath}/admin/boardRecipe/detail.ad?postNumber=${post.postNumber}" class="admin_list_userid_link">
                        <c:out value="${post.postTitle}" />
                      </a>
                    </p>
                    <p class="admin_list_row">${post.memberId}</p>
                    <p class="admin_list_row"><fmt:formatDate value="${post.postDate}" pattern="yy-MM-dd" /></p>
                    <p class="admin_list_row">${post.postViewCount}</p>
                    <p class="admin_list_row">${post.postLikeCount}</p>
                    <p class="admin_list_row">
                      <form action="${pageContext.request.contextPath}/admin/boardRecipe/deleteOk.ad" method="post">
                        <input type="hidden" name="postNumber" value="${post.postNumber}">
                        <button type="submit" class="delete_btn">삭제</button>
                      </form>
                    </p>
                  </li>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <li class="admin_list_value">등록된 글이 없습니다.</li>
              </c:otherwise>
            </c:choose>
          </ul>
        </div>
      </div>
    </main>
  </div>
</body>
</html>
