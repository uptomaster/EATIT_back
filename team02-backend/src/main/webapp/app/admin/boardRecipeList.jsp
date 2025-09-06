<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>레시피 관리</title>
  <script defer src="${pageContext.request.contextPath}/assets/js/admin/boardRecipeList.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/boardRecipeList.css">
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
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
        <li class="sidebar_list" id="sidebar_list_dashboard">
          <a href="${pageContext.request.contextPath}/admin/dashboard.ad">대시보드</a>
        </li>
        <li class="sidebar_list" id="sidebar_list_member">
          <a href="${pageContext.request.contextPath}/admin/member/list.ad">회원관리</a>
        </li>
        <li class="sidebar_list active" id="sidebar_list_community">
          <a href="${pageContext.request.contextPath}/admin/notice/list.ad">게시글 관리</a>
        </li>
        <li class="sidebar_list" id="sidebar_list_warning">
          <a href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a>
        </li>
        <li class="sidebar_list" id="sidebar_list_customerservice">
          <a href="${pageContext.request.contextPath}/admin/faq/list.ad">고객센터</a>
        </li>
      </ul>
      <form action="${pageContext.request.contextPath}/admin/logoutOk.ad" method="post">
        <button id="admin_logoutbtn">로그아웃</button>
      </form>
    </aside>

    <!-- 메인컨텐츠 -->
    <div class="admin_inner">
      <h1 class="admin_pagetitle">게시글 관리</h1>
      <div class="admin_listwrapper">

        <!-- 탭 메뉴 -->
        <div class="admin_list_title">
          <ul class="admin_list">
            <li class="admin_list_menu">
              <a href="${pageContext.request.contextPath}/admin/notice/list.ad">공지사항</a>
            </li>
            <li class="admin_list_menu">
              <a href="${pageContext.request.contextPath}/admin/boardFree/list.ad">자유게시판</a>
            </li>
            <li class="admin_list_menu">
              <a href="${pageContext.request.contextPath}/admin/boardPromotion/list.ad">홍보게시판</a>
            </li>
            <li class="admin_list_menu active">
              <a href="${pageContext.request.contextPath}/admin/boardRecipe/list.ad">레시피</a>
            </li>
          </ul>
        </div>

        <!-- 게시글 목록 -->
        <div class="admin_list_whitebox">
          <ul class="admin_list_name">
            <li class="admin_list_row col-num">번호</li>
            <li class="admin_list_row col-title">제목</li>
            <li class="admin_list_row col-user">작성자</li>
            <li class="admin_list_row col-date">등록일</li>
            <li class="admin_list_row col-views">조회</li>
            <li class="admin_list_row col-likes">추천</li>
            <li class="admin_list_row col-manage">관리</li>
          </ul>

          <ul class="admin_list_valuebox">
            <c:choose>
              <c:when test="${not empty recipeList}">
                <c:forEach var="post" items="${recipeList}">
                  <li class="admin_list_value">
                    <p class="admin_list_row col-num">${post.postNumber}</p>
                    <p class="admin_list_row col-title">
                      <a href="${pageContext.request.contextPath}/admin/boardRecipe/detail.ad?postNumber=${post.postNumber}"
                         class="admin_list_userid_link">${post.postTitle}</a>
                    </p>
                    <p class="admin_list_row col-user">${post.memberId}</p>
                    <p class="admin_list_row col-date">${post.postCreatedDate}</p>
                    <p class="admin_list_row col-views">${post.postViewCount}</p>
                    <p class="admin_list_row col-likes">${post.postLikeCount}</p>
                    <p class="admin_list_row col-manage">
                      <form action="${pageContext.request.contextPath}/admin/boardRecipe/deleteOk.ad" method="post">
                        <input type="hidden" name="postNumber" value="${post.postNumber}">
                        <button type="submit" class="delete_btn">삭제</button>
                      </form>
                    </p>
                  </li>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <li class="admin_list_value empty">등록된 레시피 글이 없습니다.</li>
              </c:otherwise>
            </c:choose>
          </ul>
        </div>

        <!-- 페이지네이션 + 검색창 -->
        <div class="admin_pagenation_search">
          <!-- 페이지네이션 -->
          <ul class="admin_pagenation">
            <c:if test="${prev}">
              <li><a href="${pageContext.request.contextPath}/admin/boardRecipe/list.ad?page=${startPage-1}" class="prev">&lt;</a></li>
            </c:if>
            <c:forEach var="i" begin="${startPage}" end="${endPage}">
              <c:choose>
                <c:when test="${i == page}">
                  <li><a href="#" class="active">${i}</a></li>
                </c:when>
                <c:otherwise>
                  <li><a href="${pageContext.request.contextPath}/admin/boardRecipe/list.ad?page=${i}">${i}</a></li>
                </c:otherwise>
              </c:choose>
            </c:forEach>
            <c:if test="${next}">
              <li><a href="${pageContext.request.contextPath}/admin/boardRecipe/list.ad?page=${endPage+1}" class="next">&gt;</a></li>
            </c:if>
          </ul>

          <!-- 검색 -->
          <div class="admin_search_wrapper">
            <form action="${pageContext.request.contextPath}/admin/boardRecipe/list.ad" method="get" class="admin_search">
              <select class="admin_notice_category" name="searchType">
                <option value="title" ${searchType == 'title' ? 'selected' : ''}>제목</option>
                <option value="memberId" ${searchType == 'memberId' ? 'selected' : ''}>아이디</option>
              </select>
              <input type="text" id="searchWord" name="searchWord" value="${searchWord}">
              <button class="search_btn" type="submit"><i class="fas fa-search"></i></button>
            </form>
          </div>
        </div>

      </div>
    </div>
  </div>
</body>
</html>
