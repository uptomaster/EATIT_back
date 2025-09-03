<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>공지사항 관리</title>
  <script defer src="${pageContext.request.contextPath}/assets/js/admin/noticeList.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/noticeList.css">
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
        integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
  <!-- 회색영역 -->
  <div class="admin_innerwrapper">
    <!-- 좌측 사이드바 -->
    <aside class="sidebar">
      <!-- 관리자페이지 로고 -->
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
      <!-- 로그아웃 버튼 -->
      <form action="${pageContext.request.contextPath}/admin/logoutOk.ad" method="post">
        <button id="admin_logoutbtn">로그아웃</button>
      </form>
    </aside>

    <!-- 메인컨텐츠 영역 -->
    <div class="admin_inner">
      <h1 class="admin_pagetitle">게시글 관리</h1>
      <div class="admin_listwrapper">
        
        <!-- 탭 메뉴 -->
        <div class="admin_list_title">
          <ul class="admin_list">
            <li id="admin_list_menu_notice" class="admin_list_menu">
              <a href="${pageContext.request.contextPath}/admin/notice/list.ad">공지사항</a>
            </li>
            <li id="admin_list_menu_free" class="admin_list_menu">
              <a href="${pageContext.request.contextPath}/admin/boardFree/list.ad">자유게시판</a>
            </li>
            <li id="admin_list_menu_propaganda" class="admin_list_menu">
              <a href="${pageContext.request.contextPath}/admin/boardPromotion/list.ad">홍보게시판</a>
            </li>
            <li id="admin_list_menu_recipe" class="admin_list_menu">
              <a href="${pageContext.request.contextPath}/admin/boardRecipe/list.ad">레시피</a>
            </li>
          </ul>
        </div>

        <!-- 회원 목록 -->
        <div class="admin_list_whitebox">
          <div class="admin_list_namebox">
            <!-- 컬럼 명 -->
            <ul class="admin_list_name">
              <li id="admin_list_num" class="admin_list_row">번호</li>
              <li id="admin_list_title" class="admin_list_row">제목</li>
              <li id="admin_list_userid" class="admin_list_row">작성자</li>
              <li id="admin_list_date" class="admin_list_row">등록일</li>
              <li id="admin_list_views" class="admin_list_row">조회</li>
              <li id="admin_list_likes" class="admin_list_row">추천</li>
            </ul>
          </div>        
          <!-- 글 목록 행 -->
          <ul class="admin_list_valuebox">
            <c:choose>
              <c:when test="${not empty noticeList}">
                <c:forEach var="notice" items="${noticeList}">
                  <li class="admin_list_value">
                    <!-- 번호: postNumber -->
                    <p class="admin_list_row">
                      <c:out value="${notice.postNumber}" />
                    </p>
                    <!-- 제목 -->
                    <p class="admin_list_row">
                      <a href="${pageContext.request.contextPath}/admin/notice/detail.ad?postNumber=${notice.postNumber}"
                         class="admin_list_userid_link">
                        <c:out value="${notice.postTitle}" />
                      </a>
                    </p>
                    <!-- 작성자 -->
                    <p class="admin_list_row">
                      <img class="grade_icon" src="${pageContext.request.contextPath}/assets/img/관리자.png" alt="">
                      <c:out value="${notice.adminId}" />
                    </p>
                    <!-- 등록일 -->
                    <p class="admin_list_row">
                      <c:out value="${notice.postCreatedDate}" />
                    </p>
                    <!-- 조회 -->
                    <p class="admin_list_row">
                      <c:out value="${notice.postViewCount}" />
                    </p>
                    <!-- 추천 -->
                    <p class="admin_list_row">
                      <c:out value="${notice.postLikeCount}" />
                    </p>            
                  </li>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <li class="admin_list_value">등록된 공지사항이 없습니다.</li>
              </c:otherwise>
            </c:choose>
          </ul>
        </div> 

        <!-- 페이지네이션, 검색창 -->
        <div class="admin_pagenation_search">
          <!-- 공지작성버튼 -->
          <form action="${pageContext.request.contextPath}/admin/notice/write.ad" method="get">
            <button id="prepare_notice_btn" type="submit">공지작성</button>
          </form>

          <!-- 페이지네이션 -->
          <ul class="admin_pagenation">
            <c:if test="${prev}">
              <li>
                <a href="${pageContext.request.contextPath}/admin/notice/list.ad?page=${startPage-1}" class="prev">&lt;</a>
              </li>
            </c:if>
            <c:forEach var="i" begin="${startPage}" end="${endPage}">
              <c:choose>
                <c:when test="${i == page}">
                  <li><a href="#" class="active"><c:out value="${i}" /></a></li>
                </c:when>
                <c:otherwise>
                  <li>
                    <a href="${pageContext.request.contextPath}/admin/notice/list.ad?page=${i}">
                      <c:out value="${i}" />
                    </a>
                  </li>
                </c:otherwise>
              </c:choose>
            </c:forEach>
            <c:if test="${next}">
              <li>
                <a href="${pageContext.request.contextPath}/admin/notice/list.ad?page=${endPage+1}" class="next">&gt;</a>
              </li>
            </c:if>
          </ul>

          <!-- 검색 -->
          <form action="${pageContext.request.contextPath}/admin/notice/list.ad" method="get">
            <div class="admin_search">
              <select class="admin_notice_category" name="searchType">
                <option value="title" ${searchType == 'title' ? 'selected' : ''}>제목</option>
                <option value="adminId" ${searchType == 'adminId' ? 'selected' : ''}>작성자</option>
              </select>
              <input type="text" id="searchWord" name="searchWord" value="${searchWord}">
              <button class="search_btn" type="submit">
                <i class="fas fa-search"></i>
              </button>
            </div>
          </form>
        </div> 
      </div>  
    </div>
  </div>
</body>
</html>
