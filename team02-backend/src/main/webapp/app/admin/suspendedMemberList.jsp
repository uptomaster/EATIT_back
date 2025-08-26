<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>정지회원목록</title>
  <script defer src="${pageContext.request.contextPath}/assets/js/admin/suspendedMemberList.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin/suspendedMemberList.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
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
        <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/postTrade/list.ad">게시글 관리</a></li>
        <li class="sidebar_list" id="sidebar_list_warning"><a href="${pageContext.request.contextPath}/admin/report/list.ad">신고관리</a></li>
        <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/banner/list.ad">배너/광고</a></li>
        <li class="sidebar_list"><a href="${pageContext.request.contextPath}/admin/inquiry/list.ad">고객센터</a></li>
      </ul>
      <form action="${pageContext.request.contextPath}/admin/logoutOk.ad" method="post">
        <button id="admin_logoutbtn">로그아웃</button>
      </form>
    </aside>

    <!-- 메인컨텐츠 영역 -->
    <div class="admin_inner">
      <h1 class="admin_pagetitle">신고관리 - 정지회원목록</h1>
      <div class="admin_listwrapper">
        
        <!-- 탭 메뉴 -->
        <div class="admin_list_title">
          <ul class="admin_list">
            <li class="admin_list_menu">
              <a href="${pageContext.request.contextPath}/admin/report/list.ad">신고목록</a>
            </li>
            <li class="admin_list_menu" id="admin_list_menu_ban">
              <a href="${pageContext.request.contextPath}/admin/suspend/list.ad">정지회원목록</a>
            </li>
            <li class="admin_list_menu">
              <a href="${pageContext.request.contextPath}/admin/blacklist/list.ad">블랙리스트</a>
            </li>
          </ul>
        </div>

        <!-- 컬럼 헤더 -->
        <div class="admin_list_whitebox">
          <div class="admin_list_namebox">
            <ul class="admin_list_name">
              <li class="admin_list_row">회원번호</li>
              <li class="admin_list_row">정지 시작일</li>
              <li class="admin_list_row">정지 종료일</li>
              <li class="admin_list_row">누적 신고 수</li>
            </ul>
          </div>

          <!-- 실제 정지회원 목록 -->
          <ul class="admin_list_valuebox">
            <c:choose>
              <c:when test="${not empty suspendList}">
                <c:forEach var="s" items="${suspendList}">
                  <li class="admin_list_value">
                    <p class="admin_list_row">${s.memberNumber}</p>
                    <p class="admin_list_row">${s.suspendStartDate}</p>
                    <p class="admin_list_row">${s.suspendEndDate}</p>
                    <p class="admin_list_row">${s.suspendReportCount}</p>
                  </li>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <li class="admin_list_value">
                  <p class="admin_list_row">정지된 회원이 없습니다.</p>
                </li>
              </c:otherwise>
            </c:choose>
          </ul>
        </div>

        <!-- 검색창 (옵션) -->
        <div class="admin_pagenation_search">
          <form action="${pageContext.request.contextPath}/admin/suspend/list.ad" method="get">
            <div class="admin_search">
              <select class="admin_notice_category" name="searchType">
                <option value="id">아이디</option>
                <option value="name">이름</option>
                <option value="type">유형</option>
              </select>
              <input type="text" id="search_word" name="searchWord" value="${searchWord}">
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
